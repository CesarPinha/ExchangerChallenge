import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaTipoDeCambio consulta = new ConsultaTipoDeCambio();
        GeneradorDeArchivo generador = new GeneradorDeArchivo();
        boolean continuar = true;

        System.out.println("=== Programa de Conversión de Monedas ===");

        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Convertir Moneda");
            System.out.println("9. Salir");

            System.out.print("Ingrese su opción: ");
            String opcionInput = lectura.nextLine();

            try {
                int opcion = Integer.parseInt(opcionInput);

                switch (opcion) {
                    case 1:
                        realizarConversion(lectura, consulta, generador);
                        break;
                    case 9:
                        System.out.println("Finalizando el programa. ¡Hasta luego!");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número correspondiente a las opciones.");
            }
        }

        lectura.close();
    }

    private static void realizarConversion(Scanner lectura, ConsultaTipoDeCambio consulta, GeneradorDeArchivo generador) {
        try {
            // Seleccionar Moneda de Origen
            System.out.println("\nSeleccione la moneda de origen:");
            System.out.println(Moneda.menuMonedas());
            System.out.print("Ingrese el número correspondiente: ");
            int origenInput = Integer.parseInt(lectura.nextLine());
            Moneda origen = Moneda.fromOpcion(origenInput);

            // Seleccionar Moneda de Destino
            System.out.println("\nSeleccione la moneda de destino:");
            System.out.println(Moneda.menuMonedas());
            System.out.print("Ingrese el número correspondiente: ");
            int destinoInput = Integer.parseInt(lectura.nextLine());
            Moneda destino = Moneda.fromOpcion(destinoInput);

            // Ingresar Cantidad a Convertir
            System.out.print("\nIngrese la cantidad a convertir (" + origen.getCodigo() + "): ");
            double cantidad = Double.parseDouble(lectura.nextLine());

            // Obtener Tipo de Cambio
            CambioAPI cambio = consulta.buscarTipoDeCambio(origen.getCodigo(), destino.getCodigo());

            // Realizar Conversión
            double resultado = cambio.getConversionRate() * cantidad;

            // Formatear Resultados
            DecimalFormat df = new DecimalFormat("#.##");

            // Mostrar Resultado
            System.out.printf("\n%.2f %s equivale a %.2f %s.\n", cantidad, origen.getCodigo(), resultado, destino.getCodigo());

            // Generar Archivo JSON
            generador.guardarJson(cambio, origen.getCodigo(), destino.getCodigo(), cantidad, resultado);
            System.out.println("Se ha generado el archivo JSON con los detalles de la conversión.");

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Asegúrese de ingresar números válidos para las opciones y la cantidad.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al generar el archivo JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
