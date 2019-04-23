package base;

public abstract class View extends UIComponent implements Instance {

    public View() { super(null); }

    public View(String title) {
        super(title);
    }
}
