package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;

public class HomeSuperView extends AbstractView{
    private String choice;

    /**
     * Displays a welcome message if the response object is not null.
     *
     * @param response The results of a previous operation.
     */
    public void showResults(Response response) {
        if (response != null) {
        getWelcomeMessage(response.getBody().get("username").toString());
        }
    }

    /**
     * Presents the available options to the user through console.
     * User can choose to manage Users ('U') or Exit ('E').
     */
    public void showOptions() {
        System.out.println("-------------MENU------------\n");
        System.out.println(" Seleziona cosa vuoi gestire:");
        System.out.println(" [R]eferti [U]tenti [E]sci");
        choice = this.getInput();
    }

    /**
     * Based on user's choice, it creates and sends a request to appropriate controller via the Dispatcher.
     * If an invalid option is chosen, it prompts the user to choose again.
     */
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "U":
                request.setController("User");
                request.setMethod("getAll");
                MainDispatcher.getInstance().callAction(request);
                break;
            case "R":
                MainDispatcher.getInstance().callView("HomeReport",null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Login", null);
                break;
            default:
                notAvaiableAction();
                showOptions();
                submit();
        }
    }
}
