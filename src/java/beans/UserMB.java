/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import service.UsersService;

/**
 *
 * @author Muman
 */
@ManagedBean(name = "usersMB")
@SessionScoped
public class UserMB implements Serializable{

    private int x = 0;
    
    public UserMB() {
        
    }
    
    public void incX() {
        this.x++;
    }
    
    public int getX() { return this.x; }
    public void setX(int x) { this.x = x; }
    
    private Users user = new Users();
    
    @ManagedProperty(value = "#{usersServiceImpl}" )
    private UsersService usersService;
   
    
    @Autowired
    private LoginState loginState;
   
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
    
    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
}
