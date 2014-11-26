/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.AssetNotes;

/**
 *
 * @author Muman
 */
public interface AssetNotesDAO {
    public void addAssetNotes(AssetNotes assetNotes);
    
    public void deleteAssetNotes(AssetNotes assetNotes);
    
    public void updateAssetNotes(AssetNotes assetNotes);
    
    public AssetNotes getAssetNotesById(int id);
    
    public List<AssetNotes> getAllAssetNotes();
    
}
