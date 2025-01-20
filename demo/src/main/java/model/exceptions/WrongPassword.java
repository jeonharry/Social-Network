package model.exceptions;

public class WrongPassword extends Exception{
    public WrongPassword(String mes)
    {
        super(mes);
    }
    public WrongPassword()
    {
        this("Sorry, your password was incorrect");
    }
}
