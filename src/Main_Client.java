import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main_Client {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socketServer = null;
        assert false;


        try {
            socketServer = new Socket("localhost", 55555);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
        System.out.println("Connected");

        try
        {

            PrintWriter out = new PrintWriter(socketServer.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            //Eingabe-Reader/Ausgabe-Writer erzeugen:

            //Solange der User etwas eingibt (und danach Enter drückt), werden die Daten
            //zum Server geschickt. Eingabe von "X" beendet alles.
            String textInput = null;
            out.println("1234" + "\r\n" );
            out.flush();
            System.out.println("Test");
            System.out.println(in.readLine());

            System.out.println("Test2");
            while ( (textInput = reader.readLine() ) != null && !"e".equals(textInput))
            {

                System.out.println(in.readLine());


            }
            //User hat "e" eingegeben: Socket dichtmachen.
            socketServer.close();
        }
        catch (IOException e)
        {
            System.out.println ("IOException: " + e.getMessage());
            System.exit(-1);
        }
    }
}






/*import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main_Client {
    public static void main(String[] args) {
        Socket socket = null;
        try
        {
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 55555
            Socket s = new Socket(ip, 55555);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            System.out.println(dis);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            System.out.println(dos);

            dis.close();
            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

*/