package Model.Exception;

/**
 * Created by boscp on 2017-05-24.
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String msg) {
        super(msg);
    }
}
