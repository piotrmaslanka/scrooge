package beans;

import components.LoginState;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Bean doing login/logout stuff
 * @author Maslanka
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {
    
    private String login;
    private String password;
    
    public void setLogin(String login) { this.login = login; }
    public String getLogin() { return this.login; }
    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return this.password; }

    @ManagedProperty(value="#loginState")
    private LoginState loginState;   

    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    
    
}
