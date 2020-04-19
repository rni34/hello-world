package entity;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "preparation_step", schema = "main", catalog = "")
public class PreparationStep {
    private Short idStep;
    private Short stepNumber;
    private String description;
    private Recipe recipe;
    private Set<Ingredient> ingredients;



    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "ingredient_usedin_step",
            joinColumns = { @JoinColumn(name = "id_step") },
            inverseJoinColumns = { @JoinColumn(name = "id_ingredient") }
            )
    public Set<Ingredient> getIngredients() {
        return ingredients;
        }

        public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
        }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe")
    public entity.Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }



    @Id
    @Column(name = "id_step")
    public Short getIdStep() {
        return idStep;
    }

    public void setIdStep(Short idStep) {
        this.idStep = idStep;
    }

    @Basic
    @Column(name = "step_number")
    public Short getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Short stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreparationStep that = (PreparationStep) o;

        if (idStep != null ? !idStep.equals(that.idStep) : that.idStep != null) return false;
        if (stepNumber != null ? !stepNumber.equals(that.stepNumber) : that.stepNumber != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStep != null ? idStep.hashCode() : 0;
        result = 31 * result + (stepNumber != null ? stepNumber.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "Preparation step with ID " + idStep + ": " + description + " with ingredients: "
                + ingredients.stream().map(i -> i.getName()).collect(Collectors.joining(", "));
    }

}
