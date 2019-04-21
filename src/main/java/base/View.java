package base;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class View extends UIComponent implements Instance {

    public View() { super(null); }

    public View(String title) {
        super(title);
    }
}
