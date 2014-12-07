package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Assets;
import models.Location;
import models.Users;
import service.AssetsService;
import service.LocationService;
import service.UsersService;

/**
 *
 * @author Maslanka
 */
@ManagedBean(name = "manageAssetsMB")
@ViewScoped
public class ManageAssetsMB implements Serializable {
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;   

    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    public LoginState getLoginState() { return this.loginState; }

    @ManagedProperty(value="#{locationServiceImpl}")
    private LocationService locationService;    

    @ManagedProperty(value="#{assetsServiceImpl}")
    private AssetsService assetsService;

    @ManagedProperty(value="#{usersServiceImpl}")
    private UsersService usersService;
    
    
    public void setAssetsService(AssetsService assetsService) { this.assetsService = assetsService; }
    public AssetsService getAssetsService() { return this.assetsService; }
    public void setLocationService(LocationService locationService) { this.locationService = locationService; }
    public LocationService getLocationService() { return this.locationService; }    
    public void setUsersService(UsersService usersService) { this.usersService = usersService; }
    public UsersService getUsersService() { return this.usersService; }    


    private Location location;
    public void setLocation(Location location) { this.location = location; }
    public Location getLocation() { 
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (this.location == null) {
            this.location = this.locationService.getLocationById(params.get("location"));
        }
        if (params.get("location") != null)
            if (params.get("location") != this.location.getId())
                this.location = this.locationService.getLocationById(params.get("location"));
   
        return this.location;
    }
 
    public void setAssets(List<Assets> assets) { }
    public List<Assets> getAssets() {
        return new ArrayList<Assets>(this.locationService.getLocationById(this.location.getId()).getAssetses());
    }
   
    private Assets asset = new Assets();
    public void setAsset(Assets asset) { this.asset = asset; }
    public Assets getAsset() { return this.asset; }

    public Map<String, String> getUserChoices() {
        Map<String, String> usermap = new HashMap<>();
        for (Users user : this.usersService.getAllUsers())
            usermap.put(user.getName()+" "+user.getPassword(), user.getLogin());
        usermap.put("Nikt", null);
        return usermap;
    }
    
    private String targetUser;
    public void setTargetUser(String tu) { this.targetUser = tu; }
    public String getTargetUser() { return this.targetUser; }
    
    /**
     * Add location
     */
    public void addAsset() {
        if (!this.loginState.isAdmin())
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            } catch (IOException e) {}
  
        this.asset.setLocation(this.getLocation());
        if (this.targetUser != null)
            this.asset.setUsers(this.usersService.getUserByLogin(this.targetUser));
     
        getAssetsService().addAsset(this.asset);
        this.asset = new Assets();
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano", "Dodano nowy środek"));        
    }    
}
