package it.contrader.controller;

import it.contrader.dto.BloodTestDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.service.BloodTestService;

import java.util.List;

public class BloodTestController implements Controller{
    private final BloodTestService service;
    private static String sub_package = "report.";


    public BloodTestController(){
        this.service=new BloodTestService();
    }
    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                List<BloodTestDTO> bloodTestsDTO = service.getAll();
                response.put("bloodTests", bloodTestsDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportGetAll", response);
                break;
            case "read":
                BloodTestDTO bloodTestDTO = service.read(Integer.parseInt(request.getBody().get("id").toString()));
                response.put("bloodTest", bloodTestDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportRead", response);
                break;
            case "readUser":
                BloodTestDTO bloodTestUserDTO = service.readUser(Integer.parseInt(request.getBody().get("id").toString()),Integer.parseInt(request.getBody().get("idUser").toString()) );
                response.put("bloodTest", bloodTestUserDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportRead", response);
                break;
            case "readAdmin":
                BloodTestDTO bloodTestAdminDTO = service.readAdmin(Integer.parseInt(request.getBody().get("id").toString()),Integer.parseInt(request.getBody().get("idAdmin").toString()) );
                response.put("bloodTest", bloodTestAdminDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportRead", response);
                break;
            case "insert":
                BloodTestDTO bloodTestToInsert = (BloodTestDTO) request.getBody().get("bloodTestToInsert");
                service.insert(bloodTestToInsert);
                MainDispatcher.getInstance().callView(sub_package + "ReportInsert", response);
                break;
            case "delete":
                service.delete(Integer.parseInt(request.getBody().get("id").toString()));
                MainDispatcher.getInstance().callView(sub_package + "ReportDelete", response);
                break;
            case "update":
                BloodTestDTO bloodTestToUpdate = (BloodTestDTO) request.getBody().get("bloodTestToUpdate");
                service.update(bloodTestToUpdate);
                MainDispatcher.getInstance().callView(sub_package + "ReportUpdate", response);
                break;
            case "validation":
                BloodTestDTO bloodTestToValidate = (BloodTestDTO) request.getBody().get("bloodTestToValidate");
                service.validation(bloodTestToValidate);
                MainDispatcher.getInstance().callView(sub_package + "ReportValidation", response);
                break;
            case "getAllAdmin":
                List<BloodTestDTO> bloodTestsAdminDTO = service.getAll((Integer) request.getBody().get("idAdmin"));
                response.put("bloodTests", bloodTestsAdminDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportGetAll", response);
                break;
            case "getAllUser":
                List<BloodTestDTO>  bloodTestsUserDTO = service.getAllUser((Integer) request.getBody().get("idUser"));
                response.put("bloodTests", bloodTestsUserDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportGetAll", response);
                break;
            case "getAllChecked":
                List<BloodTestDTO> bloodTestsValidationDTO = service.getAllChecked((Integer) request.getBody().get("idAdmin"));
                response.put("bloodTests", bloodTestsValidationDTO);
                MainDispatcher.getInstance().callView(sub_package + "ReportValidation", response);
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
