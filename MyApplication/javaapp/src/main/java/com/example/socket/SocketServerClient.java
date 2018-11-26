package com.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/24
 */
public class SocketServerClient implements Runnable {
    ServerSocket ss;

    public SocketServerClient(ServerSocket ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = ss.accept();
                process(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void process(Socket socket) throws IOException {
        if (!socket.isConnected()) {
            socket.close();
            return;
        }

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        do {
            line = bufferedReader.readLine();
            System.out.println(line);
        } while (line != null);

        socket.close();
    }

    public static void main(String[] args) {
        try {
            new Thread(new SocketServerClient(new ServerSocket(8989)))
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
