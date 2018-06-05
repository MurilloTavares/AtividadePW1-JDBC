package controle;

import interfaces.InterfaceDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Pedido;

public class PedidoDAO implements InterfaceDAO<Pedido> {

    Conexao conexao;
    Connection connection;

    public PedidoDAO() {
        conexao = new Conexao();
        connection = conexao.getConn();
    }

    public PedidoDAO(String url, String user, String pass) {
        conexao = new Conexao(url, user, pass);
        connection = conexao.getConn();
    }

    @Override
    public void incluir(Pedido p) throws SQLException {
        String sql = "INSERT INTO PEDIDO(ID, Data, Cliente, valor) Values(?,?,?,?)";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt  (1, p.getId());
            stat.setDate (2, p.getData());
            stat.setInt  (3, p.getCliente());
            stat.setFloat(4, p.getValor());
            
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void alterar(Pedido p, Pedido novo) throws SQLException {
        String sql = "UPDATE Pedido SET Id = ?, Data = ?, Cliente = ?, Valor = ?"
                   + "WHERE Id = ? ";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt  (1, novo.getId());
            stat.setDate (2, novo.getData());
            stat.setInt  (3, novo.getCliente());
            stat.setFloat(4, novo.getValor());
            
            stat.setInt(5, p.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public void alterar(int id, Pedido novo) throws SQLException {
        String sql = "UPDATE Pedido SET Id = ?, Data = ?, Cliente = ?, Valor = ?"
                   + "WHERE Id = ? ";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt  (1, novo.getId());
            stat.setDate (2, novo.getData());
            stat.setInt  (3, novo.getCliente());
            stat.setFloat(4, novo.getValor());
            
            stat.setInt(5, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void deletar(Pedido p) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE Id = ? and Data = ? and Cliente = ? and Valor = ?";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt  (1, p.getId());
            stat.setDate (2, p.getData());
            stat.setInt  (3, p.getCliente());
            stat.setFloat(4, p.getValor());
            
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE Id = ?";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void listar() throws SQLException {
        String sql = "SELECT * FROM Pedido";
        
        try (Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                Date data = rs.getDate("Data");
                int cliente = rs.getInt("Cliente");
                float valor = rs.getFloat("Valor");

                Pedido pedido = new Pedido(id, data, cliente, valor);
                System.out.println(pedido);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

}
