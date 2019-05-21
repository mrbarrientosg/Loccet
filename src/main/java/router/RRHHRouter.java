package router;

import base.Injectable;
import controller.RRHHController;
import view.RRHHView;

public class RRHHRouter {

    public static RRHHView create() {
        RRHHView rrhhView = Injectable.find(RRHHView.class);
        RRHHRouter router = new RRHHRouter();
        RRHHController controller = Injectable.find(RRHHController.class);

        rrhhView.setController(controller);
        rrhhView.setRouter(router);

        controller.setView(rrhhView);

        return rrhhView;
    }
}
