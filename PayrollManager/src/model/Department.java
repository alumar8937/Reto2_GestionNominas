package model;

/**
 * Represents a department with a name and ID.
 *
 * @author Pedro Marín Sanchis
 */
public class Department {
    private String name = "";
    private String ID = "";

    /**
     * Constructs a Department object with the specified name and ID.
     *
     * @param name the name of the department
     * @param ID   the ID of the department
     */
    public Department(String name, String ID) {
        this.name = name;
        this.ID = ID;
    }

    /**
     * Returns a string representation of the department.
     *
     * @return a string representation of the department
     */
    @Override
    public String toString() {
        return name + " ID:" + ID;
    }

    /**
     * Returns the ID of the department.
     *
     * @return the ID of the department
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the name of the department.
     *
     * @return the name of the department
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the ID of the department.
     *
     * @param ID the ID of the department to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Sets the name of the department.
     *
     * @param name the name of the department to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
