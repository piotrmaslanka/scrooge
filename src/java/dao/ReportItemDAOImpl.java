/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import models.ReportItem;

/**
 *
 * @author Muman
 */
public class ReportItemDAOImpl implements ReportItemDAO{

    private SessionFactory sessionFactory;

     /**
     * @return the SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param SessionFactory the SessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void addReportItem(ReportItem reportItem) {
        getSessionFactory().getCurrentSession().save(reportItem);
    }

    @Override
    public void deleteReportItem(ReportItem reportItem) {
        getSessionFactory().getCurrentSession().delete(reportItem);
    }

    @Override
    public void updateReportItem(ReportItem reportItem) {
        getSessionFactory().getCurrentSession().update(reportItem);
    }

    @Override
    public ReportItem getReportItemById(int id) {
       List list = getSessionFactory().getCurrentSession().createQuery("from report_item where id=?").setParameter(0,id).list();
       return (ReportItem)list.get(0);
    }

    @Override
    public List<ReportItem> getAllReportItems() {
        List list = getSessionFactory().getCurrentSession().createQuery("from report_item").list();
        return list;
    }
    
}
