/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import models.Users;

/**
 *
 * @author Muman
 */
public class UsersDAOImpl implements UsersDAO{

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
    public void addUser(Users user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(Users user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    @Override
    public void updateUser(Users user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    @Override
    public Users getUserById(int id) {
       List list = getSessionFactory().getCurrentSession().createQuery("from models.Users where id=?").setParameter(0,id).list();
       if (list.size() == 0) return null;
       return (Users)list.get(0);
    }

    @Override
    public Users getUserByLogin(String login) {
       List list = getSessionFactory().getCurrentSession().createQuery("from models.Users where login=?").setParameter(0, login).list();
       if (list.size() == 0) return null;
       return (Users)list.get(0);
    }
    
    @Override
    public List<Users> getAllUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from users").list();
        return list;
    }
}
