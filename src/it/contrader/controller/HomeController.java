package it.contrader.controller;

import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.main.UserSingleton;
import it.contrader.model.User;
import it.contrader.service.LoginService;
import it.contrader.service.UserService;

import java.util.HashMap;
import java.util.Map;


/**
 * Controller implementation for handling user authentication requests.
 * This Controller interacts with the LoginService to authenticate user credentials.
 */
public class HomeController implements Controller {

    private final LoginService loginService;

    private final UserService userService;

    /**
     * Constructs a HomeController with a new instance of LoginService.
     */
    public HomeController() {
        loginService = new LoginService();
        userService = new UserService();
    }

    /**
     * Implements the method from Controller interface. Unpacks a request, extracts the username and password,
     * and uses LoginService to authenticate the user. Depending on the user type, it redirects to the corresponding view.
     * If authentication fails, it redirects back to the Login view.
     *
     * @param request A Request object that encapsulates the user's request.
     */
    public void doControl(Request request) {
        if (request != null) {
            User user = UserSingleton.getInstance();
            switch (request.getMethod().toUpperCase()) {
                case "LOGIN":
                    UserDTO userDTO = loginService.login((LoginDTO) request.getBody().get("loginDTO"));
                    if (userDTO != null) {
                        Response response = new Response();
                        response.put("username", userDTO.getUsername());
                        user.setId(userDTO.getId());
                        user.setUsertype(userDTO.getUsertype());
                        switch (userDTO.getUsertype().toUpperCase()) {
                            case "SUPER":
                                MainDispatcher.getInstance().callView("HomeSuper", response);
                                break;
                            case "ADMIN":
                                MainDispatcher.getInstance().callView("HomeAdmin", response);
                                break;
                            case "USER":
                                MainDispatcher.getInstance().callView("HomeUser", response);
                                break;
                            default:
                                MainDispatcher.getInstance().callView("Login", null);
                                break;
                        }
                    } else {
                        System.out.println("Invalid credentials");
                        MainDispatcher.getInstance().callView("Login", null);
                    }
                    break;
                case "REGISTER":
                    MainDispatcher.getInstance().callView("user.UserInsert", null);

                    break;
                default:
                    System.out.println("No method matches the inserted method: " + request.getMethod());


            }
        } else MainDispatcher.getInstance().callView("Login", null);
    }
}

