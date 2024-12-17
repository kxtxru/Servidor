import java.io.*;
import java.net.*;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            // Conectar al servidor (asegúrate de que el servidor esté corriendo en el mismo puerto)
            Socket socket = new Socket("localhost", 12345);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String mensajeServidor;
            while (true) {
                // Esperar a recibir una pregunta del servidor
                mensajeServidor = br.readLine();
                if (mensajeServidor == null) break; // Si el servidor cierra la conexión, salir del bucle

                // Responder con una frase en bucle
                System.out.println(mensajeServidor);
                pw.println("Estoy respondiendo a la pregunta: ¡Hola, soy el cliente!");
            }

            socket.close(); // Cerrar la conexión
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

