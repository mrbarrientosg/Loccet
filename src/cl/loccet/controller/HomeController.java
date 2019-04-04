package cl.loccet.controller;

import cl.loccet.base.Controller;
import cl.loccet.model.Constructora;
import cl.loccet.router.AgregarTrabajadorRouter;
import cl.loccet.router.HomeRouter;
import cl.loccet.view.AgregarTrabajadorView;
import cl.loccet.view.HomeView;

public class HomeController extends Controller {

    private final HomeView view;

    private final Constructora model;

    private final HomeRouter router;

    public HomeController(HomeView view, Constructora model, HomeRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;

        showAgregarTrabajadorView();
    }

    private void showAgregarTrabajadorView() {
        AgregarTrabajadorView ag = AgregarTrabajadorRouter.create(model);
        ag.setMaster(view);
        view.setCenter(ag.getRoot());
    }
}
