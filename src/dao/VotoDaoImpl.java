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
import java.util.Date;
import java.util.List;
import model.Voto;

/**
 *
 * @author kassi
 */
public class VotoDaoImpl implements VotoDao{
    
    private DB db;
    private RestauranteDaoImpl daoRestaurante = new RestauranteDaoImpl();
    
    private static final String TB_VOTOS = "CREATE TABLE IF NOT EXISTS votos (idvotacao int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, cpf varchar(30), idrestaurante int(10), data varchar(50), hora varchar(50))";

    private static final String DELETE = "DELETE FROM votos WHERE cpf=?";
    private static final String FIND_ALL = "SELECT * FROM votos ORDER BY cpf";
    private static final String FIND_BY_CPF = "SELECT * FROM votos WHERE cpf=?";
    private static final String FIND_BY_DATE = "SELECT * FROM votos WHERE data=?";
    private static final String INSERT = "INSERT INTO votos(cpf, idrestaurante, data, hora) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE votos SET cpf=?, idrestaurante=?, data=?, hora=? WHERE cpf=?";
    private static final String FIND_WINNER = "SELECT *, COUNT(idrestaurante) AS matches FROM votacao.votos GROUP BY idvotacao ORDER BY matches DESC LIMIT 1";
    
    public VotoDaoImpl() {
        db = new DB();
        db.createTable(TB_VOTOS);
    }

    /**
     * insere um voto na base de dados
     * @param voto
     * @return o status da operacao
     */
    @Override
    public int insert(Voto voto) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, voto.getCpf());
            stmt.setInt(2, voto.getRestaurante().getIdRestaurante());
            stmt.setString(3, util.Utils.dateToString(voto.getData()));
            stmt.setString(4, util.Utils.timeToString(voto.getHora()));

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                voto.setCpf(rs.getString(1));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }

    /**
     * Deletar um voto buscando pelo cpf
     * @param cpf
     * @return o status da operacao
     */
    @Override
    public int delete(String cpf) {
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, cpf);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }

    /**
     * Atualiza um voto na base de dados
     * @param voto
     * @return o status da operacao
     */
    @Override
    public int update(Voto voto) {
        
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, voto.getRestaurante().getIdRestaurante());
            stmt.setString(2, util.Utils.dateToString(voto.getData()));
            stmt.setString(3, util.Utils.timeToString(voto.getHora()));
            stmt.setString(4, voto.getCpf());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
            db.close(conn);
        }
    }

    /**
     * Busca um voto pelo cpf
     * @param cpf
     * @return voto encontrado
     */
    @Override
    public Voto findByCpf(String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_BY_CPF);
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Voto voto = new Voto();
                voto.setCpf(rs.getString("cpf"));
                //fazer o dao do restaurante para usar o getById
                voto.setRestaurante(daoRestaurante.findById(rs.getInt("idRestaurante")));
                voto.setData(util.Utils.stringToDate(rs.getString("data")));
                voto.setHora(util.Utils.stringToTime(rs.getString("hora")));
                
                return voto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }
    
    public Voto findWinner() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_WINNER);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Voto voto = new Voto();
                voto.setCpf(rs.getString("cpf"));
                //fazer o dao do restaurante para usar o getById
                voto.setRestaurante(daoRestaurante.findById(rs.getInt("idRestaurante")));
                voto.setData(util.Utils.stringToDate(rs.getString("data")));
                voto.setHora(util.Utils.stringToTime(rs.getString("hora")));
                
                return voto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
    }
    
    public Boolean alreadyVoted(String cpf) {
    
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_BY_CPF);
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return util.Utils.compareDates(rs.getString("data"), util.Utils.dateToString(new Date()));
                
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
        }
        
    }

    /**
     * Retorna a lista de votos existentes no banco
     * @return lista de produtos
     */
    @Override
    public List<Voto> getVotos() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Voto> list = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Voto voto = new Voto();
                voto.setCpf(rs.getString("cpf"));
                //fazer o dao do restaurante para usar o getById
                voto.setRestaurante(daoRestaurante.findById(rs.getInt("idRestaurante")));
                voto.setData(util.Utils.stringToDate(rs.getString("data")));
                voto.setHora(util.Utils.stringToTime(rs.getString("hora")));

                list.add(voto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
            db.close(conn);
        }

        return list;
    }
    
    public List<Voto> getVotosDeHoje() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Voto> list = new ArrayList<>();

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(FIND_BY_DATE);
            stmt.setString(1, util.Utils.dateToString(new Date()));
            System.out.println(util.Utils.dateToString(new Date()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Voto voto = new Voto();
                voto.setCpf(rs.getString("cpf"));
                //fazer o dao do restaurante para usar o getById
                voto.setRestaurante(daoRestaurante.findById(rs.getInt("idRestaurante")));
                voto.setData(util.Utils.stringToDate(rs.getString("data")));
                voto.setHora(util.Utils.stringToTime(rs.getString("hora")));

                list.add(voto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(stmt);
            db.close(conn);
        }

        return list;
    }
    
}
