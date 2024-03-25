package it.contrader.view.registry;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.RegistryDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

public class UpdateRegistryView extends AbstractView {

    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String nationality;
    private String city;
    private String address;
    private String cf;

    @Override
    public void showResults(Response response) {
        if(response!=null)
        {
            System.out.println("L'anagrafica è stata modificata");
            MainDispatcher.getInstance().callView("HomeRegistry",null);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci nome dell'utente:");
        name = checkLenghtField("nome", 20);
        System.out.println("Inserisci cognome dell'utente:");
        surname = checkLenghtField("cognome", 20);
        System.out.println("Inserisci data di nascita dell'utente:");
        birthDate = checkLenghtField("data di nascita", 10);
        System.out.println("Inserisci email dell'utente:");
        email = checkLenghtField("email", 30);
        System.out.println("Inserisci paese dell'utente:");
        nationality =checkLenghtField("paese", 2);
        System.out.println("Inserisci città dell'utente:");
        city = checkLenghtField("città", 30);
        System.out.println("Inserisci indirizzo dell'utente:");
        address = checkLenghtField("indirizzo", 30);
        System.out.println("Inserisci cf dell'utente:");
        cf = checkLenghtField("cf", 16);
    }

    @Override
    public void submit() {

        Request request = new Request();
        User user = UserSingleton.getInstance();
        RegistryDTO registryToUpdate = new RegistryDTO(name, surname, birthDate, email, nationality,city,address,cf, user.getId());
        request.getBody().put("registryToUpdate", registryToUpdate);
        request.setMethod("update");
        request.setController("Registry");

        MainDispatcher.getInstance().callAction(request);

    }
}
