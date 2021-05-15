package exception;

public class RealStateRegistrationDuplicate extends Exception {

    private String realStateRegistration;

    public RealStateRegistrationDuplicate(String realStateRegistration) {
        super("the Realstate registration is duplicate");

        this.realStateRegistration = realStateRegistration;
    }

    public String getRealStateRegistration() {
        return realStateRegistration;
    }
}
