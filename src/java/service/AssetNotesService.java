/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import models.AssetNotes;

/**
 *
 * @author Muman
 */
public interface AssetNotesService {
    public void addAssetNotes(AssetNotes assetNotes);
    
    public void deleteAssetNotes(AssetNotes assetNotes);
    
    public void updateAssetNotes(AssetNotes assetNotes);
    
    public AssetNotes getAssetNotesById(int id);
    
    public List<AssetNotes> getAllAssetNotes();

    public List<AssetNotes> getUnsolvedAssetNotes();


}

