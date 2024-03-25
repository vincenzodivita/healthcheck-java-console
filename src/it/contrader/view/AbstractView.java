package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.dto.LoginDTO;
import it.contrader.main.MainDispatcher;

import java.util.Map;
import java.util.Scanner;

public abstract class AbstractView implements View {
    private final String appName = "HealthCare";
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void getWelcomeMessage(){
        System.out.println("\nBenvenuto in " + this.appName);
    }
    public void getWelcomeMessage(String username){
        System.out.println("\nBenvenuto in " + this.appName + " " + username);
    }


    public String getWelcomeOptions(){
        System.out.println("\tSeleziona cosa vuoi fare:");
        System.out.println("\t[A]ccedi  [R]egistrati  [C]hiudi app");
        return getInput();
    }

    public LoginDTO getLoginView(Boolean bool){
        String msgString = bool ? "ACCEDI" : "REGISTRAZIONE";
        System.out.println("----- .:" + msgString +":. ----");

        String usernameString = bool ? "Username:" : "Inserisci username dell'utente:";
        System.out.println(usernameString);
        String username = checkLenghtField("username", 20);

        String passwordString = bool ? "Password:" : "Inserisci password dell'utente:";
        System.out.println(passwordString);
        String password = checkLenghtField("password", 20);

        return new LoginDTO(username, password);
    }

    public void loginSubmit(String method){
        Request request = new Request();
        if (method.equals("login")) {
            LoginDTO loginDTO = getLoginView(true);
            request.getBody().put("loginDTO", loginDTO);
        }
        request.setController("Home");
        request.setMethod(method);
        MainDispatcher.getInstance().callAction(request);
    }

    public void killApp(){
        System.exit('0');
    }

    public String checkLenghtField(String field, int lenght){
        String string;
        boolean checkLenght;
        do {
            string = getInput();
            checkLenght = string.length() > lenght;
            if (checkLenght) {
                System.out.println("Il campo " + field +" non può essere più lungo di " + lenght +" caratteri." +
                        "\nInserisci nuovamente il campo " + field +":");
            }
        } while	(checkLenght);
        string = isEmptyField(string, field);
        return string;
    }

    public String isEmptyField(String string, String field){
        boolean isEmpty;
        isEmpty = string.isEmpty();
        while(isEmpty) {
            System.out.println("Il campo " + field +" non può essere vuoto." +
                    "\nInserisci nuovamente il campo " + field +":");
            string = getInput();
            isEmpty =  string.isEmpty();
        }
        return string;
    }
    public void notAvaiableAction(){
        System.out.println("Azione non disponibile");
    }
    public String askUsertype(){
        System.out.println("Inserisci tipo dell'utente:");
        System.out.println("[A]dmin  [U]ser");
        String choice = getInput();
        switch (choice.toUpperCase()) {
            case "A":
                choice = "admin";
                break;
            case "U":
                choice = "user";
                break;
            default:
                notAvaiableAction();
                askUsertype();
        }
        return choice;
    }
}