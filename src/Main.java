import java.util.Scanner;

public class Main {

    private static final String host = "192.168.67.157";
    private static final int port = 1347;
    private static Scanner sc = new Scanner(System.in);
//hacer que vayan en orden los hilos ejejjejeejejee Syncronized
    public static void main(String[] args) {
    
        // //Parte SERVIDOR
        // Server srv = new Server(port);
        // Thread tServer = new Thread(srv);
        // tServer.start();
        // try {
        //     Thread.sleep(500);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        
        //Parte CLIENTE
        Cliente c = new Cliente(host, port);

        if(!c.connect()){
            ES.typewriter("ERROR: Can't connect to the server");
            return;
        }

        ES.typewriter("Frase a traducir: ");
        String frase = sc.nextLine();

        c.send(frase+"~"+"ru");
        String ans = c.receive();
        ES.typewriter(ans);
    }
}

