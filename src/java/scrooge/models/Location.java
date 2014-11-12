package scrooge.models;
// Generated Nov 12, 2014 12:25:14 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Location generated by hbm2java
 */
public class Location  implements java.io.Serializable {


     private String id;
     private Set<Report> reports = new HashSet<Report>(0);
     private Set<Assets> assetses = new HashSet<Assets>(0);

    public Location() {
    }

	
    public Location(String id) {
        this.id = id;
    }
    public Location(String id, Set<Report> reports, Set<Assets> assetses) {
       this.id = id;
       this.reports = reports;
       this.assetses = assetses;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public Set<Report> getReports() {
        return this.reports;
    }
    
    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }
    public Set<Assets> getAssetses() {
        return this.assetses;
    }
    
    public void setAssetses(Set<Assets> assetses) {
        this.assetses = assetses;
    }




}


