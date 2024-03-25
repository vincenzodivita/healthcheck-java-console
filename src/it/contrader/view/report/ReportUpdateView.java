package it.contrader.view.report;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class ReportUpdateView extends AbstractView {

    String choice;
    private Float redBloodCell;
    private Float whiteBloodCell;

    private Float platelets;

    private Float hemoglobin;

    private Integer id;

    private float color;
    private float ph;
    private float protein;
    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Modifica andata a buon fine.\n");
            MainDispatcher.getInstance().callView("HomeReport", null);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("[S]angue [U]rine");
        choice = this.getInput();
        switch (choice.toUpperCase()) {
            case "S":
                System.out.println("Inserisci l'ID del report");
                id = Integer.valueOf((getInput()));
                System.out.println("Inserisci Globuli Rossi");
                redBloodCell = Float.valueOf(getInput());
                System.out.println("Inserisci Globuli Bianchi");
                whiteBloodCell = Float.valueOf(getInput());
                System.out.println("Inserisci Piatrine");
                platelets = Float.valueOf(getInput());
                System.out.println("Inserisci Emoglobina");
                hemoglobin = Float.valueOf(getInput());
                break;
            case "U":
                System.out.println("Inserisci l'ID del report");
                id = Integer.valueOf((getInput()));
                System.out.println("Inserisci il Colore");
                color = Float.parseFloat(getInput());
                System.out.println("Inserisci il PH");
                ph = Float.parseFloat(getInput());
                System.out.println("Inserisci il Proteine");
                protein = Float.parseFloat(getInput());
                System.out.println("Inserisci Emoglobina");
                hemoglobin = Float.parseFloat(getInput());
                break;
            default:
                System.out.println("Azione non disponibile");
                showOptions();
        }

    }

    @Override
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "S":
                BloodTestDTO bloodTestToUpdate= new BloodTestDTO(id, redBloodCell, whiteBloodCell, platelets, hemoglobin);
                request.getBody().put("bloodTestToUpdate", bloodTestToUpdate);
                request.setMethod("update");
                request.setController("BloodTest");
                MainDispatcher.getInstance().callAction(request);
        break;
            case "U":
                UrineTestDTO urineTestToUpdate = new UrineTestDTO(id, color, ph, protein, hemoglobin);
                request.getBody().put("urineTestToUpdate", urineTestToUpdate);
                request.setMethod("update");
                request.setController("UrineTest");
                MainDispatcher.getInstance().callAction(request);
                break;
            default:
                System.out.println("Azione non disponibile");
                showOptions();
        }
    }
}
