import java.io.*;
import java.net.*;

class Server2 implements Runnable {

    private ServerSocket server = null;
    private Socket cliente = null;
    int port = 12345;

    public Server2(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        OutputStream os = null;

        System.out.println("INFO: Server launching...");

        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to open socket on TCP " + port);
            return;
        }

        while (true) {
            try {
                cliente = server.accept();
                System.out.println("OK: Connection established");

                // Enviar un mensaje inicial al cliente
                os = cliente.getOutputStream();
                pw = new PrintWriter(os, true);
                pw.println("¿Cómo te llamas?");

                // Leer la respuesta del cliente
                is = cliente.getInputStream();
                isr = new InputStreamReader(is);
                bf = new BufferedReader(isr);

                System.out.println("SERVER: Waiting for message from client...");
                String message = bf.readLine();  // Leer la respuesta del cliente
                System.out.println("SERVER: Message received: " + message);

                // Enviar una respuesta al cliente
                pw.println("Hola " + message + ", es un placer conocerte.");

                // Cerrar las conexiones
                pw.close();
                os.close();
                bf.close();
                isr.close();
                is.close();
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server2 server = new Server2(12345);
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
}
