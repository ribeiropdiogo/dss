package MediaCenterSystem.DataAccess.DBTools;

public abstract class DBBaseQueries {

    public static String max(String table, String parameter) {
        return "SELECT max(" + parameter + ") AS 'max' FROM (" + table + ")";
    }

    public static String count(String table) {
        return DBBaseQueries.count(table, "");
    }

    public static String count(String table, String ifs) {
        return "SELECT COUNT(*) AS 'total' FROM (" + table + ") " + (ifs.equals("") ? "" : "WHERE " + ifs);
    }

    // Tradutores de algebra relacional
    public static String naturalJoin(String table1, String table2, String atribs) {
        return "SELECT " + atribs +
                " FROM (" + table1 +
                ") NATURAL JOIN (" + table2 + ")";
    }

    public static String naturalJoin(String table1, String table2) {
        return DBBaseQueries.naturalJoin(table1, table2, "*");
    }

    public static String projSel(String proj, String sel, String table) {
        String ifs = (sel.equals("")) ? ("") : ("WHERE " + sel);
        return "SELECT " + proj + " FROM (" + table + ") " + ifs;
    }

    public static String select(String sel, String table) {
        return DBBaseQueries.projSel("*", sel, table);
    }


}
