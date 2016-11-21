/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votacao;


import dao.RestauranteDaoImpl;
import dao.VotoDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Restaurante;
import org.junit.*;
import service.VotoService;

/**
 *
 * @author kassi
 */
public class VotacaoTest {
    
    RestauranteDaoImpl restauranteDao;
    VotoService votoService = null;
    VotoDaoImpl votacaoDao = null;
    
    @Before
    public void inicializa(){
        restauranteDao = new RestauranteDaoImpl();
        votoService = new VotoService();
        votacaoDao = new VotoDaoImpl();
    }
    
    @Test
    public void testInicializacaoRestauranteDaoImpl () {
        Assert.assertNotEquals(this.restauranteDao, null);
    }
    
    @Test
    public void testInicializacaoVotoService() {
        Assert.assertNotEquals(this.votoService, null);
    }
    
    @Test
    public void testInicializacaoVotoDaoImpl() {
        Assert.assertNotEquals(this.votacaoDao, null);
    }
    
    @Test
    public void testVerificaQuantidadeRestaurantesEscolhidos(){
        int nroRestaurantesEscolhidos = restauranteDao.ChosenRestaurants().size();
        if(nroRestaurantesEscolhidos >= 5) {
            System.out.println("resetar restaurantes");
        }
    }
    
    @Test
    public void testVerificaTerminoVotacao() {
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
            System.out.println("nao terminou");
        } else {
            System.out.println("terminou");
        }
    }
    
    @Test
    public void verificaRetornoVencedor() {
        Restaurante vencedor = votacaoDao.findWinner().getRestaurante();
        Assert.assertNotEquals(vencedor, null);
    }
    
}
