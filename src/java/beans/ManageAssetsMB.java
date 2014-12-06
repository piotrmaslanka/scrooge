package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.Assets;
import models.Location;
import service.AssetsService;
import service.LocationService;

/**
 *
 * @author Maslanka
 */
@ManagedBean(name = "manageAssetsMB")
@RequestScoped
public class ManageAssetsMB implements Serializable {
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;   

    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    public LoginState getLoginState() { return this.loginState; }

    @ManagedProperty(value="#{locationServiceImpl}")
    private LocationService locationService;    

    @ManagedProperty(value="#{assetsServiceImpl}")
    private AssetsService assetsService;
    
    public void setAssetsService(AssetsService assetsService) { this.assetsService = assetsService; }
    public AssetsService getAssetsService() { return this.assetsService; }
    public void setLocationService(LocationService locationService) { this.locationService = locationService; }
    public LocationService getLocationService() { return this.locationService; }    


    private Location location = new Location();
    public void setLocation(Location location) { this.location = location; }
    public Location getLocation() { return this.location; }
 
    private List<Assets> assets = new ArrayList<>();
    public void setAssets(List<Assets> assets) { this.assets = assets; }
    public List<Assets> getAssets() { return this.assets; }
   
    private Assets asset = new Assets();
    public void setAsset(Assets asset) { this.asset = asset; }
    public Assets getAsset() { return this.asset; }
        
    /**
     * Load a location
     * specified by GET
     */
    public void loadLocation() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.location = this.locationService.getLocationById(params.get("location"));
        this.assets = this.assetsService.getAssetsByLocation(params.get("location"));
        this.asset.setLocation(this.location);
    }
    
    
    /**
     * Add location
     */
    public void addAsset() {
        if (!this.loginState.isAdmin())
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            } catch (IOException e) {}
        this.assetsService.addAsset(this.asset);
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano", "Dodano nowy środek"));        
    }    
}
