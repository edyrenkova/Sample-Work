package models;

/**
 * Syntax Error Exception class. For use within Converter class in order to handle unique errors.
 */
public class SyntaxErrorException extends Exception {
    //Default constructor with my default message fed to super's constructor
    // to set message.
    public SyntaxErrorException()
    {
        super("Incorrect Syntax. Please try again.");
    }

    //Constructor that takes a String and feeds it to the super's constructor
    // to set message as such.
    public SyntaxErrorException(String message)
    {
        super(message);
    }
}
