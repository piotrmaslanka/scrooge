/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UsersDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import scrooge.models.Users;

/**
 *
 * @author Muman
 */
@Transactional
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersDAO usersDAO;
    

    @Override
    public void addUser(Users user) {
        getUsersDAO().addUser(user);
    }

    @Override
    public void deleteUser(Users user) {
        getUsersDAO().deleteUser(user);
    }

    @Override
    public void updateUser(Users user) {
        getUsersDAO().updateUser(user);          
    }

    @Override
    public Users getUserById(int id) {
        return getUsersDAO().getUserById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return getUsersDAO().getAllUsers();
    }

    /**
     * @return the usersDAO
     */
    public UsersDAO getUsersDAO() {
        return usersDAO;
    }

    /**
     * @param usersDAO the usersDAO to set
     */
    public void setUsersDAO(UsersDAO usersDAO) {
        System.out.println(usersDAO);
        this.usersDAO = usersDAO;
    }
    
}
