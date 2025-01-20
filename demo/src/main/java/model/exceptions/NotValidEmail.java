package model.exceptions;

public class NotValidEmail extends Exception{
    public NotValidEmail(String mes)
    {
        super(mes);
    }
    public NotValidEmail()
    {
        this("This email isn't valid");
    }
}
