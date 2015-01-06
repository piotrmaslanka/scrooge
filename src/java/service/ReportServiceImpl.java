/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReportDAO;
import java.util.List;
import models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import models.Report;

/**
 *
 * @author Muman
 */
@Transactional
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportDAO reportDAO;
    
    @Override
    public void addReport(Report report) {
        getReportDAO().addReport(report);
    }

    @Override
    public void deleteReport(Report report) {
        getReportDAO().deleteReport(report);
    }

    @Override
    public void updateReport(Report report) {
        getReportDAO().updateReport(report);
    }

    @Override
    public Report getReportById(int id) {
        return getReportDAO().getReportById(id);
    }

    @Override
    public List<Report> getAllRepors() {
        return getReportDAO().getAllRepors();
    }

    /**
     * @return the reportDAO
     */
    public ReportDAO getReportDAO() {
        return reportDAO;
    }

    /**
     * @param reportDAO the reportDAO to set
     */
    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }
    
    @Override
    public List<Report> getReportsByLocation(Location selectedLocation) {
        return getReportDAO().getReportsByLocation(selectedLocation);
    }
}
