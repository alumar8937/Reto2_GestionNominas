package model;

/**
 * Represents an employee with a name and NIF.
 *
 * @author David Serna Mateu
 */
public class Employee {
    private String name = " ";
    private String NIF = " ";

    /**
     * Constructs an Employee object with the specified name and NIF.
     *
     * @param name the name of the employee
     * @param NIF  the NIF (identification number) of the employee
     */
    public Employee(String name, String NIF) {
        this.name = name;
        this.NIF = NIF;
    }

    /**
     * Returns a string representation of the employee.
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return NIF + ", " + name;
    }

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the NIF (identification number) of the employee.
     *
     * @return the NIF of the employee
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name the name of the employee to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the NIF (identification number) of the employee.
     *
     * @param NIF the NIF of the employee to set
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
}
