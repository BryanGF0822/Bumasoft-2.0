package exception;

public class TypeDocumentException extends Exception {

    private String typeDoc;

    public TypeDocumentException(String typeDoc ){

        super("The type document number is incorrect.");
        this.typeDoc=typeDoc;

    }

    public String getTypeDoc() {
        return typeDoc;
    }
}
