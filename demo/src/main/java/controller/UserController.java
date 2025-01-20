package controller;

public class UserController {
    private static UserController userController;

    private UserController() {
    }

    public static UserController getUserController() {
        if(userController == null)
            userController = new UserController();
        return userController;
    }
}
