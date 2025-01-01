import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorH {
    private static final List<String> PALABRAS = Arrays.asList("java", "socket", "servidor", "cliente", "programacion");

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Boolean ganador = false;
        System.out.println("Servidor iniciado en el puerto 12345...");

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println("Cliente conectado.");

                // Preguntar modalidad
                salida.println("Elige modalidad: 1 (solo) o 2 (local con amigo):");
                salida.flush(); // Forzar el envío del mensaje
                int modalidad = Integer.parseInt(entrada.readLine());

                String palabraSecreta;

                if (modalidad == 1) {
                    palabraSecreta = PALABRAS.get(new Random().nextInt(PALABRAS.size()));
                } else {
                    salida.println("Jugador 1, introduce la palabra secreta:");
                    salida.flush();
                    palabraSecreta = entrada.readLine().toLowerCase();
                    salida.println("Palabra recibida. Jugador 2, es tu turno.");
                    salida.flush();
                }

                char[] progreso = new char[palabraSecreta.length()];
                Arrays.fill(progreso, '_');
                int intentos = palabraSecreta.length() + 3;

                while (intentos > 0 && new String(progreso).contains("_")) {
                    salida.println("Estado actual: " + new String(progreso));
                    salida.flush();
                    salida.println("Intentos restantes: " + intentos);
                    salida.flush();

                    salida.println("¿Quieres enviar una palabra (p) o una letra (l)?");
                    salida.flush();
                    String eleccion = entrada.readLine().toLowerCase();

                    if ("p".equals(eleccion)) {
                        salida.println("Introduce tu palabra:");
                        salida.flush();
                        String palabra = entrada.readLine().toLowerCase();

                        if (palabra.equals(palabraSecreta)) {
                            salida.println("¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
                            ganador = true;
                            salida.flush();
                            break;
                        } else {
                            salida.println("Palabra incorrecta.");
                            salida.flush();
                            intentos--;
                        }
                    } else if ("l".equals(eleccion)) {
                        salida.println("Introduce una letra:");
                        salida.flush();
                        char letra = entrada.readLine().toLowerCase().charAt(0);

                        boolean acierto = false;
                        for (int i = 0; i < palabraSecreta.length(); i++) {
                            if (palabraSecreta.charAt(i) == letra) {
                                progreso[i] = letra;
                                acierto = true;
                            }
                        }

                        if (acierto) {
                            salida.println("¡Letra correcta!");
                            salida.flush();
                        } else {
                            salida.println("Letra incorrecta.");
                            salida.flush();
                            intentos--;
                        }
                    } else {
                        salida.println("Opción no válida.");
                        salida.flush();
                    }
                }

                if (!new String(progreso).contains("_")) {
                    salida.println("¡Felicidades! Has completado la palabra: " + palabraSecreta);
                } else if(!ganador) {
                    salida.println("Lo siento, te has quedado sin intentos. La palabra era: " + palabraSecreta);
                }
                salida.flush();
                salida.println("Juego terminado.");
                salida.flush();
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}