/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author kassi
 */
public class Restaurante {
    
    private int idRestaurante;
    private String nome;
    private boolean escolhidoSemana;
    
    public Restaurante() {}

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEscolhidoSemana() {
        return escolhidoSemana;
    }

    public void setEscolhidoSemana(boolean escolhidoSemana) {
        this.escolhidoSemana = escolhidoSemana;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idRestaurante;
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
        final Restaurante other = (Restaurante) obj;
        if (this.idRestaurante != other.idRestaurante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "idRestaurante=" + idRestaurante + ", nome=" + nome + ", escolhidoSemana=" + escolhidoSemana + '}';
    }
    
}
