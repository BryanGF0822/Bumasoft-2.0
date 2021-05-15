package model;

public class Resident {
    private String residentId;
    private String fullName;
    private String typeId;
    private String telephone;
    private String email;
    private Resident nextResident;

    public Resident(String residentId, String fullName, String typeId, String telephone, String email) {
        this.residentId = residentId;
        this.fullName = fullName;
        this.typeId = typeId;
        this.telephone = telephone;
        this.email = email;
        nextResident = null;
    }

    public Resident getNextResident() {
        return nextResident;
    }

    public void setNextResident(Resident nextResident) {
        this.nextResident = nextResident;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
