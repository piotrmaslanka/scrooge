/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import scrooge.models.Assets;

/**
 *
 * @author Muman
 */
public interface AssetsService {
    
    public void addAsset(Assets assets);
    
    public void deleteAsset(Assets assets);
    
    public void updateAsset(Assets assets);
    
    public Assets getAssetById(int id);
    
    public List<Assets> getAllAssets();
        
}
