package components;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import models.Users;
import org.springframework.stereotype.Component;
import service.UsersService;
/**
 * Component with logged in user
 * @author Maslanka
 */
@Component
@SessionScoped
public class LoginState implements Serializable {
    private Users user = null; // user that was logged in
    
    public void setUser(Users user) { this.user = user; }
    public Users getUser() { return this.user; }
    public void setUsersService(UsersService usersService) { this.usersService = usersService; }
    
    @ManagedProperty(value = "#{usersServiceImpl}" )
    private UsersService usersService;    

   
    public boolean isAdmin() { 
        if (this.user == null) return false;    // certainly not admin :)
        return this.user.getIsAdmin();
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }
    
    /**
     * Return whether authentication succeeded. If so, assigns user to internal store.
     * @param login user login
     * @param password user password
     * @return whether authentication succeeded
     */
    public boolean authenticate(String login, String password) {
        Users u = this.usersService.getUserByLogin(login);
        if (u == null) return false;
        if (u.getPassword().equals(password)) {
            // authenticated just right
            this.user = u;
            return true;
        }
        return false;
    }
    
    /**
     * Logs user out
     */
    public void logout() {
        this.user = null;
    }
    
    
}
