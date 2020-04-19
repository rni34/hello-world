package gradle.cucumber;

import database.SQLiteJDBC;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class PreparationStep {

    private SQLiteJDBC sqlLiteJDBC;
    private Connection connection;

    @Before //Before hooks run before the first step of each scenario...
    public void setup() {
        sqlLiteJDBC = new SQLiteJDBC();
        try {
            connection = sqlLiteJDBC.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Given("I am connected to the lab1 database")
    public void iAmConnectedToTheLab1Database() throws SQLException {
        Assert.assertNotNull(connection);
    }

    @After //After hooks run after the last step of each scenario (whatever the result is, e.g., pending or skipped)
    public void close() throws SQLException {
        connection.close();
    }

    @Given("There is a recipe named {string}")
    public void thereIsARecipeNamed(String name ) throws SQLException {
        Assert.assertTrue( sqlLiteJDBC.isThereRecipeNamedAs( name ));
        }

    @When("I update step number {int} description to {string}")
    public void iUpdateStepNumberDescriptionTo(Integer int1, String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
        }


}



