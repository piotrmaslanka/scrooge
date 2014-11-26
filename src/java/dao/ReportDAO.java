/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import scrooge.models.Report;

/**
 *
 * @author Muman
 */
public interface ReportDAO {
    public void addReport(Report report);
    
    public void deleteReport(Report report);
    
    public void updateReport(Report report);
    
    public Report getReportById(int id);
    
    public List<Report> getAllRepors();
    
}
