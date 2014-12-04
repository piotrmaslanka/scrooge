package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Location;
import service.LocationService;

/**
 * Bean for managing locations
 * @author Maslanka
 */
@ManagedBean(name = "manageLocationsMB")
@SessionScoped
public class ManageLocationsMB implements Serializable {
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;   

    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    public LoginState getLoginState() { return this.loginState; }

    @ManagedProperty(value="#{locationServiceImpl}")
    private LocationService locationService;    

    public void setLocationService(LocationService locationService) { this.locationService = locationService; }
    public LocationService getLocationService() { return this.locationService; }
    
    private Location location = new Location();
    public void setLocation(Location location) { this.location = location; }
    public Location getLocation() { return this.location; }
    
    /**
     * Serves to get all locations. Access as a property.
     */
    public List<Location> getAllLocations() {
        if (!this.loginState.isAdmin())
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            } catch (IOException e) {}
 
        return this.locationService.getAllLocations();
    }
    
    /**
     * Add location
     */
    public void addLocation() {
        if (!this.loginState.isAdmin())
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            } catch (IOException e) {}
        this.locationService.addLocation(this.location);
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano", "Dodano nową salę"));        
    }
}
