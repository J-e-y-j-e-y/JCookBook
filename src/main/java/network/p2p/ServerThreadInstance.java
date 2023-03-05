package network.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreadInstance extends Thread {
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter writer;

    public ServerThreadInstance(ServerThread serverThread, Socket socket){
        this.serverThread = serverThread;
        this.socket = socket;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void run(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            while(true)
                serverThread.sendMessage(reader.readLine());
        } catch (IOException e) {
            serverThread.getThreadsSet().remove(this);
            e.printStackTrace();
        }
    }
}
