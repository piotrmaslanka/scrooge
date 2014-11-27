/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import service.AssetNotesService;

/**
 * Bean with dashboard pages
 */
@ManagedBean(name = "dashboardMB")
@SessionScoped
public class DashboardMB implements Serializable {
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;   
    
    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    public LoginState getLoginState() { return this.loginState; }
    
    @ManagedProperty(value="#{assetNotesServiceImpl}")
    private AssetNotesService assetNotesService;
    
    public void setAssetNotesService(AssetNotesService assetNotesService) { this.assetNotesService = assetNotesService; }
    public AssetNotesService getAssetNotesService() { return this.assetNotesService; }
    
    public int getOutstandingAssetNotesAmount() {
        return this.assetNotesService.getAllAssetNotes().size();              
    }
    
}
