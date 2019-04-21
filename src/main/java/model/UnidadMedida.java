package model;

public enum UnidadMedida {
    MM("MM"),
    CM("CM"),
    CM2("CM2"),
    CM3("CM3"),
    M("M"),
    M2("M2"),
    M3("M3"),
    L("L"),
    KG("KG"),
    GR("GR"),
    UN("UN");

    private final String name;

    UnidadMedida(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
