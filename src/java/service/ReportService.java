/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import models.Location;
import models.Report;

/**
 *
 * @author Muman
 */
public interface ReportService {
    public void addReport(Report report);
    
    public void deleteReport(Report report);
    
    public void updateReport(Report report);
    
    public Report getReportById(int id);
    
    public List<Report> getAllRepors();
    
    public List<Report> getReportsByLocation(Location selectedLocation);
}
