package model;
import exception.PlateNullException;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class ResidentialUnitTest {
    private ResidentialUnit residentialUnit;
    private Vehicle vehicle;
    private Tenant tenant;

    public void setupScenary1() throws FileNotFoundException {

        residentialUnit = new ResidentialUnit("","","","",0,0,0,0,0);
    }

    @Test
    public void testAddVehicle() throws FileNotFoundException, PlateNullException {
        setupScenary1();

        String vehicleType = "carro";
        String brand = "mazda";
        String model = "2018";
        String cylinderCapacity = "1600";
        String color = "vinotinto";
        String licensePlate = "1234";
        
        Vehicle myVehicle = new Vehicle(vehicleType, brand, model, cylinderCapacity, color, licensePlate);



        residentialUnit.addVehicle(myVehicle);

        assertEquals(1,residentialUnit.getTheVehicles().size());
        assertEquals(licensePlate,residentialUnit.getTheVehicles().get(0).getLicensePlate());

        assertEquals(1,residentialUnit.getTheVehicles().size());
        assertEquals(brand,residentialUnit.getTheVehicles().get(0).getBrand());


    }

    private void setupScenary2() throws FileNotFoundException {
        residentialUnit = new ResidentialUnit("","","","",0,0,0,0,0);

    }

    @Test
    public void testAddTenant() throws FileNotFoundException {
        setupScenary2();
        int quantityVehicle = 2;
        String towerLessee = "1";
        int apartmentLessee = 405;
        String residentId = "12345";
        String fullName = "Paola";
        String typeId = "CC";
        String telephone = "123456";
        String email = "asdfgh";
        Tenant tenant = new Tenant(residentId, fullName, typeId,telephone, email, quantityVehicle, towerLessee, apartmentLessee);
        residentialUnit.addTenant(residentId,fullName,typeId,telephone,email,quantityVehicle,towerLessee,apartmentLessee);
        assertNotNull(residentialUnit.getTheTenants());
        assertEquals(fullName, tenant.getFullName());
        assertEquals(residentId, tenant.getResidentId());
        assertEquals(fullName,tenant.getFullName());

    }


}