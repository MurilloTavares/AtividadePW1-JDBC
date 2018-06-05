package visao;

import controle.ClienteDAO;
import controle.DAOFactory;
import controle.PedidoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import modelo.Cliente;
import modelo.Pedido;

public class ClassePrincipal {

    public static void main(String[] args) {

        DAOFactory factory = new DAOFactory();
        ClienteDAO cDao = factory.criaClienteDAO();
        PedidoDAO pDao = factory.criaPedidoDAO();
        
        System.out.println("Povoando Tabela Cliente");
        try {
            Cliente joao = new Cliente(1, "João", "documento AAAAAAA", 1000f, true);
            cDao.incluir(joao);

            Cliente maria = new Cliente(2, "Maria", "documento BBBBBBB", 900f, true);
            cDao.incluir(maria);
            
            cDao.listar();
            System.out.println("");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClassePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Povoando Tabela Pedido");       
        try {
            Pedido joaoA = new Pedido(1, null, 1, 10f);
            pDao.incluir(joaoA);
            
            Pedido mariaA = new Pedido(2, null, 2, 20f);
            pDao.incluir(mariaA);
            
            Pedido mariaB = new Pedido(3, null, 2, 5f);
            pDao.incluir(mariaB);
            
            pDao.listar();
            System.out.println("");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClassePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            System.out.println("JdbcRowSet:");
            pDao.listarJdbcRowSet();
            System.out.println("");
            
            System.out.println("CachedRowSet");
            pDao.listarCachedRowSet();
            System.out.println("");
            
            System.out.println("FilteredRowSet");
            cDao.listarFilteredRowSet(950f, 1500f);
            System.out.println("");
            
            System.out.println("JoinRowSet");
            RowSetFactory rsFactory = RowSetProvider.newFactory();
            JoinRowSet jrs = rsFactory.createJoinRowSet();
            
            
            RowSet clientes = cDao.listarCachedRowSet();
            RowSet pedidos = pDao.listarCachedRowSet();
            
            jrs.addRowSet(clientes, "ID");
            jrs.addRowSet(pedidos, "Cliente");
            
            System.out.println("---Junção---");
            while(jrs.next()){
                System.out.println("id=" + jrs.getString("ID") +
                               ", nome=" + jrs.getString("Nome") +
                               ", valor=" + jrs.getFloat("Valor"));                
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClassePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

}
