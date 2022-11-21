import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String message;


    public void run () {

        try {

//            სერვერის პორტის შექმნა
            ServerSocket serverSocket = new ServerSocket(8081);


            while (true){
//                serverSocket.accept მეთოდი გამოიყენება სოკეტში ანუ კლიენტიდან წამოსული ინფორმაციის მისაღებად, იმისათვის,რომ შესრულდეს მოთხოვნა
//                სწორედ ეს socket.accept ამოწმებს ჰოსტის მისამართს, პორტის ნომერს და ასე შემდეგ.
                socket = serverSocket.accept();

//                კლიენტიდან წამოსული ობიექტის მიღება
                in  = new ObjectInputStream(socket.getInputStream());

//               in.readObject კლიენტიდან წამოსული მესიჯის წაკითხვაა  და გამოტანა ეკრანზე
                System.out.println("S -> " + in.readObject());

                System.out.println("Server : ");
                Scanner scanner = new Scanner(System.in);
                message = scanner.nextLine();

//                ObjectOutputStream სერვერიდან ობიექტის გაგზავნა კლიენტისაკენ
                out  = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject((Object) message) ;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
