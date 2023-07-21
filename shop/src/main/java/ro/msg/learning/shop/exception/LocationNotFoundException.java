package ro.msg.learning.shop.exception;

public class LocationNotFoundException extends Exception {
    public LocationNotFoundException() {
        super("Location could not be found");
    }
}
