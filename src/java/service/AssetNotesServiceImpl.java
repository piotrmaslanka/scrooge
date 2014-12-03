/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AssetNotesDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import models.AssetNotes;

/**
 *
 * @author Muman
 */
@Transactional
public class AssetNotesServiceImpl implements AssetNotesService{
    @Autowired
    private AssetNotesDAO assetNotesDAO;
    
    @Override
    public void addAssetNotes(AssetNotes assetNotes) {
        getAssetNotesDAO().addAssetNotes(assetNotes);
    }

    @Override
    public void deleteAssetNotes(AssetNotes assetNotes) {
        getAssetNotesDAO().deleteAssetNotes(assetNotes);
    }

    @Override
    public void updateAssetNotes(AssetNotes assetNotes) {
        getAssetNotesDAO().updateAssetNotes(assetNotes);
    }

    @Override
    public AssetNotes getAssetNotesById(int id) {
        return getAssetNotesDAO().getAssetNotesById(id);
    }

    @Override
    public List<AssetNotes> getAllAssetNotes() {
        return getAssetNotesDAO().getAllAssetNotes();
    }

    @Override
    public List<AssetNotes> getUnsolvedAssetNotes() {
        return getAssetNotesDAO().getUnsolvedAssetNotes();
    }
    
    /**
     * @return the assetNotesDAO
     */
    public AssetNotesDAO getAssetNotesDAO() {
        return assetNotesDAO;
    }

    /**
     * @param assetNotesDAO the assetNotesDAO to set
     */
    public void setAssetNotesDAO(AssetNotesDAO assetNotesDAO) {
        this.assetNotesDAO = assetNotesDAO;
    }
}
