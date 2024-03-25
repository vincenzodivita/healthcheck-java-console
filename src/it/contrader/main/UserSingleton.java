package it.contrader.main;

import it.contrader.model.User;

public class UserSingleton {
    private static User instance;
    public UserSingleton() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }

        return instance;
    }

    public static void setInstance(UserSingleton instance) {

    }
}
