package it.contrader.controller;

import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.service.UrineTestService;

import java.util.List;

public class UrineTestController implements Controller {

    private static String sub_package = "report.";

    private final UrineTestService urineTestService;

    public UrineTestController() {

        this.urineTestService = new UrineTestService();
    }

    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                List<UrineTestDTO> urineTestDTO = urineTestService.getAll();
                response.put("urineTests", urineTestDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportGetAll", response);
                break;

            case "read":
                UrineTestDTO urineTestDTO1 = urineTestService.read(Integer.parseInt(request.getBody().get("id").toString()));
                response.put("urineTest", urineTestDTO1);
                MainDispatcher.getInstance().callView(sub_package + "ReportRead", response);
                break;


            case "read_user":
                UrineTestDTO urineTestDTOUser = urineTestService.read_user(Integer.parseInt(request.getBody().get("id").toString()), Integer.parseInt(request.getBody().get("idUser").toString()));
                response.put("urineTest", urineTestDTOUser);
                MainDispatcher.getInstance().callView(sub_package + "ReportRead", response);
                break;


            case "insert" :
                UrineTestDTO urineTestDTO2 = (UrineTestDTO) request.getBody().get("urineTestToInsert");
                urineTestService.insert(urineTestDTO2);
                MainDispatcher.getInstance().callView(sub_package + "ReportInsert", response);
                break;
            case "delete" :
                urineTestService.delete(Integer.parseInt(request.getBody().get("id").toString()));
                MainDispatcher.getInstance().callView(sub_package + "ReportDelete", response);
                break;
            case "update":
                UrineTestDTO urineTestDTO3 = (UrineTestDTO) request.getBody().get("urineTestToUpdate");
                urineTestService.update(urineTestDTO3);
                MainDispatcher.getInstance().callView(sub_package + "ReportUpdate", response);
                break;
            case "validation":
                UrineTestDTO urineTestDTO4 = (UrineTestDTO) request.getBody().get("urineTestToValidate");
                urineTestService.validation(urineTestDTO4);
                MainDispatcher.getInstance().callView(sub_package + "ReportValidation", response);
                break;
            case "getAllAdmin":
                List<UrineTestDTO> urineTestDTO5 = urineTestService.getAll((Integer) request.getBody().get("idAdmin"));
                response.put("urineTests", urineTestDTO5);
                MainDispatcher.getInstance().callView(sub_package + "ReportGetAll", response);
                break;
            case "getAllUser":
                List<UrineTestDTO>  urineTestsUserDTO = urineTestService.getAllUser((Integer) request.getBody().get("idUser"));
                response.put("urineTests", urineTestsUserDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportGetAll", response);
                break;
            case "getAllChecked":
                List<UrineTestDTO> urineTestDTO6 = urineTestService.getAllChecked((Integer) request.getBody().get("idAdmin"));
                response.put("urineTests", urineTestDTO6);
                MainDispatcher.getInstance().callView(sub_package + "ReportValidation", response);
                break;
            case "readIdAdmin":
                int urineTestId = Integer.parseInt(request.getBody().get("id").toString());
                int idAdmin = Integer.parseInt(request.getBody().get("idAdmin").toString());
                UrineTestDTO urineTestDTO7 = urineTestService.readIdAdmin(urineTestId, idAdmin);
                response.put("urineTest", urineTestDTO7);
                MainDispatcher.getInstance().callView(sub_package + "ReportRead", response);
                break;
            default:
                User user = UserSingleton.getInstance();
                System.out.println("\n 404 pagina non trovata");
                if (user.getUsertype().equalsIgnoreCase("super")){
                    MainDispatcher.getInstance().callView("user.HomeSuper", null);
                } else if (user.getUsertype().equalsIgnoreCase("admin")){
                    MainDispatcher.getInstance().callView("user.HomeAdmin", null);
                }else{
                    MainDispatcher.getInstance().callView("user.HomeUser", null);
                }
        }

    }
}
