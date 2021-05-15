package exception;

public class PlateNullException extends Exception {

    private String plate;

    public PlateNullException(String plate) {

        super("the plate is empty");

        this.plate = plate;

    }
}
