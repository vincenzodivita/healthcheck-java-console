package it.contrader.view.user;

//import com.sun.tools.javac.Main;
import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.view.AbstractView;

public class UserInsertView extends AbstractView {
	private String username;
	private String password;
	private String usertype = "user";
	User user = UserSingleton.getInstance();
	LoginDTO draft;
	public UserInsertView() {
	}
	
	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Response response) {
		if (response!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("user.InsertRegistry",null);
			MainDispatcher.getInstance().callView("Login", response);
			if (user.getUsertype() != null) {
				if (user.getUsertype().equalsIgnoreCase("super")) {
					Request request = new Request("User", "getAll", null);
					MainDispatcher.getInstance().callAction(request);
				}
			} else {
				System.out.println("Adesso puoi eseguire l'accesso:\n");
				MainDispatcher.getInstance().callView("Login", response);
			}

		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {

		draft = getLoginView(false);
		if (user.getUsertype() != null) {
			if (user.getUsertype().equalsIgnoreCase("super")) {
				usertype = askUsertype();
			}
		}
	}

	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		Request request = new Request();
		UserDTO userToInsert = new UserDTO(draft.getUsername(), draft.getPassword(), usertype);
		request.getBody().put("userToInsert", userToInsert);
		request.setMethod("insert");
		request.setController("User");
		MainDispatcher.getInstance().callAction(request);
	}


}
