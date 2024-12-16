import java.text.SimpleDateFormat;
import java.util.*;

public class ES {
    private static final Scanner sc = new Scanner(System.in);

    public static String readString() {

        String inputString = null;

        try {
            inputString = sc.nextLine();
            if (inputString == null || inputString.isEmpty()) {
                throw new Exception("String vacio o nulo.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            inputString = readString();
        }

        return inputString;
    }

    public static int readInteger() {
        int readInt = 0;

        try {
            String inputString = sc.nextLine();

            if (inputString == null || inputString.isEmpty()) {
                throw new Exception("String vacio o nulo.");
            }

            readInt = Integer.parseInt(inputString);

        } catch (NumberFormatException e) {
            System.out.println("Error: El argumento introducido no es del tipo int. Detalles: " + e.getMessage());
            readInt = readInteger();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            readInt = readInteger();
        }

        return readInt;
    }

    public static char readChar() {
        char readChar = ' ';

        try {
            String inputString = sc.nextLine();

            if (inputString == null || inputString.isEmpty()) {
                throw new Exception("String vacio o nulo.");
            }

            if (inputString.length() > 1) {
                throw new Exception("Introduce solo un caracter.");
            }

            readChar = inputString.charAt(0);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            readChar = readChar();
        }

        return readChar;
    }

    public static double readDouble() {
        double readDouble = 0.0;

        try {
            String inputString = sc.nextLine();

            if (inputString == null || inputString.isEmpty()) {
                throw new Exception("Introduce solo un caracter.");
            }

            readDouble = Double.parseDouble(inputString);

        } catch (NumberFormatException e) {
            System.out.println("Error: Valor no valido para el tipo double. Detalles: " + e.getMessage());
            readDouble = readDouble();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            readDouble = readDouble();
        }

        return readDouble;
    }

    public static boolean readBoolean() {
        boolean readBoolean = false;

        try {
            String inputString = sc.nextLine();

            Map<String, Boolean> booleanMap = new HashMap<>();

            booleanMap.put("true", true);
            booleanMap.put("false", false);
            booleanMap.put("t", true);
            booleanMap.put("f", false);
            booleanMap.put("si", true);
            booleanMap.put("no", false);
            booleanMap.put("s", true);
            booleanMap.put("n", false);

            if (!booleanMap.containsKey(inputString.toLowerCase())) {
                throw new Exception(
                        "Introduce un valor valido para el tipo boolean. Valores validos: true, false, t, f, si, no, s, n.");
            }

            readBoolean = booleanMap.get(inputString.toLowerCase());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            readBoolean = readBoolean();
        }

        return readBoolean;
    }

    public static int readRange(int min, int max) throws Exception {
        int readInt = 0;

        try {
            readInt = readInteger();
            if (readInt < min || readInt > max) {
                throw new Exception("Numero fuera del rango");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            readInt = readRange(min, max);
        }

        return readInt;
    }

    public static java.sql.Date readDateSql() {
        String fechaStr = sc.nextLine();

        // Definir el formato de la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            java.util.Date utilDate = dateFormat.parse(fechaStr);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return sqlDate;
        } catch (java.text.ParseException e) {
            System.out.println(
                    "Error al analizar la fecha. Asegúrate de ingresarla en el formato correcto (dd/MM/yyyy).");
            readDateSql();
        }
        return null;
    }

        // Códigos ANSI para los colores
        private static final String[] COLORS = {
            "\u001B[31m", // Rojo
            "\u001B[32m", // Verde
            "\u001B[33m", // Amarillo
            "\u001B[34m", // Azul
            "\u001B[35m", // Magenta
            "\u001B[36m", // Cian
            "\u001B[37m"  // Blanco
        };

    
    public static synchronized void typewriter(String text) {

        
        for (int i = 0; i < text.length(); i++) {
            // Selecciona un color de manera cíclica
            String color = COLORS[i % COLORS.length];
            System.out.print(color + text.charAt(i)); // Imprime el carácter actual con color
            try {
                Thread.sleep(50); // Pausa de 100 ms entre caracteres
            } catch (InterruptedException e) {
                System.err.println("Error en el hilo: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restaura el estado interrumpido
            }
        }
        // Resetea el color al blanco después de terminar de imprimir
        System.out.println("\u001B[0m");
    }

}
