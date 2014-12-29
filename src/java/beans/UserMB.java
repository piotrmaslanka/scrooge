package beans;

import components.LoginState;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Users;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import service.UsersService;

/**
 *
 * @author Muman
 */
@ManagedBean(name = "usersMB")
@ViewScoped
public class UserMB implements Serializable{

    private int x = 0;
    
    private Users selectedUser;
    private Users user = new Users();
    private List<Users> users;
    
    public UserMB() {
    
    }
    
    @PostConstruct
    public void init() {
        setUsers(usersService.getAllUsers());
    }
    
    public void incX() {
        this.x++;
    }
    
    @ManagedProperty(value = "#{usersServiceImpl}" )
    private UsersService usersService;
       
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;     
        
    public int getX() { return this.x; }
    public void setX(int x) { this.x = x; }
    
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
    
    public String register(){
        if(null != this.user){
            getUsersService().addUser(user);
            this.user = new Users();
            this.users = getUsersService().getAllUsers();
        }
            
        return "";
    }
    
    public String deleteSelectedUser(){
        if(null != selectedUser){
            if(null != usersService){
                usersService.deleteUser(selectedUser);
                users = usersService.getAllUsers();
                selectedUser = null;
            }
        }
        return null;
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

    /**
     * @return the selectedUser
     */
    public Users getSelectedUser() {
        return selectedUser;
    }

    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    /**
     * @return the users
     */
    public List<Users> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<Users> users) {
        this.users = users;
    }
    
    
    public void onRowEdit(RowEditEvent event) {
        Users userEdited = ((Users) event.getObject());
        
        FacesMessage msg = new FacesMessage("User Edited", userEdited.getLogin());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        usersService.updateUser(userEdited);
        
        users = usersService.getAllUsers();
    }
}
