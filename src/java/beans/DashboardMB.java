/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import components.LoginState;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import service.AssetNotesService;

/**
 * Bean with dashboard pages
 */
@ManagedBean(name = "dashboardMB")
@SessionScoped
public class DashboardMB implements Serializable {

    @ManagedProperty(value = "#{loginState}")
    private LoginState loginState;

    public void setLoginState(LoginState loginState) {
        this.loginState = loginState;
    }

    public LoginState getLoginState() {
        return this.loginState;
    }

    @ManagedProperty(value = "#{assetNotesServiceImpl}")
    private AssetNotesService assetNotesService;

    public void setAssetNotesService(AssetNotesService assetNotesService) {
        this.assetNotesService = assetNotesService;
    }

    public AssetNotesService getAssetNotesService() {
        return this.assetNotesService;
    }

    public int getOutstandingAssetNotesAmount() {
        if (!this.loginState.isLoggedIn()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
            }
        }
        return this.assetNotesService.getUnsolvedAssetNotes().size();
    }

    public void raportyRedirect() {
        if (this.loginState.isLoggedIn()) {
            try {
                if (this.loginState.isAdmin()) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("raporty.xhtml");
                    } catch (IOException e) {
                    }
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("raporty.xhtml");
                }
            } catch (IOException e) {
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void audytRedirect() {
        if (this.loginState.isLoggedIn()) {
            try {
                if (this.loginState.isAdmin()) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("audyt.xhtml");
                    } catch (IOException e) {
                    }
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("audyt.xhtml");
                }
            } catch (IOException e) {
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void edycjaRedirect() {
        if (this.loginState.isLoggedIn()) {
            try {
                if (this.loginState.isAdmin()) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("edycja.xhtml");
                    } catch (IOException e) {
                    }
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("denied.xhtml");
                }
            } catch (IOException e) {
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void rejestrRedirect() {
        if (this.loginState.isLoggedIn()) {
            try {
                if (this.loginState.isAdmin()) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("rejestr.xhtml");
                    } catch (IOException e) {
                    }
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("denied.xhtml");
                }
            } catch (IOException e) {
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void zgloszeniaRedirect() {
        if (this.loginState.isLoggedIn()) {
            try {
                if (this.loginState.isAdmin()) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("zgloszenia.xhtml");
                    } catch (IOException e) {
                    }
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("zgloszenia.xhtml");
                }
            } catch (IOException e) {
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
            }
        }
    }
    
    public void zarzadzanieKontamiRedirect(){
        if (this.loginState.isLoggedIn()) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("uzytkownicy.xhtml");
            } catch (IOException e) {
            }
        }
    }

}
