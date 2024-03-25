package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;

public class HomeUserView extends AbstractView{

	String choice;

	@Override
	public void showResults(Response response) {
		if(response!=null) {
			getWelcomeMessage(response.getBody().get("username").toString());
		}
	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("Quale scelta vuoi fare?");
		System.out.println("[C]ancella [R]eferti [A]nagrafica [E]sci");
		choice = this.getInput();
	}

	@Override
	public void submit() {
		switch (choice.toUpperCase()) {
			case "C":
				MainDispatcher.getInstance().callView("user.UserDelete", null);
				break;
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;
            case "R":
                MainDispatcher.getInstance().callView("HomeReport", null);
                break;
			case "A":
				MainDispatcher.getInstance().callView("HomeRegistry",null);
				break;
			default:
				notAvaiableAction();
				showOptions();
				submit();

		}
	}

}
