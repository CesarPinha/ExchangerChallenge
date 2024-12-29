public class Principal {
    public static void main(String[] args) {

        ConsultaTipoDeCambio consulta = new ConsultaTipoDeCambio();
        CambioAPI cambio = consulta.buscarTipoDeCambio("");

        System.out.println(cambio);

    }
}
