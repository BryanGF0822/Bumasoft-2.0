package model;

public class DefaultingAdministration {
    private double debt;
    private String apartmentNumber;
    private String tower;
    private DefaultingAdministration left;
    private DefaultingAdministration right;

    public DefaultingAdministration(double debt, String apartmentNumber, String tower) {
        this.debt = debt;
        this.apartmentNumber = apartmentNumber;
        this.tower = tower;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public DefaultingAdministration getLeft() {
        return left;
    }

    public void setLeft(DefaultingAdministration left) {
        this.left = left;
    }

    public DefaultingAdministration getRight() {
        return right;
    }

    public void setRight(DefaultingAdministration right) {
        this.right = right;
    }
}
