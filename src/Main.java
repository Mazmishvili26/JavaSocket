public class Main {

    public static void main (String [] args){

//        აქ ხდება სერვერის გაშვება და აუცილებელია start ის გამოყენება და ეს რომ გამოვიყენოთ server კლასს უნდა იყოს extends thread
        Server server = new Server();
        server.start();

//        აქ ხდება კლიენტის გაშვება და ის რომ წავიკითხოთ client კლასი უნდა იყოს implements Runnable
        Client client = new Client();
        Thread t = new Thread(client);
        t.start();

    }

}
