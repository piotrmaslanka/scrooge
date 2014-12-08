/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Assets;
import models.Location;

/**
 *
 * @author Muman
 */
public interface AssetsDAO {

    public void addAsset(Assets assets);

    public void deleteAsset(Assets assets);

    public void updateAsset(Assets assets);

    public Assets getAssetById(String id);

    public List<Assets> getAssetsByLocation(Location location);
    
    public List<Assets> getAllAssets();

}
