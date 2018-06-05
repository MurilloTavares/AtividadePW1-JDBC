package controle;

import interfaces.DAOFactoryIF;

public class DAOFactory implements DAOFactoryIF {

    public DAOFactory() {
    }

    @Override
    public ClienteDAO criaClienteDAO() {
        return new ClienteDAO();
    }

    @Override
    public PedidoDAO criaPedidoDAO() {
        return new PedidoDAO();
    }
    
}
