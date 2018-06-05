package controle;

import Filters.SaldoFilter;
import interfaces.InterfaceDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import modelo.Cliente;

public class ClienteDAO implements InterfaceDAO<Cliente> {
    
    Conexao conexao;
    Connection connection;
    
    public ClienteDAO(){
        conexao = new Conexao();
        connection = conexao.getConn();
    }
    
    public ClienteDAO(String url, String user, String pass){
        conexao = new Conexao(url, user, pass);
        connection = conexao.getConn();
    }
    
    @Override
    public void incluir(Cliente c) throws SQLException {
        String sql = "INSERT INTO Cliente(ID, Nome, Documento, Saldo, Ativo, Imagem)"
                   + "Values(?,?,?,?,?,?)";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt     (1, c.getId());
            stat.setString  (2, c.getNome());
            stat.setString  (3, c.getDocumento());
            stat.setFloat   (4, c.getSaldo());
            stat.setBoolean (5, c.isAtivo());
            stat.setString  (6, c.getImgPath());
            
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void alterar(Cliente c, Cliente novo) throws SQLException {
        String sql = "UPDATE Cliente SET Id = ?, Nome = ?, Documento = ?, Saldo = ?, Ativo = ?, Imagem = ? "
                   + "WHERE Id = ? ";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt     (1, novo.getId());
            stat.setString  (2, novo.getNome());
            stat.setString  (3, novo.getDocumento());
            stat.setFloat   (4, novo.getSaldo());
            stat.setBoolean (5, novo.isAtivo());
            stat.setString  (6, novo.getImgPath());
            
            stat.setInt(7, c.getId());
            stat.executeUpdate();
        } catch (SQLException ex ) {
            throw new SQLException(ex);
        }
    }
    
    public void alterar(int id, Cliente novo) throws SQLException {
        String sql = "UPDATE Cliente SET Id = ?, Nome = ?, Documento = ?, Saldo = ?, Ativo = ?, Imagem = ? "
                   + "WHERE Id = ? ";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt     (1, novo.getId());
            stat.setString  (2, novo.getNome());
            stat.setString  (3, novo.getDocumento());
            stat.setFloat   (4, novo.getSaldo());
            stat.setBoolean (5, novo.isAtivo());
            stat.setString  (6, novo.getImgPath());
            
            stat.setInt(7, id);
            stat.executeUpdate();
        } catch (SQLException ex ) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void deletar(Cliente c) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE Id = ? and Nome = ? and Documento = ?"
                   + "and Saldo = ? and Ativo = ? and Imagem = ? ";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt     (1, c.getId());
            stat.setString  (2, c.getNome());
            stat.setString  (3, c.getDocumento());
            stat.setFloat   (4, c.getSaldo());
            stat.setBoolean (5, c.isAtivo());
            stat.setString  (6, c.getImgPath());
            
            stat.executeUpdate();
            stat.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE Id = ?";
        
        try (PreparedStatement stat = connection.prepareStatement(sql)) {
            stat.setInt(1, id);
            stat.executeUpdate();
            stat.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void listar() throws SQLException {
        String sql = "SELECT * FROM Cliente";
        
        try (Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String nome = rs.getString("Nome");
                String documento = rs.getString("Documento");
                float saldo = rs.getFloat("Saldo");
                boolean ativo = rs.getBoolean("Ativo");
                String imgPath = rs.getString("ImgPath");

                Cliente cliente = new Cliente(id, nome, documento, saldo, ativo, imgPath);
                System.out.println(cliente);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    //Executando com FilteredRowSet
    public void listarFilteredRowSet(double saldoMin, double saldoMax) throws SQLException {
        RowSetFactory factory = RowSetProvider.newFactory();
        FilteredRowSet frs = factory.createFilteredRowSet();
        
        frs.setUrl(conexao.getUrl());
        frs.setUsername(conexao.getUser());
        frs.setPassword(conexao.getPass());
        
        String sql = "SELECT * FROM Cliente";
        frs.setCommand(sql);
        frs.execute();
        
        frs.setFilter(new SaldoFilter(saldoMin, saldoMax));
        
        while (frs.next()) {
            int id = frs.getInt("Id");
            String nome = frs.getString("Nome");
            String documento = frs.getString("Documento");
            float saldo = frs.getFloat("Saldo");
            boolean ativo = frs.getBoolean("Ativo");
            String imgPath = frs.getString("ImgPath");

            Cliente cliente = new Cliente(id, nome, documento, saldo, ativo, imgPath);
            System.out.println(cliente);
        }
    }    
}
