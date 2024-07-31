import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    // Uncomment this block to pass the first stage
    //
     try {
         ServerSocket serverSocket = new ServerSocket(4221);

         // Since the tester restarts your program quite often, setting SO_REUSEADDR
         // ensures that we don't run into 'Address already in use' errors
         serverSocket.setReuseAddress(true);



             try {
                 Socket socket = serverSocket.accept(); // Wait for connection from client.
                 System.out.println("accepted new connection");
                 socket.setReuseAddress(true);

                 Reader iStreamReader = new InputStreamReader(socket.getInputStream());
                 StringBuilder builder = new StringBuilder();
                 int count;
                 while ((count = iStreamReader.read()) != -1) {
                     builder.append((char) count);
                     // break on CRLF
                     if (builder.toString().contains("\r\n")) break;
                 }
                 System.out.println(builder.toString());
                 String clientRequestAddress=builder.toString();
                 String[] reqPara=clientRequestAddress.split(" ");
                 if (reqPara[1].equals("/") && reqPara[0].equals("GET")) socket.getOutputStream().write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                 if (!reqPara[1].isBlank() && reqPara[0].equals("GET")) socket.getOutputStream().write("HTTP/1.1 404 Not Found\r\n\r\n".getBytes());
                 }
             catch(IOException e){
                     System.out.println("IOException: " + e.getMessage());

                 }
         }

     catch(IOException e){
         System.out.println("IOException: " + e.getMessage());

     }
     }
    }

