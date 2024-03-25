package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;

/**
 * A representation of the Admin's Home page.
 * Extends AbstractView and contains methods to display results, show options, and submit user inputs.
 */
public class HomeAdminView extends AbstractView {
    User user = UserSingleton.getInstance();
    private String choice;
    @Override
    public void showResults(Response response) {

    }

    @Override
    public void showOptions() {
        System.out.println("-------------MENU------------\n");
        System.out.println(" Seleziona cosa vuoi gestire:");
        System.out.println("[C]ancella il profilo, [R]eferti [P]azienti [A]nagrafica [E]sci");
        choice = this.getInput();
    }

    @Override
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "C":
                MainDispatcher.getInstance().callView("user.UserDelete", null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Login", null);
                break;
            case "R":
                MainDispatcher.getInstance().callView("HomeReport", null);
                break;
            case "P":
                request.getBody().put("idAdmin", user.getId());
                request.setController("Registry");
                request.setMethod("getAllPatient");
                MainDispatcher.getInstance().callAction(request);
            case "A":
                MainDispatcher.getInstance().callView("HomeRegistry",null);
                break;
            default:
                notAvaiableAction();
                showOptions();
                submit();
        }
    }
}
