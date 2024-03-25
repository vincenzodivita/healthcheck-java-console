package it.contrader.view.registry;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.RegistryDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

public class ReadRegistryView extends AbstractView {
    private int id;
    User user = UserSingleton.getInstance();
    public ReadRegistryView() {

    }
    @Override
    public void showResults(Response response) {
        if (response != null) {
            RegistryDTO registry = (RegistryDTO) response.getBody().get("registry");
            System.out.println("Nome\tCognome\tData di nascita\tEmail\tNazionalità\tCittà\tIndirizzo\tCF");
            System.out.print(registry.getName() + "\t");
            System.out.print(registry.getSurname() + "\t");
            System.out.print(registry.getBirthDate() + "\t");
            System.out.print(registry.getEmail() + "\t");
            System.out.print(registry.getNationality() + "\t");
            System.out.print(registry.getCity() + "\t");
            System.out.print(registry.getAddress() + "\t");
            System.out.println(registry.getCf() + "\t");
            MainDispatcher.getInstance().callView("HomeRegistry",null);
        }
    }

    @Override
    public void showOptions() {
        id = user.getId();
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.getBody().put("idUser", id);
        request.setController("Registry");
        request.setMethod("read");
        MainDispatcher.getInstance().callAction(request);
    }
}
