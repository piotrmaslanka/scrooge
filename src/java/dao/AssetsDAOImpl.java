/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import models.Assets;

/**
 *
 * @author Muman
 */
public class AssetsDAOImpl implements AssetsDAO{
    
    private SessionFactory sessionFactory;

    @Override
    public void addAsset(Assets asset) {
        getSessionFactory().getCurrentSession().save(asset);
    }

    @Override
    public void deleteAsset(Assets asset) {
        getSessionFactory().getCurrentSession().delete(asset);
    }

    @Override
    public void updateAsset(Assets asset) {
        getSessionFactory().getCurrentSession().update(asset);
    }

    @Override
    public Assets getAssetById(int id) {
       List list = getSessionFactory().getCurrentSession().createQuery("from assets where id=?").setParameter(0,id).list();
       return (Assets)list.get(0);
    }

    @Override
    public List<Assets> getAllAssets() {
        List list = getSessionFactory().getCurrentSession().createQuery("from assets").list();
        return list;
    }
    
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
}
