package com.example.projetoaeroporto.DAO;

import com.example.projetoaeroporto.entity.Voo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VooDAOImplements implements VooDAO{

    @Override
    public void salvar(Voo v) {

        Connection con = Context.getConnection();
        try {
            
            String sql = "INSERT INTO voo (origem, destino, decolagem, pouso, preco) " +
                    "VALUES (?, ?, ?, ?, ?)";
                    
            System.out.println(sql);
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, v.getOrigem());
            stmt.setString(2, v.getDestino());
            stmt.setDate(3, java.sql.Date.valueOf(v.getDecolagem().toLocalDate()));
            stmt.setDate(4, java.sql.Date.valueOf(v.getDecolagem().toLocalDate()));
            stmt.setDouble(5, v.getPreco());

            stmt.executeUpdate();
            
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Voo> pesquisarPorId(Integer id) {
        List<Voo> lista = new ArrayList<>();

        Connection con = Context.getConnection();

        try {
            
            String sql = "SELECT * FROM voo WHERE id = ?";

            System.out.println(sql);

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id );

            ResultSet rs = stmt.executeQuery();

            while( rs.next() ) {
                Voo v = new Voo();
                v.setId(rs.getInt("id"));
                v.setOrigem(rs.getString("Origem"));
                v.setDestino(rs.getString("Destino"));

                LocalDate ads = rs.getDate("Decolagem").toLocalDate();
                v.setDecolagem(LocalDateTime.of(ads, null));

                LocalDate ada = rs.getDate("pouso").toLocalDate();
                v.setPouso(LocalDateTime.of(ada, null));

                v.setPreco(rs.getDouble("Preço"));

                v.setAssentosDisponiveis(rs.getInt("assentos"));

                lista.add(v);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Context.closeConnection(con);
        }
        return lista;
    }

    @Override
    public void remover(int id) {
        try {
            Connection con = Context.getConnection();
            String sql = "DELETE FROM voo WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(int id, Voo v) {
        try {
            Connection con = Context.getConnection();
            String sql = "UPDATE pet SET id = ?, origem = ?, destino = ?, decolagem = ?, preco=? WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, v.getId());
            stmt.setString(2, v.getOrigem());
            stmt.setString(3, v.getDestino());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(v.getDecolagem()));
            stmt.setDouble(5, v.getPreco());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
