package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Bean doing login/logout stuff
 *
 * @author Maslanka
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    @ManagedProperty(value = "#{loginState}")
    private LoginState loginState;

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public void doLogin() {
        if (this.loginState.authenticate(this.login, this.password)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Zalogowano."));
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
            } catch (IOException e) {
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Błędny login lub hasło."));
        }
    }

    public void doLogout() {
        this.loginState.logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
        }
    }
}
