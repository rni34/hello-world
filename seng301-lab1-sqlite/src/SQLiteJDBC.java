import java.sql.*;

/**
 * This class shows how to deal with JDBC connections in Java. It is written in a
 * Minimum Working Example (MWE) way and is not intended to be reused that way, e.g.,
 *
 * # static methods are bad
 * # way too many things are hardcoded
 * # the connection is passed here and there instead of reopening/closing the connection properly each time
 * # JDBC ResultSet objects are returned instead of proper domain objects (or even interfaces)
 * # other bunch of problems that you should find by yourself
 *
 * HINT: https://www.sonarlint.org/
 * @author Fabian Gilson
 */

public class SQLiteJDBC {

    /**
     * Select all recipes from table using given connection
     *
     * @param connection a non null connection to the database
     * @return the result set (sql cursor) with all recipes in the database
     * @throws SQLException if any error occurred regarding the database
     */
    public static ResultSet selectAllRecipes(Connection connection) throws SQLException{
        assert null != connection;
        System.out.println("get all recipes");
        PreparedStatement statement = connection.prepareStatement("select * from recipe");
        ResultSet resultSet = statement.executeQuery();
        statement.closeOnCompletion();
        return resultSet;
    }

    /**
     * Insert a new preparation step in the database
     *
     * @param connection a non-null connection to the database
     * @param recipeId the id of the recipe to which a preparation step will be added
     * @param stepNumber the sequence number of that description within given recipe
     * @param description the description of the recipe for that stepNumber
     * @throws SQLException if any error occurred regarding the database
     */
    public static void insertPreparationStep(Connection connection, int recipeId, int stepNumber, String description) throws SQLException {
        assert null != connection && recipeId >= 0 && stepNumber > 1 && null != description;
        System.out.println("add a new step " + stepNumber + " to recipe " + recipeId + " with description " + description);
        // need to retrieve latest technical id of a step by selecting the max value from table
        // we can access to a column by its index (starting from 1)
        PreparedStatement statement = connection.prepareStatement("select max(id_step) from preparation_step");
        int highestStep = statement.executeQuery().getInt(1);

        // using wildcards ("?") to make the sql statement reusable and easier to read
        // as no type conversion are needed when passing parameters to insert in DB

        statement = connection.prepareStatement("insert into preparation_step(id_step, id_recipe, step_number, description) values (?,?,?,?)");
        // use indexes of wildcard ("?") starting from 1
        statement.setInt(1, highestStep + 1);
        statement.setInt(2, recipeId);
        statement.setInt(3, stepNumber);
        statement.setString(4, description);
        // print the result of the insert statement, 0 means nothing has been inserted
        System.out.println("rows added: " + statement.executeUpdate());
        statement.closeOnCompletion();
    }

    public static void insertRecipe(Connection connection, String name) throws SQLException {
        assert null != connection && name != null;
        System.out.println("adding a new recipe for " +  name);
        PreparedStatement statement = connection.prepareStatement("select max(id_recipe) from recipe");
        int highestId = statement.executeQuery().getInt(1);
        statement = connection.prepareStatement("insert into recipe(id_recipe, name) values (?,?)");
        // use indexes of wildcard ("?") starting from 1
        statement.setInt(1, highestId + 1);
        statement.setString(2, name);
        // print the result of the insert statement, 0 means nothing has been inserted
        System.out.println("rows added: " + statement.executeUpdate());
        statement.closeOnCompletion();
    }

    public static void insertIngredientUsedinStep(Connection connection, int recipeId, int stepNumber, int ingredientId)
            throws SQLException{
        //assert null != connection && recipeId >= 0 && ingredientId >= 0 && stepNumber > 0;
        System.out.println("inserting an used ingredient");
        PreparedStatement statement = connection.prepareStatement("select id_step from preparation_step where step_number = ? and id_recipe = ?");
        statement.setInt(1, stepNumber);
        statement.setInt(2, recipeId);

        int stepId = statement.executeQuery().getInt(1);

        statement = connection.prepareStatement("insert into ingredient_usedin_step(id_ingredient, id_step) values (?,?)");
        // use indexes of wildcard ("?") starting from 1
        statement.setInt(1, ingredientId);
        statement.setInt(2, stepId);

        // print the result of the insert statement, 0 means nothing has been inserted
        System.out.println("rows added: " + statement.executeUpdate());
        statement.closeOnCompletion();
        System.out.println("passed");
    }



    /**
     * Print current preparation_step pointed by given resultset cursor
     *
     * @param set a non null resultset
     * @throws SQLException if any error occurred regarding the database
     */
    public static void printPreparationStep(ResultSet set) throws SQLException {
        assert null != set;
        // rows can be retrieved by names
        System.out.print(set.getInt("step_number") + ", ");
        // or by index (starts at index 1 for columns)
        System.out.print(set.getString(4));
        System.out.println();
    }

    /**
     * Print all preparation steps for given recipe using given connection to the database
     *
     * @param connection an active connection to the database
     * @param recipeId an id of a recipe
     * @throws SQLException if anything goes wrong with the connection
     */
    public static void printPreparationForRecipe(Connection connection, int recipeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from preparation_step where id_recipe = ?");
        statement.setInt(1, recipeId);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            printPreparationStep(resultSet);
        }
        resultSet.close();
        statement.closeOnCompletion();
    }



    /**
     * Let's show how to connect to a sqlite database, retrieve data from it and insert some rows
     *
     * @param args none
     */
    public static void main(String[] args) {
        // the path to the sqlite file, here it is at the root of current project
        // the url is of the form "jdbc:protocol:path"
        String url = "jdbc:sqlite:lab1.sqlite";
        System.out.println("open connection to " + url);
        // try (with resource)-like syntax, which raise an error if the connection goes wrong
        // it also auto-close all resources opened within the parenthesis
        try(Connection connection = DriverManager.getConnection(url)) {
            // get all recipes
            ResultSet set = selectAllRecipes(connection);
            // for all of them, get recipe Id and print all steps
            while(set.next()) {
                System.out.println("Print all steps for recipe " + set.getString("name"));
                printPreparationForRecipe(connection, set.getInt("id_recipe"));
            }
            // add a new preparation step for recipe with id 2 (Caesar salad in our test database)
            //insertPreparationStep(connection,2, 1, "Wash salad");
            // print all steps for that recipe
            System.out.println("Print all steps for recipe 2 (Caesar Salad)");
            printPreparationForRecipe(connection, 2);

            insertIngredientUsedinStep(connection, 0, 1, 0);

            // close resultset
            set.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}