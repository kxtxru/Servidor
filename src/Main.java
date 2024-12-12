
public class Main {

    private static final String host = "192.168.67.173";
    private static final int port = 1337;
    public static void main(String[] args) {
    
        // //Parte SERVIDOR
        // Server srv = new Server(port);
        // Thread tServer = new Thread(srv);
        // tServer.start();
        // try {
        //     Thread.sleep(1000);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        
        //Parte CLIENTE
        Cliente c = new Cliente(host, port);

        if(!c.connect()){
            System.out.println("ERROR: Can't connect to the server");
            return;
        }
        c.send("Hoy");
        String ans = c.receive();
        System.out.println(ans);

        if(!c.connect()){
            System.out.println("ERROR: Can't connect to the server");
            return;
        }
        c.send("Cóndor");
        ans = c.receive();
        System.out.println(ans);
    }
}

