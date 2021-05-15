package model;

public class Tenant extends Resident implements Comparable<Tenant>{
    private int quantityVehicle;
    private String towerLessee;
    private int apartmentLessee;

    public Tenant(String residentId, String fullName, String typeId, String telephone, String email,int quantityVehicle,String towerLessee,int apartmentLessee) {
        super(residentId, fullName, typeId, telephone, email);
        this.quantityVehicle = quantityVehicle;
        this.towerLessee = towerLessee;
        this.apartmentLessee = apartmentLessee;
    }

    public int getQuantityVehicle() {
        return quantityVehicle;
    }

    public void setQuantityVehicle(int quantityVehicle) {
        this.quantityVehicle = quantityVehicle;
    }

    public String getTowerLessee() {
        return towerLessee;
    }

    public void setTowerLessee(String towerLessee) {
        this.towerLessee = towerLessee;
    }

    public int getApartmentLessee() {
        return apartmentLessee;
    }

    public void setApartmentLessee(int apartmentLessee) {
        this.apartmentLessee = apartmentLessee;
    }

    @Override
    public int compareTo(Tenant otherTenant) {
        int comp;
        if(quantityVehicle < otherTenant.getQuantityVehicle()) {
            comp = 1;
        }else if(quantityVehicle > otherTenant.getQuantityVehicle()) {
            comp = -1;
        }else if(apartmentLessee < otherTenant.apartmentLessee){
            comp = 1;
        }else if(apartmentLessee > otherTenant.getApartmentLessee()){
            comp = -1;
        }else {
            comp = 0;
        }
        return comp;
    }
}
