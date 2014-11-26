/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import scrooge.models.Users;
import service.UsersService;

/**
 *
 * @author Muman
 */
@ManagedBean(name = "usersMB")
@SessionScoped
public class UserManagedBean implements Serializable{

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        
    }
    
    private Users user = new Users();
    
    @ManagedProperty(value = "#{usersServiceImpl}" )
    private UsersService usersService;
   
    /**
     * @return the user
     */
    public Users getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Users user) {
        this.user = user;
    }
    
    public void register(){
        getUsersService().addUser(user);
    }

    /**
     * @return the usersService
     */
    public UsersService getUsersService() {
        return usersService;
    }

    /**
     * @param usersService the usersService to set
     */
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }
}
