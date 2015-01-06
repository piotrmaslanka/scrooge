/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ReportItemDAO;
import java.util.List;
import models.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import models.ReportItem;

/**
 *
 * @author Muman
 */
@Transactional
public class ReportItemServiceImpl implements ReportItemService{
    @Autowired
    private ReportItemDAO reportItemDAO;
    
    @Override
    public void addReportItem(ReportItem reportItem) {
        getReportItemDAO().addReportItem(reportItem);
    }

    @Override
    public void deleteReportItem(ReportItem reportItem) {
        getReportItemDAO().deleteReportItem(reportItem);
    }

    @Override
    public void updateReportItem(ReportItem reportItem) {
        getReportItemDAO().updateReportItem(reportItem);
    }

    @Override
    public ReportItem getReportItemById(int id) {
        return getReportItemDAO().getReportItemById(id);
    }

    @Override
    public List<ReportItem> getAllReportItems() {
        return getReportItemDAO().getAllReportItems();
    }

    /**
     * @return the reportItemDAO
     */
    public ReportItemDAO getReportItemDAO() {
        return reportItemDAO;
    }

    /**
     * @param reportItemDAO the reportItemDAO to set
     */
    public void setReportItemDAO(ReportItemDAO reportItemDAO) {
        this.reportItemDAO = reportItemDAO;
    }
    
    @Override
    public List<ReportItem> getReportItemsByReport(Report selectedReport) {
        return getReportItemDAO().getReportItemsByReport(selectedReport);
    }
}
