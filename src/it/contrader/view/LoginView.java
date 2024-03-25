package it.contrader.view;

//import com.sun.tools.javac.Main;
import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.LoginDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginView extends AbstractView {

	private String choice;

	public void showResults(Response response) {
		if (response != null) {
			loginSubmit("login");
			killApp();
		}
	}
	public void showOptions() {
		getWelcomeMessage();
		this.choice = getWelcomeOptions();
	}
	public void submit() {
		switch (choice.toUpperCase()) {
			case "A":
				loginSubmit("login");
				break;
			case "R":
				User user = UserSingleton.getInstance();
				user.setUsertype("user");
				loginSubmit("register");
				break;
			case "C":
				killApp();
				break;
			default:
				notAvaiableAction();
				showOptions();
				submit();
		}
	}
}
