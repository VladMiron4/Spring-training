package ro.msg.learning.shop.exception;

public class NegativeQuantityException extends RuntimeException {
    public NegativeQuantityException() {
        super("Quantities cannot be negative");
    }
}
