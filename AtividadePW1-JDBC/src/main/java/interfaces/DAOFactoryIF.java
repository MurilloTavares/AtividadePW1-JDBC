package interfaces;

import controle.ClienteDAO;
import controle.PedidoDAO;

public interface DAOFactoryIF {
    
    public ClienteDAO criaClienteDAO();
    
    public PedidoDAO criaPedidoDAO();
    
}
