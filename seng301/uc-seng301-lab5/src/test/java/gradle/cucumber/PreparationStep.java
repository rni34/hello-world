package gradle.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import uc.seng301.lab5.database.SQLiteJDBC;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PreparationStep {

    SQLiteJDBC mockedJdbc;
    private String recipeName;
    private int steps;


    @Before
    public void setupTestEnvironment( ) {
        // mock() is used to create a wrapper substituting an interface by a (dummy) implementation
        mockedJdbc = mock( SQLiteJDBC.class );
        }


    @Given("I am connected to the lab1 database")
    public void iAmConnectedToTheLab1Database() {

        // supplying a method stub (local dummy implementation)
        when( mockedJdbc.createConnection( ) ).thenReturn( true );
        // further JUnit assertion
        Assert.assertTrue( mockedJdbc.createConnection( ) );

    }

    @Given("There is a recipe named {string} with {int} steps")
    public void thereIsARecipeNamedWithSteps( String recipeName, int numberOfSteps ) {
        this.recipeName = recipeName;
        this.steps = numberOfSteps;
        when( mockedJdbc.isThereRecipeNamedAs( recipeName ) ).thenReturn( this.recipeName.equals(recipeName));
        when( mockedJdbc.getRecipesNumberOfSteps(recipeName)).thenReturn(this.steps);
        
        Assert.assertTrue( mockedJdbc.isThereRecipeNamedAs( recipeName ) );
        Assert.assertEquals( mockedJdbc.getRecipesNumberOfSteps( recipeName ), numberOfSteps );
        }


    @When("I add a new step {string} to recipe {string}")
    public void iAddANewStepToRecipe( String stepDescription, String recipeName ) {
        // the handling of stubbing is very simplistic, but you can see what can be done
        steps++;
        when( mockedJdbc.addStepToRecipe( stepDescription, recipeName ) ).thenReturn( steps );
        Assert.assertEquals( mockedJdbc.addStepToRecipe( stepDescription, recipeName ), steps);
        }

    @Then("There is a step number {int} for the recipe {string}")
    public void thereIsAStepNumberForTheRecipe(Integer int1, String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Recipe {string} step number {int} description is {string}")
    public void recipeStepNumberDescriptionIs(String string, Integer int1, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}


