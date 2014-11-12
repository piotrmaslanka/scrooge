/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import scrooge.models.Location;


/**
 *
 * @author Muman
 */
public interface LocationDAO {
    public void addLocation(Location location);
    
    public void deleteLocation(Location location);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int id);
    
    public List<Location> getAllLocations();
     
}
