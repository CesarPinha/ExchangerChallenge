public enum Moneda {
    USD(1, "USD", "Dólares (USD)"),
    MXN(2, "MXN", "Peso mexicano (MXN)"),
    COP(3, "COP", "Peso colombiano (COP)"),
    BRL(4, "BRL", "Real brasileño (BRL)"),
    EUR(5, "EUR", "Euro (EUR)");

    private final int opcion;
    private final String codigo;
    private final String descripcion;

    Moneda(int opcion, String codigo, String descripcion) {
        this.opcion = opcion;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Moneda fromOpcion(int opcion) {
        for (Moneda moneda : Moneda.values()) {
            if (moneda.getOpcion() == opcion) {
                return moneda;
            }
        }
        throw new IllegalArgumentException("Opción de moneda inválida.");
    }

    public static String menuMonedas() {
        StringBuilder menu = new StringBuilder();
        for (Moneda moneda : Moneda.values()) {
            menu.append(moneda.getOpcion())
                    .append("-")
                    .append(moneda.getDescripcion())
                    .append(" | ");
        }
        // Eliminar el último " | "
        if (menu.length() > 3) {
            menu.setLength(menu.length() - 3);
        }
        return menu.toString();
    }
}
