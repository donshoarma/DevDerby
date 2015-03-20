package nl.whitehorses.tornado.dataControls;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import nl.whitehorses.tornado.util.ApplicationSettings;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class SettingsDC {
    private static String DB_TABLE_NAME = "TORNADO_SETTINGS";
    private static String DB_COLUMN_NAME_OVERVIEW_TYPE = "OVERVIEW_TYPE";
    
    public SettingsDC() {
        super();
    }
    
    public String getOverviewTypeFromDb() {
        Connection connection = null;
        List resultSet = new ArrayList();
        
        try {
            //Open connection
            String applicationDirectory =
                AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
            String databaseFileLocation = applicationDirectory + "/" + ApplicationSettings.SQLITE_SETTINGS_DB_FILENAME;
            connection = new SQLite.JDBCDataSource("jdbc:sqlite:" + databaseFileLocation).getConnection();
            
            //SQL select statement
            Statement statement = connection.createStatement();
            ResultSet statementResult = statement.executeQuery("SELECT " + DB_COLUMN_NAME_OVERVIEW_TYPE + " FROM TORNADO_SETTINGS " + DB_TABLE_NAME  + ";");
            
            //Construct result set
            while(statementResult.next()) {
                return statementResult.getString("OVERVIEW_TYPE");
            }
        } catch (SQLException ex) {
            //SQL exception occurred
            ex.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        
        return null;
    }
    
    public void storeOverviewTypeInDb(String overviewType) {
        Connection connection = null;
        
        try  {
            //Open connection
            String applicationDirectory =
                AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory);
            String databaseFileLocation = applicationDirectory + "/" + ApplicationSettings.SQLITE_SETTINGS_DB_FILENAME;
            connection = new SQLite.JDBCDataSource("jdbc:sqlite:" + databaseFileLocation).getConnection();
            connection.setAutoCommit(false);
            
            //Clear old cached data
            Statement batchStatement = connection.createStatement();
            batchStatement.addBatch("DELETE FROM " + DB_TABLE_NAME + ";");
            
            //Insert new value
            batchStatement.addBatch("INSERT OR IGNORE INTO " + DB_TABLE_NAME + " (" + DB_COLUMN_NAME_OVERVIEW_TYPE + ") VALUES ('" + overviewType + ");");
            
            //Execute statement and commit database transaction
            batchStatement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            //SQL exception occurred
            ex.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}