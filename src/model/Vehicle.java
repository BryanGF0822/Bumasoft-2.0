package model;

public class Vehicle implements Comparable<Vehicle> {
    private String vehicleType;
    private String brand;
    private String model;
    private String cylinderCapacity;
    private String color;
    private String licensePlate;

    public Vehicle(String vehicleType, String brand, String model, String cylinderCapacity, String color, String licensePlate) {
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.cylinderCapacity = cylinderCapacity;
        this.color = color;
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(String cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public int compareTo(Vehicle o) {
        int comp;
        int v1 = Integer.parseInt(getVehicleType());
        int v2 = Integer.parseInt(o.getVehicleType());

        comp = Integer.compare(v1, v2);

        if (comp == 0){
            v1 = Integer.parseInt(getBrand());
            v2 = Integer.parseInt(o.getBrand());

            comp = Integer.compare(v1, v2);
        }
        return comp;

    }
}
