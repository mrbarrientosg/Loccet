package router;

import base.Injectable;
import controller.HomeController;
import controller.TableroController;
import model.Constructora;
import view.TableroView;

public class TableroRouter {

    public static TableroView create(Constructora model) {
        TableroView view = Injectable.find(TableroView.class);
        TableroRouter router = new TableroRouter();
        TableroController controller = new TableroController(view, model, router);

        return view;
    }
}
