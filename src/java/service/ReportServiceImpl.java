/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReportDAO;
import java.util.List;
import scrooge.models.Report;

/**
 *
 * @author Muman
 */
public class ReportServiceImpl implements ReportService{

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
}
