
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable {

    private ServerSocket server = null;
    private Socket cliente = null;
    int port = 0;

    public Server(int port) {
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
                System.out.println("OK: Connection");

                for (int i = 0; i < 25; i++) {
                    String list = "/";
                    System.out.print(list);
                    list += "/";
                    Thread.sleep(30);
                }
                System.out.println();

                is = cliente.getInputStream();
                isr = new InputStreamReader(is);
                bf = new BufferedReader(isr);

                //Leer mensaje
                ES.typewriter("SERVER: Waiting");
                String message = bf.readLine();
                ES.typewriter("SERVER: Message received");

                //Coger respuesta
                ES.typewriter(message);

                //Escribir respuesta
                os = cliente.getOutputStream();
                pw = new PrintWriter(os);
                pw.write(answer(message));
                pw.flush();
                ES.typewriter("SERVER: Message sent");

                //Cerrar
                pw.close();
                os.close();
                bf.close();
                isr.close();
                is.close();
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private String answer(String message) {
        return "hola";
    }



}
