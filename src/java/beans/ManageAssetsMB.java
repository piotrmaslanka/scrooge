package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import models.Assets;
import models.Location;
import models.Report;
import models.ReportItem;
import models.Users;
import org.primefaces.event.RowEditEvent;
import service.AssetsService;
import service.LocationService;
import service.ReportItemService;
import service.ReportService;
import service.UsersService;
import utils.ProjectUtils;

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
    
    @ManagedProperty(value="#{reportServiceImpl}")
    private ReportService reportService;
    
    @ManagedProperty(value="#{reportItemServiceImpl}")
    private ReportItemService reportItemService;
      
    private Location location;
    
    private String targetUser;
    
    private Assets asset = new Assets();
    
    private Location selectedLocation;
    
    private List<Assets> assetsForSelecetedLocation;
    
    private List<Location> Locations;
    
    @PostConstruct
    public void init(){
        setLocations(locationService.getAllLocations());
        
    }
    
    private String newReportExtraInfo;
    
    public void setAssetsService(AssetsService assetsService) { this.assetsService = assetsService; }
    public AssetsService getAssetsService() { return this.assetsService; }
    public void setLocationService(LocationService locationService) { this.locationService = locationService; }
    public LocationService getLocationService() { return this.locationService; }    
    public void setUsersService(UsersService usersService) { this.usersService = usersService; }
    public UsersService getUsersService() { return this.usersService; }    
    public void setLocation(Location location) { this.location = location; }
    
    public Location getLocation() { 
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (this.location == null) {
            this.location = this.locationService.getLocationById(params.get("location"));
        }
        if (params.get("location") != null)
            if (!params.get("location").equals(this.location.getId()))
                this.location = this.locationService.getLocationById(params.get("location"));
   
        return this.location;
    }
 
    public void setAssets(List<Assets> assets) { 
        
    }
    
    public List<Assets> getAssets() {
        return new ArrayList<Assets>(this.locationService.getLocationById(this.location.getId()).getAssetses());
    }
   
    public void setAsset(Assets asset) { this.asset = asset; }
    public Assets getAsset() { return this.asset; }

    public Map<String, String> getUserChoices() {
        Map<String, String> usermap = new HashMap<>();
        for (Users user : this.usersService.getAllUsers())
            usermap.put(user.getName()+" "+user.getPassword(), user.getLogin());
        usermap.put("Nikt", null);
        return usermap;
    }
    
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
  
        // check name uniqueness
        boolean unique = false;
        try {
            this.assetsService.getAssetById(this.asset.getId());           
        } catch (IndexOutOfBoundsException e) {
            unique = true;
        }
        
        if (!unique) {
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "ID nie jest unikatowe"));             
            return;
        }
        
        this.asset.setLocation(this.getLocation());
        if (this.targetUser != null)
            this.asset.setUsers(this.usersService.getUserByLogin(this.targetUser));
     
        getAssetsService().addAsset(this.asset);
        this.asset = new Assets();
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano", "Dodano nowy środek"));        
    } 

    /**
     * @return the selectedLocation
     */
    public Location getSelectedLocation() {
        return selectedLocation;
    }

    /**
     * @param selectedLocation the selectedLocation to set
     */
    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    /**
     * @return the Locations
     */
    public List<Location> getLocations() {
        return Locations;
    }

    /**
     * @param Locations the Locations to set
     */
    public void setLocations(List<Location> Locations) {
        this.Locations = Locations;
    }

    /**
     * @return the assetsForSelecetedLocation
     */
    public List<Assets> getAssetsForSelecetedLocation() {
        return assetsForSelecetedLocation;
    }

    /**
     * @param assetsForSelecetedLocation the assetsForSelecetedLocation to set
     */
    public void setAssetsForSelecetedLocation(List<Assets> assetsForSelecetedLocation) {
        this.assetsForSelecetedLocation = assetsForSelecetedLocation;
    }
    
    public void findAssetsForSelectedLocation(){
        if(null != this.selectedLocation){
           List<Assets> assetsForLocation = this.assetsService.getAssetsByLocation(this.selectedLocation);
           this.assetsForSelecetedLocation = assetsForLocation;
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        Assets assetEdited = ((Assets) event.getObject());
        
        System.out.println(assetEdited);
        
        FacesMessage msg = new FacesMessage("Asset Edited", assetEdited.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        assetsService.updateAsset(assetEdited);
    }
    
    public void onRowEditCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Assets) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void generujRaport(){
       if(null != this.assetsForSelecetedLocation && !assetsForSelecetedLocation.isEmpty()){
            Report newReport = new Report(this.selectedLocation,new Date(),this.newReportExtraInfo);
            getReportService().addReport(newReport);

            for(Assets asset : assetsForSelecetedLocation){
                ReportItem newReportItem = new ReportItem(asset,newReport,asset.getExtraInfo(),asset.isIsDamaged(),asset.isIsLost());
                getReportItemService().addReportItem(newReportItem);
            }
            
           setNewReportExtraInfo("");
           FacesMessage msg = new FacesMessage(ProjectUtils.RAPORT_GENERATED,"");
           FacesContext.getCurrentInstance().addMessage(null, msg);
       }
       else{
           FacesMessage msg = new FacesMessage(ProjectUtils.RAPORT_COULD_NOT_BE_GENERATED,"");
           FacesContext.getCurrentInstance().addMessage(null, msg);
       }
    }

    /**
     * @return the newRaportExtraInfo
     */
    public String getNewReportExtraInfo() {
        return newReportExtraInfo;
    }

    /**
     * @param newRaportExtraInfo the newRaportExtraInfo to set
     */
    public void setNewReportExtraInfo(String newRaportExtraInfo) {
        this.newReportExtraInfo = newRaportExtraInfo;
    }

    /**
     * @return the reportService
     */
    public ReportService getReportService() {
        return reportService;
    }

    /**
     * @param reportService the reportService to set
     */
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * @return the reportItemService
     */
    public ReportItemService getReportItemService() {
        return reportItemService;
    }

    /**
     * @param reportItemService the reportItemService to set
     */
    public void setReportItemService(ReportItemService reportItemService) {
        this.reportItemService = reportItemService;
    }
}
