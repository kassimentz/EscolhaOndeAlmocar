/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Restaurante;

/**
 *
 * @author kassi
 */
public class RestauranteDaoImpl implements RestauranteDao {
    
    private DB db = new DB();
    
    private static final String TB_RESTAURANTE = "CREATE TABLE IF NOT EXISTS restaurantes (idrestaurante mediumint(9) NOT NULL PRIMARY KEY AUTO_INCREMENT, nome varchar(200), escolhidosemana boolean)";

    private static final String DELETE = "DELETE FROM restaurantes WHERE idrestaurante=?";
    private static final String FIND_ALL = "SELECT * FROM restaurantes ORDER BY idrestaurante";
    private static final String FIND_ALL_CHOSEN = "SELECT * FROM restaurantes WHERE escolhidosemana=?";
    private static final String FIND_BY_ID = "SELECT * FROM restaurantes WHERE idrestaurante=?";
    private static final String FIND_BY_NAME = "SELECT * FROM restaurantes WHERE nome=?";
    private static final String INSERT = "INSERT INTO restaurantes(idrestaurante, nome, escolhidosemana) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE restaurantes SET nome=?, escolhidosemana=? WHERE idrestaurante=?";
    
    public RestauranteDaoImpl() {
        db.createTable(TB_RESTAURANTE);
    }

    /**
     * insere um restaurante na base de dados
     * @param restaurante
     * @return o status da operacao
     */
    @Override
    public int insert(Restaurante restaurante) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, restaurante.getIdRestaurante());
            stmt.setString(2, restaurante.getNome());
            stmt.setBoolean(3, restaurante.isEscolhidoSemana());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                restaurante.setIdRestaurante(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }

    /**
     * Deletar um restaurante buscando pelo cpf
     * @param cpf
     * @return o status da operacao
     */
    @Override
    public int delete(int idRestaurante) {
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, idRestaurante);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }

    /**
     * Atualiza um restaurante na base de dados
     * @param restaurante
     * @return o status da operacao
     */
    @Override
    public int update(Restaurante restaurante) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, restaurante.getNome());
            stmt.setBoolean(2, restaurante.isEscolhidoSemana());
            stmt.setInt(3, restaurante.getIdRestaurante());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
            db.close(conn);
        }
    }

     /**
     * Busca um restaurante pelo id
     * @param idRestaurante
     * @return restaurante encontrado
     */
    @Override
    public Restaurante findById(int idRestaurante) {
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, idRestaurante);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setIdRestaurante(rs.getInt("idrestaurante"));
                restaurante.setNome(rs.getString("nome"));
                restaurante.setEscolhidoSemana(rs.getBoolean("escolhidosemana"));
                
                return restaurante;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }
    
    
    public Restaurante findByName(String nome) {
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setIdRestaurante(rs.getInt("idrestaurante"));
                restaurante.setNome(rs.getString("nome"));
                restaurante.setEscolhidoSemana(rs.getBoolean("escolhidosemana"));
                
                return restaurante;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }

    /**
     * Retorna a lista de restaurantes existentes no banco
     * @return lista de produtos
     */
    @Override
    public List<Restaurante> getRestaurantes() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Restaurante> list = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Restaurante restaurante = new Restaurante();
                restaurante.setIdRestaurante(rs.getInt("idrestaurante"));
                restaurante.setNome(rs.getString("nome"));
                restaurante.setEscolhidoSemana(rs.getBoolean("escolhidosemana"));

                list.add(restaurante);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
            db.close(conn);
        }

        return list;
    }

    public List<Restaurante> ChosenRestaurants() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Restaurante> list = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_ALL_CHOSEN);
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Restaurante restaurante = new Restaurante();
                restaurante.setIdRestaurante(rs.getInt("idrestaurante"));
                restaurante.setNome(rs.getString("nome"));
                restaurante.setEscolhidoSemana(rs.getBoolean("escolhidosemana"));

                list.add(restaurante);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
            db.close(conn);
        }

        return list;
    }

    public void resetChosen() {
        List<Restaurante> restaurantesEscolhidos = ChosenRestaurants();
        for (Restaurante restaurantesEscolhido : restaurantesEscolhidos) {
            restaurantesEscolhido.setEscolhidoSemana(false);
            update(restaurantesEscolhido);
        }
    }

    
    
    
}
