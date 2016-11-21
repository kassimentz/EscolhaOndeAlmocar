/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VotoDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kassi
 */
public class VotoService {

    public VotoService(){

    }

    public boolean verificaTerminoVotacao() {
        /*
		 * configurei o termino da votacao para 11:30. a partir deste horario os
		 * votos contam para o proximo dia
         */

        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Date horaMinima = null;
        Date horaMaxima = null;
        Date horaAtualFormatada = null;
        Date horaAtual = new Date();
        String horaAtualString = formatador.format(horaAtual);
        try {
            horaMinima = formatador.parse("7:00");
            horaMaxima = formatador.parse("11:30");
            horaAtualFormatada = formatador.parse(horaAtualString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        if (horaAtualFormatada.getTime() > horaMinima.getTime() && horaAtualFormatada.getTime() < horaMaxima.getTime()) {
            return false;
        } else {
            return true;
        }
    }


}
