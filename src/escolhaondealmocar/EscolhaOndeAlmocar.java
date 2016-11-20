/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escolhaondealmocar;

import dao.DB;
import telas.frmVotacao;

/**
 *
 * @author kassi
 */
public class EscolhaOndeAlmocar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DB dao = new DB();
        dao.iniciaDB();
        
        frmVotacao telaVoto = new frmVotacao();
        telaVoto.setVisible(true);
    }
    
}
