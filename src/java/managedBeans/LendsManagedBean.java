/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import dao.LendsDAO;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import scrooge.models.Lends;
import service.LendsService;

/**
 *
 * @author Muman
 */
@SessionScoped
public class LendsManagedBean {
    
    private LendsService lendsService;
    
    private List<Lends> lends;
    private Lends lend;

    /**
     * @return the lends
     */
    public List<Lends> getLends() {
        return getLendsService().getAllLends();
    }

    /**
     * @param lends the lends to set
     */
    public void setLends(List<Lends> lends) {
        this.lends = lends;
    }

    /**
     * @return the lendsService
     */
    public LendsService getLendsService() {
        return lendsService;
    }

    /**
     * @param lendsService the lendsService to set
     */
    public void setLendsService(LendsService lendsService) {
        this.lendsService = lendsService;
    }
}
