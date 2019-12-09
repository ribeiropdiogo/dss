package MediaCenterSystem.DataAccess.DBTools;

import java.sql.*;

public abstract class DBAcess {

    public interface ResultProcesser {
        Object parseRs(ResultSet rs);
    }

    private final static String url = "jdbc:mysql://localhost:3306/mydb";
    private final static String user = "root";
    private final static String pass = "root";

    public static Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Executa uma dada query em PLSQL, utilizando uma função de ordem superior para processar o resultado
     * da query.
     * @param msg Código PLSQL a enviar.
     * @param rproc Função que irá processar o ResultSet.
     * @return Objeto criado pela função de processamento.
     */
    public static Object excuteQuery(String msg, ResultProcesser rproc) {
        try (Connection con = DBAcess.makeConnection()) {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(msg);
            return rproc.parseRs(rs);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void putQuery(String table, String id, String newParams) {
        try (Connection conn = DBAcess.makeConnection()) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM " + table + " WHERE " + id);
            String sql = "INSERT INTO " + table + " VALUES " + newParams;
            stm.executeUpdate(sql);
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

}
