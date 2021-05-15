package model;

public class Visitor {
    private String fullName;
    private String id;
    private String typeId;
    private String apartmentNumber;
    private String tower;
    private Visitor nextVisitor;

    public Visitor(String fullName, String id, String typeId, String apartmentNumber, String tower) {
        this.fullName = fullName;
        this.id = id;
        this.typeId = typeId;
        this.apartmentNumber = apartmentNumber;
        this.tower = tower;
        nextVisitor = null;
    }

    public Visitor getNextVisitor() {
        return nextVisitor;
    }

    public void setNextVisitor(Visitor nextVisitor) {
        this.nextVisitor = nextVisitor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
}
