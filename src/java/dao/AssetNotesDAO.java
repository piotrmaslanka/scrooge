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
    public List<AssetNotes> getUnsolvedAssetNotes();
    
}
