package ro.msg.learning.shop.exception;

public class StrategyNotFoundException extends Exception {

    public StrategyNotFoundException() {
        super("Strategy must either be set to abundant or single");
    }
}
