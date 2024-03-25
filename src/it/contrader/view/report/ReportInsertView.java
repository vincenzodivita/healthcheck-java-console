package it.contrader.view.report;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;
import it.contrader.dto.UrineTestDTO;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ReportInsertView  extends AbstractView {

    String choice;
    private Float redBloodCell;
    private Float whiteBloodCell;

    private Float platelets;

    private Float hemoglobin;

    private Integer idAdmin;
    private Integer idUser;


    private int id;
    private float color;
    private float ph;
    private float protein;








    @Override
    public void showResults(Response response) {
        if (response!=null && response.getBody().get("doctors") != null){
            System.out.println("\n------------------- Lista Dottori ----------------\n");

            List<UserDTO> doctors = (List<UserDTO>)response.getBody().get("doctors");
            for (UserDTO test : doctors){
                System.out.println(test.getId() + "\t\t" + test.getUsername());
            }
        } else{
            System.out.println("Inserimento andato a buon fine!");
            MainDispatcher.getInstance().callView("HomeReport", null);
        }

    }

    @Override
    public void showOptions() {
        User user = UserSingleton.getInstance();
        System.out.println("[S]angue [U]rine");
        choice = this.getInput();
        switch (choice.toUpperCase()) {
            case "S":
                System.out.println("Inserisci Globuli Rossi");
                redBloodCell = Float.valueOf(getInput());
                System.out.println("Inserisci Globuli Bianchi");
                whiteBloodCell = Float.valueOf(getInput());
                System.out.println("Inserisci Piastrine");
                platelets = Float.valueOf(getInput());
                System.out.println("Inserisci Emoglobina");
                hemoglobin = Float.valueOf(getInput());
                System.out.println("Inserisci ID del dottore");
                idAdmin = Integer.valueOf((getInput()));
                idUser = user.getId();
                break;
            case "U":
                System.out.println("Inserisci il Colore");
                color = Float.parseFloat(getInput());
                System.out.println("Inserisci il PH");
                ph = Float.parseFloat(getInput());
                System.out.println("Inserisci il Proteine");
                protein = Float.parseFloat(getInput());
                System.out.println("Inserisci Emoglobina");
                hemoglobin = Float.parseFloat(getInput());
                System.out.println("Inserisci ID del dottore");
                idAdmin = Integer.valueOf((getInput()));
                break;
            default:
                System.out.println("Azione non disponibile");
                showOptions();
        }
    }

    @Override
    public void submit() {
        Request request = new Request();
        User user = UserSingleton.getInstance();
        switch (choice.toUpperCase()) {
            case "S":
                BloodTestDTO bloodTestToInsert = new BloodTestDTO(redBloodCell, whiteBloodCell, platelets, hemoglobin, idAdmin, idUser);
                request.getBody().put("bloodTestToInsert", bloodTestToInsert);
                request.setMethod("insert");
                request.setController("BloodTest");
                MainDispatcher.getInstance().callAction(request);
                break;
            case "U":
                UrineTestDTO urineTestToInsert = new UrineTestDTO(color, ph, protein, hemoglobin, idAdmin, user.getId());
                request.getBody().put("urineTestToInsert", urineTestToInsert);
                request.setMethod("insert");
                request.setController("UrineTest");
                MainDispatcher.getInstance().callAction(request);



                break;
            default:
                System.out.println("Azione non disponibile");
                showOptions();
        }
    }
}
