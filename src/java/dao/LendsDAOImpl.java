/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import models.Lends;

/**
 *
 * @author Muman
 */
public class LendsDAOImpl implements LendsDAO{

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
    public void addLend(Lends lend) {
        getSessionFactory().getCurrentSession().save(lend);
    }

    @Override
    public void deleteLend(Lends lend) {
        getSessionFactory().getCurrentSession().delete(lend);
    }

    @Override
    public void updateLend(Lends lend) {
        getSessionFactory().getCurrentSession().update(lend);
    }

    @Override
    public Lends getLendById(int id) {
       List list = getSessionFactory().getCurrentSession().createQuery("from models.Lends where id=?").setParameter(0,id).list();
       return (Lends)list.get(0);
    }

    @Override
    public List<Lends> getAllLends() {
        List list = getSessionFactory().getCurrentSession().createQuery("from models.Lends").list();
        return list;
    }

}
