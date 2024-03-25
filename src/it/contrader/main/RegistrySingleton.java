package it.contrader.main;

import it.contrader.model.Registry;
import it.contrader.model.User;

public class RegistrySingleton {
    private static Registry instance = new Registry();
    private RegistrySingleton() {
    }


    public static Registry getInstance() {
        if (instance == null) {
            instance = new Registry();
        }

        return instance;
    }

    public static void setInstance(RegistrySingleton instance) {

    }
}