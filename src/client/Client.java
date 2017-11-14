package client;


import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends JDialog {
    // адрес сервера
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;
    // следующие поля отвечают за элементы формы
    private String clientName = "";
  //  BankView bankView = new BankView();
    // получаем имя клиента
    public String getClientName() {
        return this.clientName;
    }
    BankView bankView =new BankView();

    // конструктор
    public Client() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   {
                       BankView bankView = new BankView();
                       bankView.pack();
                       bankView.setVisible(true);
                       // bankView.pack();
                       // bankView.setVisible(true);
                        //     System.exit(0);
                        // если есть входящее сообщение
                      //  if (.hasNext()) {
                            // считываем его
                        //    String inMes = inMessage.nextLine();
                            //  String clientsInChat = "Клиентов в чате = ";
                            //  if (inMes.indexOf(clientsInChat) == 0) {
                            //      jlNumberOfClients.setText(inMes);
                            //  } else {
                            // выводим сообщение
                            //     jtaTextAreaMessage.append(inMes);
                            // добавляем строку перехода
                            //    jtaTextAreaMessage.append("\n");
                            //  }
                        }
                } catch (Exception e) {
                }
            }
        }).start();

    }
    public String getAccount(String text) {
        // формируем сообщение для отправки на сервер
   //    String account = jtfName.getText() + ": " + jtfMessage.getText();
        // отправляем сообщение
        //  outMessage.println(account);
        // outMessage.flush();
        //  jtfMessage.setText("");
        return text;
    }

 }



