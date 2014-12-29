/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Report;
import models.ReportItem;

/**
 *
 * @author Muman
 */
public interface ReportItemDAO {
    
    public void addReportItem(ReportItem reportItem);
    
    public void deleteReportItem(ReportItem reportItem);
    
    public void updateReportItem(ReportItem reportItem);
    
    public ReportItem getReportItemById(int id);
    
    public List<ReportItem> getAllReportItems();
    
    public List<ReportItem> getReportItemsByReport(Report report);
}
