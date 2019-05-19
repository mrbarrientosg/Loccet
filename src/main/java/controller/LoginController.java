package controller;

import base.Controller;
import com.google.gson.JsonObject;
import exceptions.EmptyFieldsException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import network.LoccetService;
import network.NetworkException;
import router.LoginRouter;
import util.FakeData;
import util.NodeUtils;
import view.LoginView;
import view.TableroView;

import java.io.IOException;

public final class LoginController extends Controller {

    private LoginView view;

    private LoccetService service = LoccetService.getInstace();

    public void setView(LoginView view) {
        this.view = view;
    }

    /**
     * Inicia sesion
     */
    public boolean loginUser() throws EmptyFieldsException, IOException, NetworkException {

        if (view.getUsername().isEmpty() || view.getPassword().isEmpty() || view.getDNS().isEmpty()) {
            throw new EmptyFieldsException();
        }

        JsonObject parameters = new JsonObject();
        parameters.addProperty("username", view.getUsername());
        parameters.addProperty("password", view.getPassword());
        parameters.addProperty("dns", view.getDNS());

        JsonObject result = service.login(parameters).getAsJsonObject();

        return result.get("login").getAsBoolean();
    }


}
