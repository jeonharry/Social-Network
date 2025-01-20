package model.exceptions;

public class UserNameExists extends Exception{
    public UserNameExists(String mes)
    {
        super(mes);
    }
    public UserNameExists()
    {
        this("Sorry, This Username is taken");
    }
}
