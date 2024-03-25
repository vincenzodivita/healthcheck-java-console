package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;

public class HomeReportView extends AbstractView{

    User user = UserSingleton.getInstance();
    String choice;
    @Override
    public void showResults(Response response) {

    }

    @Override
    public void showOptions() {
        if (user.getUsertype().equalsIgnoreCase("user")){
            System.out.println("-------------REFERTI------------\n");
            System.out.println("[I]nserisci [C]erca [M]odifica [V]isualizzaReferti [E]limina [B]ack");
            choice = this.getInput();
        } else {
            System.out.println("-------------REFERTI------------\n");
            System.out.println("[I]nserisci [C]erca [M]odifica [E]limina [V]isualizzaReferti [VA]lidazione [B]ack");
            choice = this.getInput();

        }


    }

    @Override
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "I":
                request.setController("User");
                request.setMethod("getAllDoctors");
                MainDispatcher.getInstance().callAction(request);
                //MainDispatcher.getInstance().callView("report.ReportInsert", null);
                break;
            case "C":
                MainDispatcher.getInstance().callView("report.ReportRead", null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("report.ReportUpdate", null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("report.ReportDelete", null);
                break;
            case "B":
                if(user.getUsertype().equalsIgnoreCase("super")) {
                    MainDispatcher.getInstance().callView("HomeSuper", null);
                } else if (user.getUsertype().equalsIgnoreCase("admin")) {
                    MainDispatcher.getInstance().callView("HomeAdmin", null);
                }else {
                    MainDispatcher.getInstance().callView("HomeUser", null);
                }
                break;
            case "V":
                System.out.println("[S]angue [U]rine");
                choice = getInput();
                switch (choice.toUpperCase()) {
                    case "S":

                        if (user.getUsertype().equalsIgnoreCase("admin")) {

                            request.getBody().put("idAdmin", user.getId());
                            request.setController("BloodTest");
                            request.setMethod("getAllAdmin");
                            MainDispatcher.getInstance().callAction(request);

                        } else if(user.getUsertype().equalsIgnoreCase("super")) {
                            request.setController("BloodTest");
                            request.setMethod("getAll");

                            MainDispatcher.getInstance().callAction(request);
                        }
                        break;
                    case "U":

                        if (user.getUsertype().equalsIgnoreCase("admin")) {

                            request.getBody().put("idAdmin", user.getId());
                            request.setController("UrineTest");
                            request.setMethod("getAllAdmin");
                            MainDispatcher.getInstance().callAction(request);

                        } else if(user.getUsertype().equalsIgnoreCase("super")) {
                            request.setController("UrineTest");
                            request.setMethod("getAll");

                            MainDispatcher.getInstance().callAction(request);
                        }
                        break;
                }
           case "VA":
               System.out.println("[S]angue [U]rine");
               choice = getInput();
               switch (choice.toUpperCase()) {
               case "S":
                   request.getBody().put("idAdmin", user.getId());
                   request.setController("BloodTest");
                   request.setMethod("getAllChecked");
                   MainDispatcher.getInstance().callAction(request);
                   break;
               case "U":
                   request.getBody().put("idAdmin", user.getId());
                   request.setController("UrineTest");
                   request.setMethod("getAllChecked");
                   MainDispatcher.getInstance().callAction(request);
                   break;
           }
               break;
            default:
                notAvaiableAction();
                showOptions();
                submit();
        }
    }
}
