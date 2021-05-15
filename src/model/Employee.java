package model;

public class Employee {
    private String fullName;
    private String employeeId;
    private int position;
    private Employee left;
    private Employee Right;

    public Employee(String fullName, String employeeId, int position) {
        this.fullName = fullName;
        this.employeeId = employeeId;
        this.position = position;
    }

    public Employee getLeft() {
        return left;
    }

    public void setLeft(Employee left) {
        this.left = left;
    }

    public Employee getRight() {
        return Right;
    }

    public void setRight(Employee right) {
        Right = right;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
