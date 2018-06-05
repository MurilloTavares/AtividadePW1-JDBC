package Filters;

import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.Predicate;

public class SaldoFilter implements Predicate {

    private static final String COLUNA = "Saldo";
    private double min;
    private double max;

    public SaldoFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean evaluate(RowSet rs) {
        if (rs == null) {
            return false;
        }
        
        CachedRowSet crs = (CachedRowSet) rs;

        try {
            double saldo = rs.getDouble(COLUNA);
            return saldo >= min && saldo <= max;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    @Override
    public boolean evaluate(Object valor, String coluna) throws SQLException {
        if(coluna.equalsIgnoreCase(COLUNA)){            
            double saldo = ((Double)valor);
            return saldo >= min && saldo <=max;            
        }
        return false;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public boolean evaluate(Object o, int i) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
