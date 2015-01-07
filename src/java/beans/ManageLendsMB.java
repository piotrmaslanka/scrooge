package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Lends;
import service.LendsService;

/**
 * @author Piotr Maślanka
 */
@ManagedBean(name = "manageLendsMB")
@ViewScoped
public class ManageLendsMB implements Serializable {
    @ManagedProperty(value = "#{loginState}")
    private LoginState loginState;

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }
    @ManagedProperty(value="#{lendsServiceImpl}")
    private LendsService lendsService;
    
    public LendsService getLendsService() { return this.lendsService; }
    public void setLendsService(LendsService x) { this.lendsService = x; }
    
    public List<Lends> getAllLends() {
        if (!this.loginState.isAdmin())
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {}
    
        return this.lendsService.getAllLends();
    }
    public void setAllLends(List<Lends> locations) {}    
    
    
}
