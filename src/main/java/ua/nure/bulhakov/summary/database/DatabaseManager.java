package ua.nure.bulhakov.summary.database;

import java.sql.*;
import java.util.Map;

public abstract class DatabaseManager {

    // TODO initialize into launcher
    static String connectionString;

    static final String driverName = "org.postgresql.Driver";

    static Map<CLASS_LEVEL, String> loginMap;

    static Map<CLASS_LEVEL, String> passwordMap;

    public enum CLASS_LEVEL{
        CLIENT, ADMINISTRATOR, BOSS;

        String getLogin(){
            return loginMap.get(this);
        }

        String getPassword(){
            return passwordMap.get(this);
        }
    }

    public static void setConnectionString(String str){
        connectionString = str;
    }

    public static void setLoginMap(Map<CLASS_LEVEL, String> map){
        loginMap = map;
    }

    public static void setPasswordMap(Map<CLASS_LEVEL, String> map){
        passwordMap = map;
    }

    void closeConnection(Connection conn) throws SQLException {
        if(conn != null) {
            conn.close();
        }
    }

    void closeStatement(Statement stat) throws SQLException {
        if(stat != null) {
            stat.close();
        }
    }

    void closeResultSet(ResultSet set) throws SQLException {
        if(set != null) {
            set.close();
        }
    }
}

