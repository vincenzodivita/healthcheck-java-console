package it.contrader.view.report;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

import java.util.List;

public class ReportGetAllView extends AbstractView {

    User user = UserSingleton.getInstance();
    private Integer idAdmin;
    private String choice;
    @Override
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("\n------------------- Lista Referti ----------------\n");
            List<BloodTestDTO> bloodTests = (List<BloodTestDTO>) response.getBody().get("bloodTests");
            if (bloodTests != null) {
                for (BloodTestDTO test : bloodTests) {
                    System.out.println(test);
                }
            } else {
                List<UrineTestDTO> urineTests = (List<UrineTestDTO>) response.getBody().get("urineTests");
                if (urineTests != null) {
                    for (UrineTestDTO test : urineTests) {
                        System.out.println(test);
                    }
                }

            }
            System.out.println("----------------------------------------------------\n");
            MainDispatcher.getInstance().callView("HomeReport", null);
        }

    }
    @Override
    public void showOptions() {

    }

    @Override
    public void submit() {

    }
}
