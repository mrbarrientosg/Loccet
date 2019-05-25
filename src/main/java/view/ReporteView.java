package view;
import base.View;
import controller.ReporteController;
import router.ReporteRouter;

public class ReporteView extends View {

    private ReporteController controller;
    private ReporteRouter router;

    @Override
    public void viewDidLoad() {

    }
    public void setController(ReporteController controller){this.controller = controller;}
    public void setRouter(ReporteRouter router){this.router = router;}
}
