package model.exceptions;

public class WeakPassword extends Exception{
    public WeakPassword(String mes)
    {
        super(mes);
    }
    public WeakPassword()
    {
        this("This password is weak");
    }
}
