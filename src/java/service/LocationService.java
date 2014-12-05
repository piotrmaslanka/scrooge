/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import models.Location;

/**
 *
 * @author Muman
 */
public interface LocationService {
    public void addLocation(Location location);
    
    public void deleteLocation(Location location);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(String id);
    
    public List<Location> getAllLocations();
}
