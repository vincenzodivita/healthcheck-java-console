package it.contrader.view.report;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.util.List;

public class ReportValidationView extends AbstractView {

    private Integer id;

    private String choice;


    private Boolean isChecked = true;

    @Override
    public void showResults(Response response) {
        if (response != null) {
            if (response.getBody().get("bloodTests") != null) {
                choice = "bloodTest" ;
                System.out.println("\n------------------- Lista Referti ----------------\n");

                List<BloodTestDTO> bloodTests = (List<BloodTestDTO>) response.getBody().get("bloodTests");
                for (BloodTestDTO test : bloodTests) {
                    System.out.println(test);
                }
                System.out.println("----------------------------------------------------\n");

            } else if (response.getBody().get("urineTests") != null) {
                choice = "urineTest" ;
                System.out.println("\n------------------- Lista Referti ----------------\n");
                List<UrineTestDTO> urineTest = (List<UrineTestDTO>) response.getBody().get("urineTests");
                for (UrineTestDTO test : urineTest) {
                    System.out.println(test);
                }
                System.out.println("----------------------------------------------------\n");
            } else {
                System.out.println("Validazione andata a buon fine");
                MainDispatcher.getInstance().callView("HomeReport", null);

            }
        }
    }
    @Override
    public void showOptions() {
                System.out.println("Inserisci l'ID da validare");
                id = Integer.valueOf(getInput());
        }

    @Override
    public void submit() {
        Request request = new Request();

        if (choice.equals("bloodTest")) {

            BloodTestDTO bloodTestToValidate = new BloodTestDTO(id, isChecked);
            request.getBody().put("bloodTestToValidate", bloodTestToValidate);
            request.setMethod("validation");
            request.setController("BloodTest");
            MainDispatcher.getInstance().callAction(request);
        } else {

            UrineTestDTO urineTestToValidate = new UrineTestDTO(id, isChecked);
            request.getBody().put("urineTestToValidate", urineTestToValidate);
            request.setMethod("validation");
            request.setController("UrineTest");
            MainDispatcher.getInstance().callAction(request);
        }
    }
}