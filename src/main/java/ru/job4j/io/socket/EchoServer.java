package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.log.UsageLog4j;

/**
 * Класс реализует простейшее взаимодействие между клиентом и сервером с помощью сокетов.
 * Клиент отправляет запросы, сервер должен ему отвечать:
 * <p>Условие завершения работы сервера получение от клиента запроса - "http://localhost:9000/?msg=Exit".
 * <p>Ответить - Hello - "http://localhost:9000/?msg=Hello".
 * <p> Во всех остальных случаях отправлять текст запроса - "What we will do".
 * <p> Добавленна обработка  Exception через catch c выводом в логгер.
 */
public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str = in.readLine();
                    if (str.contains("msg=Exit")) {
                        socket.close();
                    } else if (str.contains("msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write("What we will do".getBytes());
                    }
                    System.out.println(str);

                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log message", e);
        }
    }
}
