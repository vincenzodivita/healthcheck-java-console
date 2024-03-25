package it.contrader.view.report;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

public class ReportReadView extends AbstractView {

    String choice;

    private Integer id;

    User user = UserSingleton.getInstance();

    @Override
    public void showResults(Response response) {
        if (response!= null ){

           if (response.getBody().get("bloodTest") != null) {
                BloodTestDTO bloodTest = (BloodTestDTO) response.getBody().get("bloodTest");
                System.out.println(bloodTest);
                MainDispatcher.getInstance().callView("HomeReport", null);
            } else {
                UrineTestDTO urineTest = (UrineTestDTO) response.getBody().get("urineTest");
                System.out.println(urineTest);
                MainDispatcher.getInstance().callView("HomeReport", null);
            }
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Scegli quale tipo di referto vuoi leggere:");
        System.out.println("[S]angue [U]rine");
        choice = this.getInput();
        switch (choice.toUpperCase()) {
            case "S":
            case "U":
                System.out.println("Inserisci l'ID del report:");
                id = Integer.parseInt(getInput());
                break;
            default:
                System.out.println("Opzione non disponible");
                showOptions();
                break;
        }

    }

    @Override
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "S":
               if (user.getUsertype().equals("super")){
                   request.getBody().put("id", id);
                   request.setController("BloodTest");
                   request.setMethod("read");
                   MainDispatcher.getInstance().callAction(request);
               } else if (user.getUsertype().equals("admin")) {
                   request.getBody().put("id", id);
                   request.getBody().put("idAdmin", user.getId());
                   request.setController("BloodTest");
                   request.setMethod("readAdmin");
                   MainDispatcher.getInstance().callAction(request);
               }else {
                   request.getBody().put("id", id);
                   request.getBody().put("idUser", user.getId());
                   request.setController("BloodTest");
                   request.setMethod("readUser");
                   MainDispatcher.getInstance().callAction(request);
               }
                break;
            case "U":
                if (user.getUsertype().equals("super")){
                    request.getBody().put("id", id);
                    request.setController("UrineTest");
                    request.setMethod("read");
                    MainDispatcher.getInstance().callAction(request);
                } else if (user.getUsertype().equals("admin")) {
                    request.getBody().put("id", id);
                    request.getBody().put("idAdmin", user.getId());
                    request.setController("UrineTest");
                    request.setMethod("readIdAdmin");
                    MainDispatcher.getInstance().callAction(request);
                }else {
                    request.getBody().put("id", id);
                    request.getBody().put("idUser", user.getId());
                    request.setController("UrineTest");
                    request.setMethod("read_user");
                    MainDispatcher.getInstance().callAction(request);
                }


                break;
        }
    }
}
