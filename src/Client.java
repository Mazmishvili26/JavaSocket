import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;

    public void run () {

        try {
            while (true){

//                აქ სერვერის მიერ შქმნილი სოკეტის მიღება ხდება და ინიციალიზაცია ხდება კლიენტის მხარეს
                socket = new Socket(InetAddress.getByName("localhost") , 8081);

//                ამ სამ ხაზში ხდება მესიჯის დაწერის პრიხოდი,რომელიც ობიექტად უნდა გაიგზავნოს სერვერისკენ
                Scanner scanner = new Scanner(System.in);
                System.out.print("Client: ");
                message = scanner.nextLine();

//                აქ ხდება მესიჯი ,რომელიც ხელით შეგვყავს რაღაცა იდება ობიექტში,რომელიც იგზავნება სერვერეისკენ
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(message);

//                აქ ხდება სერვერეიდან წამოსული ობიექტის მიღება და დაბლა უკვე მისი ეკრანზე გამოტანა
                in = new ObjectInputStream(socket.getInputStream());
                try {
//                    აქ სერვერიდან მოსული ობიექტის წაკითხვა და ეკრანზე გამოტანა ხდება
                    System.out.println("C--> "+(String) in.readObject());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
