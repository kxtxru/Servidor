import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Prueba {
    private static final String host = "192.168.67.157"; // Tu IP (servidor)
    private static final int port = 1337;

    public static void main(String[] args) {
        // Parte SERVIDOR
        Server srv = new Server(host, port);
        Thread tServer = new Thread(srv);
        tServer.start();
    }
}

class Server implements Runnable {
    private ServerSocket server = null;
    private Socket cliente = null;
    private String host;
    private int port;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("INFO: Server launching...");
        try {
            // Vincular al host y puerto
            server = new ServerSocket(port, 50, java.net.InetAddress.getByName(host));
            System.out.println("INFO: Server listening on " + host + ":" + port);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to open socket on " + host + ":" + port);
            return;
        }

        while (true) {
            try {
                cliente = server.accept();
                System.out.println("OK: Connection from " + cliente.getInetAddress());

                BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter pw = new PrintWriter(cliente.getOutputStream(), true);

                // Leer mensaje
                String message = bf.readLine();
                System.out.println("SERVER: Message received: " + message);

                // Responder
                String answer = "Respuesta a: " + message;
                pw.println(answer);
                System.out.println("SERVER: Message sent: " + answer);

                // Cerrar conexión
                bf.close();
                pw.close();
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
