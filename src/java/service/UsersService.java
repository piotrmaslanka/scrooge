/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import models.Users;

/**
 *
 * @author Muman
 */
public interface UsersService {
    public void addUser(Users user);
    
    public void deleteUser(Users user);
    
    public void updateUser(Users user);
    
    public Users getUserById(int id);
    
    public Users getUserByLogin(String login);
    
    public List<Users> getAllUsers();
}
