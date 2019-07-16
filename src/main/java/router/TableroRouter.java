package router;

import base.Injectable;
import controller.TableroController;
import view.TableroView;

public class TableroRouter {

    public static TableroView create() {
        TableroView view = Injectable.find(TableroView.class);
        TableroRouter router = new TableroRouter();
        TableroController controller = new TableroController(view, router);

        return view;
    }
}
