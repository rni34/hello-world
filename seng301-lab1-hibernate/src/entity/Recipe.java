package entity;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Recipe {

    @Basic
    @Column(name = "name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    private Short idRecipe;



    @OneToMany(mappedBy = "recipe")
    private Set<PreparationStep> preparationSteps;

    public Set<PreparationStep> getPreparationSteps() {
        return preparationSteps;
    }

    public void setPreparationSteps(Set<PreparationStep> preparationSteps) {
        this.preparationSteps = preparationSteps;
    }



    public Short getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Short idRecipe) {
        this.idRecipe = idRecipe;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (idRecipe != null ? !idRecipe.equals(recipe.idRecipe) : recipe.idRecipe != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecipe != null ? idRecipe.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }


    public String toString() {
        return "Recipe with ID " + idRecipe + " name " + name + " with steps: "
                + preparationSteps.stream().map(i -> i.toString()).collect(Collectors.joining(", "));
    }




}