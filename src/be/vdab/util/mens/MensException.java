package be.vdab.util.mens;

public class MensException extends IllegalArgumentException{
    
    public MensException(){
    super();
    }
    
     public MensException(String message){
    super(message);
    }
     
      public MensException(String message, Throwable cause){
    super(message, cause);
    }
      
       public MensException(Throwable cause){
    super(cause);
    }
    
}
