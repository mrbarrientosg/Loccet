package router;

import base.Injectable;
import controller.ReporteController;
import view.ReporteView;

public class ReporteRouter {
        public static ReporteView create() {
            ReporteView reporteView = Injectable.find(ReporteView.class);
            ReporteRouter router = new ReporteRouter();
            ReporteController controller = Injectable.find(ReporteController.class);

            reporteView.setController(controller);
            reporteView.setRouter(router);

            controller.setView(reporteView);

            return reporteView;
        }
}
