/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.LendsDAO;
import java.util.List;
import scrooge.models.Lends;

/**
 *
 * @author Muman
 */
public class LendsServiceImpl implements LendsService{

    private LendsDAO lendsDAO;
    
    @Override
    public void addLend(Lends lend) {
        getLendsDAO().addLend(lend);
    }

    @Override
    public void deleteLend(Lends lend) {
        getLendsDAO().deleteLend(lend);
    }

    @Override
    public void updateLend(Lends lend) {
        getLendsDAO().updateLend(lend);
    }

    @Override
    public Lends getLendById(int id) {
        return getLendsDAO().getLendById(id);
    }

    @Override
    public List<Lends> getAllLends() {
        return getLendsDAO().getAllLends();
    }

    /**
     * @return the LendsDAO
     */
    public LendsDAO getLendsDAO() {
        return lendsDAO;
    }

    /**
     * @param LendsDAO the LendsDAO to set
     */
    public void setLendsDAO(LendsDAO lendsDAO) {
        this.lendsDAO = lendsDAO;
    }
}
