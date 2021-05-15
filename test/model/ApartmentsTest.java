package model;

import exception.RealStateRegistrationDuplicate;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentsTest {
    private Apartments apartments;
    private ResidentialUnit residentialUnit;

    private void setupScenary1() throws FileNotFoundException {
        residentialUnit = new ResidentialUnit("","","","",0,0,0,0,0);
    }

    @Test
    public void testApartments1() throws FileNotFoundException {
        setupScenary1();
        assertTrue(residentialUnit.getTheApartments().isEmpty());
    }

    private void setupScenary2() throws FileNotFoundException {
        residentialUnit = new ResidentialUnit("","","","",0,0,0,0,0);
    }

    @Test
    public void testApartments() throws FileNotFoundException, RealStateRegistrationDuplicate {
        setupScenary2();
        String realStateRegistration = "123456";
        String apartmentNumber = "1234";
        String tower = "1";
        String ownerId = "12345";
        String state = "asdf";
        int population = 1;
        String residentType = "asdfg";
        apartments = new Apartments(realStateRegistration,apartmentNumber,tower,ownerId,state,population,residentType);
        residentialUnit.addApartments(realStateRegistration,apartmentNumber,tower,ownerId,state,population,residentType);
        assertEquals(ownerId, residentialUnit.getTheApartments().get(0).getOwnerId(), residentialUnit.getTheApartments().get(0).getRealStateRegistration());

    }



}