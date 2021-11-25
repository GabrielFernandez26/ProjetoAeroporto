package src.com.example.projetoaeroporto.DAO;

import src.com.example.projetoaeroporto.entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImplements implements ClienteDAO {

    @Override
    public void salvar(Cliente c) {
        Connection con = Context.getConnection();
        try {
            String sql = "INSERT INTO cliente (id, nome, datanascimento, cpf) " +
                    "VALUES (?, ?, ?, ?)";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.setString(1,  c.getNome());
            stmt.setDate(1,  java.sql.Date.valueOf(c.getNascimento()));
            stmt.setInt(1, c.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Context.closeConnection(con);
        }
    }

    @Override
    public List<Cliente> pesquisarPorId(Integer id) {
        List<Cliente> lista = new ArrayList<Cliente>();

        Connection con = Context.getConnection();

        try {
            String sql = "SELECT * FROM cliente WHERE id like ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + id + "%");
            ResultSet rs = stmt.executeQuery();

            while( rs.next() ) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id do cliente"));
                c.setNome(rs.getString("nome do cliente"));
                c.setNascimento(rs.getDate("nascimento").toLocalDate());
                c.setCpf(rs.getInt("cpf do cliente"));
                lista.add(c);
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

        Connection con = Context.getConnection();

        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            Context.closeConnection(con);
        }
    }


    @Override
    public void atualizar(int id, Cliente c) {

        Connection con = Context.getConnection();

        try {
            String sql = "UPDATE cliente SET id = ?, nome = ?, datanascimento = ?, cpf =? WHERE id = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getNome());
            stmt.setDate(3, java.sql.Date.valueOf(c.getNascimento()));
            stmt.setInt(3, c.getCpf());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Context.closeConnection(con);
        }
    }
}
