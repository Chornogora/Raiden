package ua.nure.bulhakov.summary.database;

import javax.sql.DataSource;
import java.sql.*;

public abstract class DatabaseManager {

    private static DataSource clientSource;

    private static DataSource adminSource;

    public enum ROLES{
        CLIENT, ADMINISTRATOR, BOSS;

        protected Connection getConnection() throws SQLException{
            switch(this){
                case CLIENT: return clientSource.getConnection();
                case ADMINISTRATOR: return adminSource.getConnection();
                default: return clientSource.getConnection();
            }
        }
    }

    public static void setClientSource(DataSource clientSource) {
        DatabaseManager.clientSource = clientSource;
    }

    public static void setAdminSource(DataSource adminSource) {
        DatabaseManager.adminSource = adminSource;
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

    void rollbackConnection(Connection connection){
        try{
            if(connection != null){
                connection.rollback();
            }
        }catch(SQLException e){
            //Can't do anything
        }
    }
}

