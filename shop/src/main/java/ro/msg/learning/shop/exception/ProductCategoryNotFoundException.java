package ro.msg.learning.shop.exception;

public class ProductCategoryNotFoundException extends Exception{
    public ProductCategoryNotFoundException(){
        super("Could not find category");
    }

}
