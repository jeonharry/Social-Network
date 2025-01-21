package model.exceptions;

public class WeakPassword extends Exception{
    public WeakPassword(String mes)
    {
        super(mes);
    }
    public WeakPassword()
    {
        this("your password is weak\nuse 0-9 a-z A-Z @#$%& in your password");
    }
}
