
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Cliente {

    String host = "localhost";
    int port = 1234;

    Socket socket = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    final String errorMSG = "CLIENT ERROR";

    public Cliente(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //Para conectar con el servidor
    //Devuelve un boolean que nos indicara si esta conectado o no
    public boolean connect() {
        try {
            socket = new Socket(host, port);
            ES.typewriter("CLIENT: Connected");
            return true;
        } catch (Exception e) {
            ES.typewriter("CLIENT: Connection rejected");
            return false;
        }
    }

    public String receive() {
        try {

            isr = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(isr);
            //En caso de que sea solo una linea de mensaje
            String ans = br.readLine();
            ES.typewriter("CLIENTE: Message received");
            //Recordar cerrar al final
            br.close();
            isr.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return errorMSG;
        }

    }

    public boolean send(String message) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(message);
            pw.flush();
            ES.typewriter("CLIENT: Message sent");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void waitForServerResponse() {
        while (true) {
            String response = receive();
            if (response != null && !response.isEmpty()) {
                System.out.println("Respuesta recibida: " + response);
                break; // Sale del bucle cuando recibe una respuesta
            }
            try {
                Thread.sleep(500); // Espera medio segundo antes de volver a comprobar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción
            }
        }
    }
}
