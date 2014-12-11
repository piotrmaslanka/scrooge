/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.AssetNotes;
import models.Assets;
import service.AssetNotesService;
import service.AssetsService;

/**
 *
 * @author Maslanka
 */
@ManagedBean(name = "manageAssetNoteMB")
@ViewScoped
public class ManageAssetNoteMB {
    @ManagedProperty(value="#{loginState}")
    private LoginState loginState;   

    public void setLoginState(LoginState loginState) { this.loginState = loginState; }
    public LoginState getLoginState() { return this.loginState; }    
    
    
    @ManagedProperty(value="#{assetsServiceImpl}")
    private AssetsService assetsService;
    public void setAssetsService(AssetsService assetsService) { this.assetsService = assetsService; }
    public AssetsService getAssetsService() { return this.assetsService; }    
    
    @ManagedProperty(value="#{assetNotesServiceImpl}")
    private AssetNotesService assetNotesService;
    public void setAssetNotesService(AssetNotesService x) { this.assetNotesService = x; }
    public AssetNotesService getAssetNotesService() { return this.assetNotesService; }
       
    private AssetNotes assetNote;
    public void setAssetNote(AssetNotes an) { this.assetNote = an; }
    public AssetNotes getAssetNote() { 
    
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (this.assetNote == null) {
            System.out.println(params.get("an"));
            int i = new Integer(params.get("an"));
            this.assetNote = this.assetNotesService.getAssetNotesById(i);
        }
        if (params.get("an") != null)
            if (new Integer(params.get("an")) != this.assetNote.getId())
                this.assetNote = this.assetNotesService.getAssetNotesById(new Integer(params.get("an")));
   
        return this.assetNote;      
    }
    
    public void updateStuff() {
        this.assetsService.updateAsset(this.assetNote.getAssets());
        this.assetNote.setIsSolved(true);
        this.assetNotesService.updateAssetNotes(this.assetNote);
                
        FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaktualizowano", "Obsłużono notę"));    
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("zgloszenia.xhtml");
        } catch (IOException e) {}        
    }
}
