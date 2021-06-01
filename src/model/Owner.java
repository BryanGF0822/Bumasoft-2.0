package model;

public class Owner {
    private String fullName;
    private String ownerId;
    private String typeId;
    private String telephone;
    private String email;

    public Owner(String fullName, String ownerId, String typeId, String telephone, String email) {
        this.fullName = fullName;
        this.ownerId = ownerId;
        this.typeId = typeId;
        this.telephone = telephone;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

	@Override
	public String toString() {
		return fullName + "";
	}
    
    
}
