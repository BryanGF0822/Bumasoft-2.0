package model;

public class Motorcycle extends Vehicle {

    public Motorcycle(String vehicleType, String brand, String model, String cylinderCapacity, String color, String licensePlate) {
        super(vehicleType, brand, model, cylinderCapacity, color, licensePlate);
    }

    @Override
    public String getVehicleType() {
        return super.getVehicleType();
    }

    @Override
    public void setVehicleType(String vehicleType) {
        super.setVehicleType(vehicleType);
    }

    @Override
    public String getBrand() {
        return super.getBrand();
    }

    @Override
    public void setBrand(String brand) {
        super.setBrand(brand);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public String getCylinderCapacity() {
        return super.getCylinderCapacity();
    }

    @Override
    public void setCylinderCapacity(String cylinderCapacity) {
        super.setCylinderCapacity(cylinderCapacity);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public void setColor(String color) {
        super.setColor(color);
    }

    @Override
    public String getLicensePlate() {
        return super.getLicensePlate();
    }

    @Override
    public void setLicensePlate(String licensePlate) {
        super.setLicensePlate(licensePlate);
    }
}
