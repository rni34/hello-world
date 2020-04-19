package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteJDBC {

        Connection connection;


        public Connection createConnection() throws SQLException {
                String url = "jdbc:sqlite:lab1.sqlite";
                connection = DriverManager.getConnection(url);
                System.out.println("\nConnected to " + url + "!");
                return connection;
        }

        public boolean isThereRecipeNamedAs( String name ) throws SQLException {
                PreparedStatement statement = connection.prepareStatement( "select * from recipe where name = ?" );
                statement.setString( 1, name );
                statement.closeOnCompletion( );
                return statement.executeQuery().next();
        }

        public boolean isThereStepNumberXInRecipeY( int stepNumber, String recipeName ) {
                assert stepNumber >= 0 && null != recipeName && !recipeName.isBlank();
                return false;
                }



}