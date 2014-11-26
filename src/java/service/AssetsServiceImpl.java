/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AssetsDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import scrooge.models.Assets;

/**
 *
 * @author Muman
 */
@Transactional
public class AssetsServiceImpl implements AssetsService{
    @Autowired
    private AssetsDAO assetDAO;
    
    @Override
    public void addAsset(Assets assets) {
        getAssetDAO().addAsset(assets);
    }

    @Override
    public void deleteAsset(Assets assets) {
        getAssetDAO().deleteAsset(assets);
    }

    @Override
    public void updateAsset(Assets assets) {
        getAssetDAO().updateAsset(assets);
    }

    @Override
    public Assets getAssetById(int id) {
        return getAssetDAO().getAssetById(id);
    }

    @Override
    public List<Assets> getAllAssets() {
        return getAssetDAO().getAllAssets();
    }

    /**
     * @return the assetDAO
     */
    public AssetsDAO getAssetDAO() {
        return assetDAO;
    }

    /**
     * @param assetDAO the assetDAO to set
     */
    public void setAssetDAO(AssetsDAO assetDAO) {
        this.assetDAO = assetDAO;
    }
}
