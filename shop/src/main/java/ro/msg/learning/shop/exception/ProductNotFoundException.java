package ro.msg.learning.shop.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
        super("Product was not found");
    }
}
