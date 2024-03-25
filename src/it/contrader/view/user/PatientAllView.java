package it.contrader.view.user;

import it.contrader.controller.Response;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.RegistryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.util.List;

public class PatientAllView extends AbstractView {
    @Override
    public void showResults(Response response) {
        if (response != null){
            System.out.println("\n------------------- Lista Pazienti ----------------\n");

            List<RegistryDTO> patients = (List<RegistryDTO>) response.getBody().get("patients");
            for (RegistryDTO patient: patients){
                System.out.println(patient.getIdUser() + "\t\t" + patient.getName()+ "\t\t" + patient.getSurname());
            }
        }
        System.out.println("----------------------------------------------------\n");
        MainDispatcher.getInstance().callView("HomeAdmin", null);
    }

    @Override
    public void showOptions() {

    }

    @Override
    public void submit() {

    }
}
