/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UsersDAO;
import java.util.List;
import scrooge.models.Users;

/**
 *
 * @author Muman
 */
public class UsersServiceImpl implements UsersService{

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
        this.usersDAO = usersDAO;
    }
    
}
