/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import scrooge.models.Users;

/**
 *
 * @author Muman
 */
public interface UsersDAO {
    public void addUser(Users user);
    
    public void deleteUser(Users user);
    
    public void updateUser(Users user);
    
    public Users getUserById(int id);
    
    public List<Users> getAllUsers();
    
}
