package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

public class UserDeleteView extends AbstractView {
	private Request request;
	private int id;
	private String scelta;
	User user = UserSingleton.getInstance();
	private final String mode = "DELETE";

	public UserDeleteView() {
	}

	/**
	 * Se la request non ï¿½ nulla (ovvero se si arriva dalla mode DELETE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Response response) {
		if (response!=null) {
			System.out.println("Cancellazione andata a buon fine.\n");
			if (user.getUsertype().toLowerCase() == "super") {
				Request request = new Request("User", "getAll", null);
				MainDispatcher.getInstance().callAction(request);
			}
			else {
				MainDispatcher.getInstance().callView("Login", null);
			}
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da cancellare
	 */
	@Override
	public void showOptions() {
		if(user.getUsertype().equals("super")) {
			System.out.println("Inserisci id dell'utente:");
			id = Integer.parseInt(getInput());
		} else {
			System.out.println("Sei sicuro di voler cancellare il tuo account?");
			System.out.println("[S]i, [N]o");
			scelta = getInput();
			switch (scelta.toUpperCase()) {
				case "S":
					id = user.getId();
					break;
				case "N":
					if(user.getUsertype().equalsIgnoreCase("admin")) {
						MainDispatcher.getInstance().callView("HomeAdmin", null);
					} else {
						MainDispatcher.getInstance().callView("HomeUser", null);
					}
					break;
				default:
					System.out.println("Scelta non disponibile");
					MainDispatcher.getInstance().callView("UserDelete", null);
			}

		}
	}

	/**
	 * impacchetta la request con l'id dell'utente da cancellare
	 */
	@Override
	public void submit() {
		request = new Request();
		request.getBody().put("id", id);
		request.setController("User");
		request.setMethod("delete");
		MainDispatcher.getInstance().callAction(request);
		MainDispatcher.getInstance().callView("Login", null);
	}


}