/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Voto;

/**
 *
 * @author kassi
 */
public interface VotoDao {
    
    public int insert(Voto voto);
    public int delete(String cpf);
    public int update(Voto voto);
    public Voto findByCpf(String cpf);
    public List<Voto> getVotos();
}
