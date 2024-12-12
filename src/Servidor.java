import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements Runnable{
    private ServerSocket server = null;
    private Socket cliente = null;
    int port = 0;

    public Server(int port){
        this.port = port;
    }

    @Override
    public void run(){
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader bf = null;
        PrintWriter pw = null;
        OutputStream os = null;

        System.out.println("INFO: Server launching...");

        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to open socket on TCP " + port );
            return;
        }
        while (true) {
            try {
                cliente = server.accept();
                System.out.println("OK: Connection");

                is = cliente.getInputStream();
                isr = new InputStreamReader(is);
                bf = new BufferedReader(isr);

                //Leer mensaje
                System.out.println("SERVER: Waiting");
                String message = bf.readLine();
                System.out.println("SERVER: Message received");

                //Coger respuesta
                //String answer = getAnswer(message);

                //Escribir respuesta
                os = cliente.getOutputStream();
                pw = new PrintWriter(os);
                pw.write(message);
                pw.flush();
                System.out.println("SERVER: Message sent");

                //Cerrar
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

}

