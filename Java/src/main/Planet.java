package main;
/**
 * Planet class to keep track of whether a part has been found at each planet and the name of the planet
 */
public class Planet {

    public boolean partFound;
    public String name;

    public Planet(String inputName) {
        partFound = false;
        name = inputName;
    }

    public boolean isPartFound() {
		return partFound;
	}

	public void setPartFound(boolean partFound) {
		this.partFound = partFound;
	}

	/**
     * @return Returns whether a part has been found on the planet
     */
    public String toString() {
        if (partFound) {
            return "A part was found on " + name;
        } else {
            return "No part has been found yet on " + name;
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
