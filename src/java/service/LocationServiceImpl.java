/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.LocationDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import models.Location;

/**
 *
 * @author Muman
 */
@Transactional
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationDAO locationDAO;
    
    @Override
    public void addLocation(Location location) {
        getLocationDAO().addLocation(location);
    }

    @Override
    public void deleteLocation(Location location) {
        getLocationDAO().deleteLocation(location);
    }

    @Override
    public void updateLocation(Location location) {
        getLocationDAO().updateLocation(location);
    }

    @Override
    public Location getLocationById(int id) {
        return getLocationDAO().getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return getLocationDAO().getAllLocations();
    }

    /**
     * @return the locationDAO
     */
    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    /**
     * @param locationDAO the locationDAO to set
     */
    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }
    
}
