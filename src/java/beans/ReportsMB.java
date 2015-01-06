/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import models.Assets;
import models.Location;
import models.Report;
import models.ReportItem;
import service.LocationService;
import service.ReportItemService;
import service.ReportService;

/**
 *
 * @author Muman
 */
@ManagedBean(name = "reportsMB")
@ViewScoped
public class ReportsMB implements Serializable{
    
    @ManagedProperty(value = "#{locationServiceImpl}")
    private LocationService locationService;
    
    @ManagedProperty(value = "#{reportServiceImpl}")
    private ReportService reportService;
    
    @ManagedProperty(value = "#{reportItemServiceImpl}")  
    private ReportItemService reportItemService;
    
    private Report selectedReport;
    
    private Location selectedLocation;
    
    private List<Location> Locations;
    
    private List<Report> reports;
    
    private List<ReportItem> reportsItems;
    
    private List<Report> reportsForSelecetedLocation;
    
    private List<ReportItem> reportItemsForSelectedReport;
    

    public ReportsMB() {
        reports = new ArrayList<>();
        reportsItems =  new ArrayList<>();
        Locations =  new ArrayList<>();
        reportItemsForSelectedReport = new ArrayList<>();
    }
    
    @PostConstruct
    public void init(){
        setLocations(locationService.getAllLocations());  
    }
    
    /**
     * @return the locationService
     */
    public LocationService getLocationService() {
        return locationService;
    }

    /**
     * @param locationService the locationService to set
     */
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * @return the reportService
     */
    public ReportService getReportService() {
        return reportService;
    }

    /**
     * @param reportService the reportService to set
     */
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * @return the reportItemService
     */
    public ReportItemService getReportItemService() {
        return reportItemService;
    }

    /**
     * @param reportItemService the reportItemService to set
     */
    public void setReportItemService(ReportItemService reportItemService) {
        this.reportItemService = reportItemService;
    }

    /**
     * @return the selectedReport
     */
    public Report getSelectedReport() {
        return selectedReport;
    }

    /**
     * @param selectedReport the selectedReport to set
     */
    public void setSelectedReport(Report selectedReport) {
        this.selectedReport = selectedReport;
    }

    /**
     * @return the selectedLocation
     */
    public Location getSelectedLocation() {
        return selectedLocation;
    }

    /**
     * @param selectedLocation the selectedLocation to set
     */
    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    /**
     * @return the Locations
     */
    public List<Location> getLocations() {
        return Locations;
    }

    /**
     * @param Locations the Locations to set
     */
    public void setLocations(List<Location> Locations) {
        this.Locations = Locations;
    }

    /**
     * @return the reports
     */
    public List<Report> getReports() {
        return reports;
    }

    /**
     * @param reports the reports to set
     */
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    /**
     * @return the reportsItems
     */
    public List<ReportItem> getReportsItems() {
        return reportsItems;
    }

    /**
     * @param reportsItems the reportsItems to set
     */
    public void setReportsItems(List<ReportItem> reportsItems) {
        this.reportsItems = reportsItems;
    }
    
    public void findReportsForSelectedLocation(){
        if(null != this.selectedLocation){
           List<Report> reportsForLocation = this.reportService.getReportsByLocation(this.selectedLocation);
           this.setReportsForSelecetedLocation(reportsForLocation);
           setReportItemsForSelectedReport(null); // clear selected report details
        }
    }
    
    public void findReportItemsForSelectedReport(){
        if(null != this.selectedReport){
           List<ReportItem> reportItemsForReport = this.reportItemService.getReportItemsByReport(this.selectedReport);
           this.setReportItemsForSelectedReport(reportItemsForReport);
        }
    }

    /**
     * @return the reportsForSelecetedLocation
     */
    public List<Report> getReportsForSelecetedLocation() {
        return reportsForSelecetedLocation;
    }

    /**
     * @param reportsForSelecetedLocation the reportsForSelecetedLocation to set
     */
    public void setReportsForSelecetedLocation(List<Report> reportsForSelecetedLocation) {
        this.reportsForSelecetedLocation = reportsForSelecetedLocation;
    }

    /**
     * @return the reportItemsForSelectedReport
     */
    public List<ReportItem> getReportItemsForSelectedReport() {
        return reportItemsForSelectedReport;
    }

    /**
     * @param reportItemsForSelectedReport the reportItemsForSelectedReport to set
     */
    public void setReportItemsForSelectedReport(List<ReportItem> reportItemsForSelectedReport) {
        this.reportItemsForSelectedReport = reportItemsForSelectedReport;
    }
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        if(null == selectedReport){
            return;
        }
        
        Document pdf = (Document) document;
        String documentTitle = getPDFraportTitle(selectedReport);
        pdf.addTitle(documentTitle);
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        pdf.add(new Phrase(documentTitle));
        
    }
    
    private String getPDFraportTitle(Report report){
        if(null != report){
            
            StringBuilder sb = new StringBuilder();
            String title = sb.append("Raport przedmiotów znajdujących się w sali ").append(report.getLocation().getId())
                    .append(" \nWykonany dnia: ").append(report.getWhenDone().toString()).toString();
            return title;
        }
        return "";
    }
}
