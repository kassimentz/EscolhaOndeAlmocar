/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Restaurante;

/**
 *
 * @author kassi
 */
public interface RestauranteDao {
    
    public int insert(Restaurante restaurante);
    public int delete(int idRestaurante);
    public int update(Restaurante restaurante);
    public Restaurante findById(int idRestaurante);
    public List<Restaurante> getRestaurantes();
}
