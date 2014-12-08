/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Assets;
import models.Location;
import service.AssetsService;

/**
 *
 * @author Maslanka
 */
@ManagedBean(name = "manageAssetMB")
@ViewScoped
public class manageAssetMB {
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;   

    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    public LoginState getLoginState() { return this.loginState; }
    
    @ManagedProperty(value="#{assetsServiceImpl}")
    private AssetsService assetsService;    
    
    private Assets asset;
    public void setAsset(Assets asset) { this.asset = asset; }
    public Assets getAsset() { 
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (this.asset == null) {
            this.asset = this.assetsService.getAssetById(params.get("asset"));
        }
        if (params.get("asset") != null)
            if (params.get("asset") != this.asset.getId())
                this.asset = this.assetsService.getAssetById(params.get("asset"));
   
        return this.asset;    
    
}
