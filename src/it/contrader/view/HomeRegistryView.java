package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;

public class HomeRegistryView extends AbstractView {
    String choice;


    @Override
    public void showResults(Response response) {

    }

    @Override
    public void showOptions() {
        System.out.println("-------------ANAGRAFICA------------\n");
        System.out.println("[V]isualizza [M]odifica [B]ack");
        choice = this.getInput();
    }

    @Override
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "V":
                MainDispatcher.getInstance().callView("registry.ReadRegistry", null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("registry.UpdateRegistry", null);
                break;
            case "B":
                MainDispatcher.getInstance().callView("HomeUser", null);
                break;
            default:
                notAvaiableAction();
                showOptions();
                submit();

        }
    }
}