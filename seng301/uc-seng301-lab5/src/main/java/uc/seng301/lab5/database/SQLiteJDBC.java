package uc.seng301.lab5.database;

public interface SQLiteJDBC {
    /**
     * Ensure the connection to the database is alive
     *
     * @return true if a connection can be made to the databse, false otherwise
     */
    boolean createConnection();

    /**
     * Check whether a recipe with given name exists in database
     *
     * @param name a name of a recipe (case insensitive)
     * @return true if given name identifies a recipe in database, false otherwise
     */
    boolean isThereRecipeNamedAs(String name);

    /**
     * Check whether given recipeName has a step with given stepNumber
     * @param stepNumber a step number (positive)
     * @param recipeName a name of a recipe (case insensitive)
     * @return true if given step exists in given recipeName, false otherwise
          */
    boolean isThereStepNumberXInRecipeY(int stepNumber, String recipeName);

    /**
     * Retrieve a description for a given recipe and stepNumber
     * @param recipeName a name of a recipe (case insensitive)
     * @param stepNumber a step number (positive)
     * @return the description of given step for given recipe, null if no recipe could be found,
     * a blank string if no such step exists fo given recipe
     */
    String getRecipeStepDescription(String recipeName, int stepNumber);


    /**
     * Retrieve the number of steps for a recipe id
     * @param recipeName a name of a recipe (case insensitive)
     * @return the number of steps of given recipe, -1 if given recipe id does not exist
     */
    int getRecipesNumberOfSteps(String recipeName);

    /**
     * Add given step to given recipe
     * @param stepDescription a description of a step
     * @param recipeName a name of a recipe (case insensitive)
     * @return the newly created step id if given recipe exists, -1 if no such recipe exists
     */
    int addStepToRecipe(String stepDescription, String recipeName);
}

