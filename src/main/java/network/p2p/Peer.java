package network.p2p;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Peer {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("> enter peer username and port");
        String[] input = reader.readLine().split(" ");

        ServerThread serverThread = new ServerThread(input[1]);
        serverThread.start();

        new Peer().updatePeerListener(reader, input[0], serverThread);
    }

    public void updatePeerListener(BufferedReader reader, String username,
                                   ServerThread serverThread) throws Exception {
        System.out.println("> enter space separated peers to listen from, format-> hostname:port (enter 's' to skip)");

        String[] input = reader.readLine().split(" ");

        if(!input[0].equals("s"))
            for (String s : input) {
                String[] con = s.split(":");
                Socket socket = null;

                try {
                    socket = new Socket(con[0], Integer.parseInt(con[1]));
                    new PeerThread(socket).start();
                } catch (Exception e) {
                    if (socket != null)
                        socket.close();
                    System.out.println(e.getMessage());
                }
            }
        setCommunication(reader, username, serverThread);
    }

    public void setCommunication(BufferedReader reader, String username,
                                   ServerThread serverThread) throws Exception {
        try {
            System.out.println("> start communication (e - exit, c - change");
            boolean flag = true;

            while (flag){
                String message = reader.readLine();
                System.out.println("m = " + message);
                if(message.equals("e")){
                    flag = false;
                    break;
                }else if(message.equals("s")){
                    updatePeerListener(reader, username, serverThread);
                }else{
                    System.out.println("here");
                    StringWriter writer = new StringWriter();
                    Map<String, String> map = new HashMap<>();
                    map.put("username", username);
                    map.put("message", message);
                    JsonObject obj = (JsonObject) Json.createObjectBuilder(map);
                    System.out.println("sending: " + obj);
                    Json.createWriter(writer).writeObject(obj);
                    System.out.println("sending: " + writer.toString());
                    serverThread.sendMessage(writer.toString());
                }
            }

        }catch (Exception e){

        }
    }
}