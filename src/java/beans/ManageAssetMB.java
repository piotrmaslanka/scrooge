/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Assets;
import models.Lends;
import models.Location;
import models.Users;
import org.hibernate.exception.SQLGrammarException;
import service.AssetsService;
import service.LendsService;
import service.LocationService;
import service.UsersService;

/**
 *
 * @author Maslanka
 */
@ManagedBean(name = "manageAssetMB")
@ViewScoped
public class ManageAssetMB {
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
    
    @ManagedProperty(value="#{lendsServiceImpl}")
    private LendsService lendsService;
    
    public void setAssetsService(AssetsService assetsService) { this.assetsService = assetsService; }
    public AssetsService getAssetsService() { return this.assetsService; }
    public void setLocationService(LocationService locationService) { this.locationService = locationService; }
    public LocationService getLocationService() { return this.locationService; }    
    public void setUsersService(UsersService usersService) { this.usersService = usersService; }
    public UsersService getUsersService() { return this.usersService; }      
    public void setLendsService(LendsService lendsService) { this.lendsService = lendsService; }
    public LendsService getLendsService() { return this.lendsService; }      
    
    private Assets asset;
    public void setAsset(Assets asset) { this.asset = asset; }
    public Assets getAsset() { 
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (this.asset == null) {
            this.asset = this.assetsService.getAssetById(params.get("asset"));
        }
        if (params.get("asset") != null)
            if (!params.get("asset").equals(this.asset.getId()))
                this.asset = this.assetsService.getAssetById(params.get("asset"));
   
        return this.asset;    
    }
    
    public String targetUser;
    public void setTargetUser(String ts) { this.targetUser = ts; }
    public String getTargetUser() {
        try {
            this.targetUser = this.getAsset().getUsers().getLogin();
        } catch (NullPointerException e) {
            this.targetUser = null;
        }
        return this.targetUser;
    }
    
    public String targetLocation;
    public void setTargetLocation(String ts) { this.targetLocation = ts; }
    public String getTargetLocation() {
        try {
            this.targetLocation = this.getAsset().getLocation().getId();
        } catch (NullPointerException e) {
            this.targetLocation = null;
        }
        return this.targetLocation;
    }
    
    
    public Map<String, String> getLocationChoices() {
        Map<String, String> locmap = new HashMap<>();
        for (Location loc : this.locationService.getAllLocations())
            locmap.put(loc.getId(), loc.getId());
        locmap.put("Nigdzie", null);
        return locmap;
    }
    
    public Map<String, String> getUserChoices() {
        Map<String, String> usermap = new HashMap<>();
        for (Users user : this.usersService.getAllUsers())
            usermap.put(user.getName()+" "+user.getPassword(), user.getLogin());
        usermap.put("Nikt", null);
        return usermap;
    }    
    
    private Lends lend = new Lends();
    public void setLend(Lends lend) { this.lend = lend; }
    public Lends getLend() { return this.lend; }
    
    
    public boolean getCanRent() {
        if (!this.getAsset().isIsLendable()) return false;
        boolean canRent = true;
        Date currentDate = new Date();
        for (Lends lend : this.getAsset().getLendses())
            if (lend.getLendTo().after(currentDate))
                canRent = false;
        return canRent;
    }
    
    public void lendAsset() {
        this.lend.setAssets(this.asset);
        this.lend.setUsers(this.loginState.getUser());
        this.lendsService.addLend(this.lend);
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Wypożyczono", "Wypożyczono środek"));
    }
    
    public void updateAsset() {
        try {
            this.asset.setLocation(this.locationService.getLocationById(this.targetLocation));
        } catch (SQLGrammarException | NullPointerException e) {
            this.asset.setLocation(null);
        }
        try {
            this.asset.setUsers(this.usersService.getUserByLogin(this.targetUser));
        } catch (SQLGrammarException e) {
            this.asset.setUsers(null);
        }
        
        this.assetsService.updateAsset(this.asset);
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Zmodyfikowano", "Zmieniono środek"));
    }
}
