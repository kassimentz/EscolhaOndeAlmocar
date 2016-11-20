/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author kassi
 */
public class Voto {
    
    private String cpf;
    private Restaurante restaurante;
    private Date data;
    private Date hora;
    
    public Voto() {}

    public String getCpf() {
        return cpf;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Date getData() {
        return data;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voto other = (Voto) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voto{" + "cpf=" + cpf + ", restaurante=" + restaurante + ", data=" + data + '}';
    }
    
}
