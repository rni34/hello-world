package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.stream.Collectors;

@Entity
public class Ingredient {
    private Short idIngredient;
    private String name;

    public String toString() {
        return "Ingredient with ID " + idIngredient +" and name " + name;
    }

    @Id
    @Column(name = "id_ingredient")
    public Short getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Short idIngredient) {
        this.idIngredient = idIngredient;
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

        Ingredient that = (Ingredient) o;

        if (idIngredient != null ? !idIngredient.equals(that.idIngredient) : that.idIngredient != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idIngredient != null ? idIngredient.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
