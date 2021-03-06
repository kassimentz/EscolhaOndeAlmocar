/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.RestauranteDaoImpl;
import dao.VotoDaoImpl;
import model.Voto;
import java.awt.Color;
import java.text.ParseException;
import model.Restaurante;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;
import service.VotoService;

/**
 *
 * @author kassi
 */
public class frmVotacao extends javax.swing.JFrame {

    RestauranteDaoImpl restauranteDao = new RestauranteDaoImpl();
    VotoService votoService = new VotoService();
    VotoDaoImpl votacaoDao = new VotoDaoImpl();
    List<Restaurante> restaurantes = new ArrayList<>();
    List<Voto> votos = new ArrayList<>();

    /**
     * Creates new form frmVotacao
     */
    public frmVotacao() {
        restaurantes = restauranteDao.getRestaurantes();
        initComponents();
        setaMascara();
        populaCombobox();
        verificaQuantidadeRestaurantesEscolhidos();
        verificaVencedor();
        lblCpfObrigatorio.setVisible(false);
        lblRestauranteObrigatorio.setVisible(false);
        lblJaVotou.setVisible(false);
        lblTotalVotos.setText(String.valueOf(votacaoDao.getVotosDeHoje().size()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbRestaurante = new javax.swing.JComboBox<>();
        btnVotar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblTotalVotos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRestauranteEscolhido = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        lblCpfObrigatorio = new javax.swing.JLabel();
        lblRestauranteObrigatorio = new javax.swing.JLabel();
        lblJaVotou = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CPF:");

        jLabel2.setText("Restaurante: ");

        cbRestaurante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnVotar.setText("Votar");
        btnVotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotarActionPerformed(evt);
            }
        });

        jLabel3.setText("Total de votos:");

        jLabel4.setText("Restaurante Escolhido:");

        lblCpfObrigatorio.setText("* Campo Obrigatório");

        lblRestauranteObrigatorio.setText("* Campo Obrigatório");

        lblJaVotou.setText("VOCÊ JÁ VOTOU HOJE. VOLTE AMANHÃ PARA VOTAR NOVAMENTE.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRestauranteObrigatorio))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJaVotou)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotalVotos)
                                    .addComponent(lblRestauranteEscolhido))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(61, 61, 61)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(lblCpfObrigatorio)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnVotar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblCpfObrigatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(lblJaVotou)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRestauranteObrigatorio))
                .addGap(18, 18, 18)
                .addComponent(btnVotar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotalVotos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblRestauranteEscolhido))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotarActionPerformed
        // TODO add your handling code here:
        String cpf = "";

        Restaurante r = restauranteDao.findByName(cbRestaurante.getSelectedItem().toString());
        System.out.println(r.getNome());

        cpf = txtCpf.getText();
        if (cpf.equalsIgnoreCase("   .   .   -   ")) {
            txtCpf.setBackground(Color.ORANGE);
            lblCpfObrigatorio.setVisible(true);
        } else {
            if (!r.isEscolhidoSemana()) {
                lblCpfObrigatorio.setVisible(false);
                lblRestauranteObrigatorio.setVisible(false);
                System.out.println(votacaoDao.alreadyVoted(cpf));
                if (!votacaoDao.alreadyVoted(cpf)) {
                    if (!votoService.verificaTerminoVotacao()) {
                        txtCpf.setBackground(Color.WHITE);
                        Voto voto = new Voto();
                        voto.setCpf(cpf);
                        voto.setData(new Date());
                        voto.setHora(new Date());
                        voto.setRestaurante(r);
                        votacaoDao.insert(voto);
                        lblTotalVotos.setText(String.valueOf(votacaoDao.getVotosDeHoje().size()));
                        System.out.println("Voto salvo com sucesso.");
                        txtCpf.setText("");
                    } else {
                        lblJaVotou.setVisible(true);
                        txtCpf.setBackground(Color.WHITE);
                        lblJaVotou.setText("A votacão de hoje já está encerrada. Volte amanhhã.");
                        txtCpf.setText("");
                    }

                } else {
                    lblJaVotou.setVisible(true);
                    txtCpf.setBackground(Color.WHITE);
                    lblJaVotou.setText("VOCÊ JÁ VOTOU HOJE. VOLTE AMANHÃ PARA VOTAR NOVAMENTE.");
                    txtCpf.setText("");
                }
            } else {
                lblRestauranteObrigatorio.setText("Este restaurante já foi escolhido esta semana. Escolha outro.");
                lblRestauranteObrigatorio.setVisible(true);
            }
        }


    }//GEN-LAST:event_btnVotarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVotacao().setVisible(true);

            }

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVotar;
    private javax.swing.JComboBox<String> cbRestaurante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblCpfObrigatorio;
    private javax.swing.JLabel lblJaVotou;
    private javax.swing.JLabel lblRestauranteEscolhido;
    private javax.swing.JLabel lblRestauranteObrigatorio;
    private javax.swing.JLabel lblTotalVotos;
    private javax.swing.JFormattedTextField txtCpf;
    // End of variables declaration//GEN-END:variables

    private void populaCombobox() {
        cbRestaurante.removeAllItems();
        for (Restaurante restaurante : restaurantes) {
            cbRestaurante.addItem(restaurante.getNome());
        }
    }

    private void setaMascara() {

        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("###.###.###-###");
        } catch (ParseException ex) {
            Logger.getLogger(frmVotacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mask.install(txtCpf);

    }

    /**
     * Se eu tiver 5 restaurantes setados como escolhidos, significa que aquela semana
     * acabou e devo setar todos os restaurantes para false, iniciando uma nova semana
     */
    private void verificaQuantidadeRestaurantesEscolhidos() {
        int nroRestaurantesEscolhidos = restauranteDao.ChosenRestaurants().size();
        if(nroRestaurantesEscolhidos >= 5) {
            restauranteDao.resetChosen();
        }
    }

    private void verificaVencedor() {
        if(votoService.verificaTerminoVotacao()){

            Restaurante vencedor = votacaoDao.findWinner().getRestaurante();
            lblRestauranteEscolhido.setText(vencedor.getNome());
            vencedor.setEscolhidoSemana(true);
            restauranteDao.update(vencedor);

        } else {
            lblRestauranteEscolhido.setText("A votação de hoje ainda não está encerrada");
        }
    }
}
