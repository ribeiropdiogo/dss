package MediaCenterSystem.DataAccess.DBTools;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class DBAcess {

    public interface ResultProcesser {
        Object parseRs(ResultSet rs);
    }

    private final static String url = "jdbc:mysql://localhost:3306/MediaCenter";
    private final static String user = "root";
    private final static String pass = "";

    public static Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Executa uma dada query em PLSQL, utilizando uma função de ordem superior para processar o resultado
     * da query.
     *
     * @param msg   Código PLSQL a enviar.
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

            if (!id.equals(""))
                stm.executeUpdate("DELETE FROM " + table + " WHERE " + id);

            String sql = "INSERT INTO " + table + " VALUES " + newParams;
            stm.executeUpdate(sql);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static void removeEntry(String table, String ifs) {
        try (Connection conn = DBAcess.makeConnection()) {
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM " + table + " WHERE " + ifs);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static int countQuery(String table) {
        return (Integer) DBAcess.excuteQuery(DBBaseQueries.count(table), DBAcess::getSize);
    }

    public static int countQuery(String table, String ifs) {
        return (Integer) DBAcess.excuteQuery(DBBaseQueries.count(table, ifs), DBAcess::getSize);
    }

    public static Object getQuery(String table, String ifs, ResultProcesser rs) {
        String sql = DBBaseQueries.select(ifs, table);
        return DBAcess.excuteQuery(sql, rs);
    }

    public static Object getQuery(String table, String proj, String ifs, ResultProcesser rs) {
        String sql = DBBaseQueries.projSel(proj, ifs, table);
        return DBAcess.excuteQuery(sql, rs);
    }

    public static Set<Integer> getIds(String table, String proj, String ifs) {
        try (Connection con = DBAcess.makeConnection()) {
            String msg = DBBaseQueries.projSel(proj, ifs, table);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(msg);
            return DBAcess.getIntSet(rs);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static Set<Integer> getIds(String table, String proj) {
        return DBAcess.getIds(table, proj, "");
    }

    public static Set<String> getNames(String table, String proj, String ifs) {
        try (Connection con = DBAcess.makeConnection()) {
            String msg = DBBaseQueries.projSel(proj, ifs, table);
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(msg);
            return DBAcess.getStringSet(rs);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static Set<String> getNames(String table, String proj) {
        return DBAcess.getNames(table, proj, "");
    }

    public static Integer maxIds(String table, String projo) {
        return (Integer)DBAcess.excuteQuery(DBBaseQueries.max(table, projo), DBAcess::getSize);
    }

    public static Integer getSize(ResultSet rs) {
        try {
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static Set<Integer> getIntSet(ResultSet rs) {
        Set<Integer> set = null;
        try {
            set = new HashSet<>();

            while (rs.next())
                set.add(rs.getInt(1));

            return set;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static Set<String> getStringSet(ResultSet rs) {
        Set<String> set = null;
        try {
            set = new HashSet<>();

            while (rs.next())
                set.add(rs.getString(1));

            return set;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static String makeVarStr(List<String> ls) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ls.size() - 1; i++) {
            sb.append(ls.get(i)).append(",");
        }

        if (ls.size() - 1 >= 0)
            sb.append(ls.get(ls.size() - 1));

        return sb.toString();
    }

}
