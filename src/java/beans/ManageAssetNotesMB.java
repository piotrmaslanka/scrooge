/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.util.Date;
import java.util.List;
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
@ManagedBean(name = "manageAssetNotesMB")
@ViewScoped
public class ManageAssetNotesMB {
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
    
    public void setUnsolvedAssetNotes(List<AssetNotes> assets) { }
    public List<AssetNotes> getUnsolvedAssetNotes() {
        return this.assetNotesService.getUnsolvedAssetNotes();
    }    
    
    public void setSolvedAssetNotes(List<AssetNotes> assets) { }
    public List<AssetNotes> getSolvedAssetNotes() {
        return this.assetNotesService.getSolvedAssetNotes();
    }        
    
    public void reportNote() {
        Assets asset = null;
        try {
            asset = this.assetsService.getAssetById(this.targetAssetID);
        } catch (IndexOutOfBoundsException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Nie ma takiego środka"));                
                    return;
        }
        
        this.assetNote.setAssets(asset);
        this.assetNote.setUsers(this.loginState.getUser());        
        this.assetNote.setIsSolved(false);
        this.assetNote.setWhenCreated(new Date());
        // Assert valid ID
        this.assetNote.setId((int)(System.currentTimeMillis() / 10000));        
        
        this.assetNotesService.addAssetNotes(this.assetNote);
            FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dodano", "Dodano zgłoszenie")); 
    }
    
    private String targetAssetID = "";
    public void setTargetAssetID(String x) { this.targetAssetID = x; }
    public String getTargetAssetID() { return this.targetAssetID; }
    
    private AssetNotes assetNote = new AssetNotes();
    public void setAssetNote(AssetNotes an) { this.assetNote = an; }
    public AssetNotes getAssetNote() { return this.assetNote; }
}
