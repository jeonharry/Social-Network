package model.exceptions;

public class NotExistenceUserName extends Exception{
    public NotExistenceUserName(String mes)
    {
        super(mes);
    }
    public NotExistenceUserName()
    {
        this("This username doesn't exist");
    }
}