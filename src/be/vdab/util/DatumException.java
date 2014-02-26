package be.vdab.util;

import java.io.Serializable;

public class DatumException extends Exception implements Serializable{

    public DatumException() {
        super();
    }
    public DatumException(String message) {
        super(message);
    }
    public DatumException(String message, Throwable cause) {
        super(message, cause);
    }
    public DatumException(Throwable cause) {
        super(cause);
    }
        
    
}
