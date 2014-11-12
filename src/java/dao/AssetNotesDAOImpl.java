/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import scrooge.models.AssetNotes;

/**
 *
 * @author Muman
 */
public class AssetNotesDAOImpl implements AssetNotesDAO{
    private SessionFactory sessionFactory;

    @Override
    public void addAssetNotes(AssetNotes assetNotes) {
        getSessionFactory().getCurrentSession().save(assetNotes);
    }

    @Override
    public void deleteAssetNotes(AssetNotes assetNotes) {
        getSessionFactory().getCurrentSession().delete(assetNotes);
    }

    @Override
    public void updateAssetNotes(AssetNotes assetNotes) {
        getSessionFactory().getCurrentSession().update(assetNotes);
    }

    @Override
    public AssetNotes getAssetNotesById(int id) {
       List list = getSessionFactory().getCurrentSession().createQuery("from asset_notes where id=?").setParameter(0,id).list();
       return (AssetNotes)list.get(0);
    }

    @Override
    public List<AssetNotes> getAllAssetNotes() {
        List list = getSessionFactory().getCurrentSession().createQuery("from asset_notes").list();
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
