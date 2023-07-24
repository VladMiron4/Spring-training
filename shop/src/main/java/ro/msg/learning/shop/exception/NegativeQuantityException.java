package ro.msg.learning.shop.exception;

public class NegativeQuantityException extends Exception{

    public NegativeQuantityException(){
        super("Quantities cannot be negative");
    }
}
