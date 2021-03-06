package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    String url;
    String user;
    String pass;
    Connection conn;

    public Conexao() {
        url = "jdbc:postgresql://localhost:5432/AtividadePW1-JDBC";
        user = "postgres";
        pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na Conexao.");
        }
    }

    public Conexao(String url, String user, String pass) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na Conexao.");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    public Connection getConn() {
        return conn;
    }
    
}
