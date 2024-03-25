package it.contrader.view.report;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dao.UrineTestDAO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

public class ReportDeleteView extends AbstractView {

    String choice;
    private int id;
    private Boolean isChecked;

    private Integer idUser;

    private Request request;

    @Override
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("Cancellazione andata a buon fine.\n");
            MainDispatcher.getInstance().callView("HomeReport", null);

        }
    }

    @Override
    public void showOptions() {
        System.out.println("[S]angue [U]rine");
        choice = this.getInput();
        switch (choice.toUpperCase()) {
            case "S":
            case "U":
                System.out.println("Inserisci ID del referto");
                id = Integer.parseInt(getInput());
                break;
            default:
                System.out.println("Azione non disponibile");
                showOptions();
        }
    }

    @Override
    public void submit() {
        request = new Request();

        switch (choice.toUpperCase()) {
            case "S":
                request.getBody().put("id", id);
                request.setController("BloodTest");
                request.setMethod("delete");
                MainDispatcher.getInstance().callAction(request);
                break;
            case "U":
                request = new Request();
                request.getBody().put("id", id);
                request.setController("UrineTest");
                request.setMethod("delete");
                MainDispatcher.getInstance().callAction(request);
                break;
        }

    }
}
