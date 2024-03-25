package it.contrader.controller;


import it.contrader.dto.BloodTestDTO;
import com.sun.tools.javac.Main;
import it.contrader.dto.RegistryDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.RegistrySingleton;
import it.contrader.main.UserSingleton;
import it.contrader.model.Registry;
import it.contrader.model.User;
import it.contrader.service.RegistryService;
import it.contrader.service.UserService;

import java.util.List;

public class RegistryController implements Controller{


    /**
     * definisce il pacchetto di vista user.
     */

    private final RegistryService RegistryService;

    User user = UserSingleton.getInstance();
    /**
     * Costruisce un oggetto di tipo UserService per poterne usare i metodi
     */
    public RegistryController() {
        this.RegistryService = new RegistryService();
    }


    /**
     * Metodo dell'interfaccia Controller. Estrae dalla request la mode
     * (che riceve dalle view specifiche e pu� essere la richesta di una
     * scelta da parte del registry "GETCHOICE") e la scelta del registry.
     * <p>
     * Se la modalit� corrisponde ad una CRUD il controller chiama i service,
     * altrimenti rimanda alla View della CRUD per richiedere i parametri
     */
    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "insert":
                RegistryDTO registrytoinsert = (RegistryDTO) request.getBody().get("registryToInsert");
                RegistryService.insert(registrytoinsert);
                break;
            case "getAllPatient":
                List<RegistryDTO> patientsDTO = RegistryService.getAllPatient((Integer) request.getBody().get("idAdmin"));
                response.put("patients", patientsDTO);
                MainDispatcher.getInstance().callView("user.PatientAll", response);
            case "read":
                RegistryDTO registryDTO = RegistryService.read(Integer.parseInt(request.getBody().get("idUser").toString()));
                response.put("registry", registryDTO);
                MainDispatcher.getInstance().callView("registry.ReadRegistry", response);
                break;
            case "update":
                RegistryDTO registryToUpdate = (RegistryDTO) request.getBody().get("registryToUpdate");
                RegistryService.update(registryToUpdate);
                MainDispatcher.getInstance().callView("registry.UpdateRegistry", response);
                if (user.getUsertype().equalsIgnoreCase("user")) {
                    MainDispatcher.getInstance().callView("Login", response);
                } else if (user.getUsertype().equalsIgnoreCase("super")){
                    MainDispatcher.getInstance().callView("HomeSuper", null);
                }
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView("HomeAdmin", null);
        }
    }
}
