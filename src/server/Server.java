package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // порт, который будет прослушивать наш сервер
    static final int PORT = 3443;
    // список клиентов, которые будут подключаться к серверу
    //private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    DbHelper dbHelper = new DbHelper();

    public Server() {

        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            // создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");
            // запускаем бесконечный цикл
            while (true) {
                clientSocket = serverSocket.accept();
                // создаём обработчик клиента, который подключился к серверу
                // this - это наш сервер
                // ClientHandler client = new ClientHandler(clientSocket, this);
                //  clients.add(client);
                // каждое подключение клиента обрабатываем в новом потоке
                new Thread((Runnable) clientSocket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
    @Override
    public void run () {
        ServerSocketHints hints = new ServerSocketHints();
        ServerSocket server = Gdx.net.newServerSocket(Protocol.TCP, "localhost", 9999, hints);
        // wait for the next client connection
        Socket client = server.accept(null);
        // read message and send it back
        try {
            String message = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
            Gdx.app.log("PingPongSocketExample", "got client message: " + message);
            client.getOutputStream().write("PONG\n".getBytes());
        } catch (IOException e) {
            Gdx.app.log("PingPongSocketExample", "an error occured", e);

}