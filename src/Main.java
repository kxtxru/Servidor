


public class Main {

    private static final String host = "localhost";
    private static final int port = 1337;
//hacer que vayan en orden los hilos ejejjejeejejee Syncronized
    public static void main(String[] args) {
    
        //Parte SERVIDOR
        Server srv = new Server(port);
        Thread tServer = new Thread(srv);
        tServer.start();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Parte CLIENTE
        Cliente c = new Cliente(host, port);

        if(!c.connect()){
            ES.typewriter("ERROR: Can't connect to the server");
            return;
        }
        c.send("que tal?");
        String ans = c.receive();
        System.out.println(ans);

        if(!c.connect()){
            ES.typewriter("ERROR: Can't connect to the server");
            return;
        }
        c.send("hola");
        ans = c.receive();
        System.out.println(ans);
    }
}

