package network.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread {
    private ServerSocket socket;
    private Set<ServerThreadInstance> threadsSet = new HashSet<>();

    public ServerThread(String port) throws IOException {
        socket = new ServerSocket(Integer.parseInt(port));
    }

    @Override
    public void run() {
        while(true){
            try {
                ServerThreadInstance instance = new ServerThreadInstance(this, socket.accept());
                threadsSet.add(instance);
                instance.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    void sendMessage(String message){
        try {
            for (ServerThreadInstance in : threadsSet) {
                in.getWriter().println(message);
            }
        }catch (Exception e){

        }
    }

    public Set<ServerThreadInstance> getThreadsSet() {
        return threadsSet;
    }
}
