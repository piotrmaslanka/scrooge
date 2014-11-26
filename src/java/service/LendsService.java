/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import scrooge.models.Lends;

/**
 *
 * @author Muman
 */
public interface LendsService {
    public void addLend(Lends lend);
    
    public void deleteLend(Lends lend);
    
    public void updateLend(Lends lend);
    
    public Lends getLendById(int id);
    
    public List<Lends> getAllLends();
}
