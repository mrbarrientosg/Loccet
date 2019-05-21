package controller;

import base.Controller;
import model.Constructora;
import router.HomeRouter;
import router.TableroRouter;
import view.HomeView;
import view.TableroView;

public class TableroController extends Controller {

    private final TableroView view;

    private final Constructora model;

    private final TableroRouter router;

    public TableroController(TableroView view, TableroRouter router) {
        this.view = view;
        this.model = Constructora.getInstance();
        this.router = router;
    }
}
