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

    public TableroController(TableroView view, Constructora model, TableroRouter router) {
        this.view = view;
        this.model = model;
        this.router = router;
    }
}
