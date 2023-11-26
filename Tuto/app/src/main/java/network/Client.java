package network;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Cell;
import model.Pair;
import model.Player;

public class Client {
    private Socket socket;
    private static String serverAddress = "127.0.0.1"; // serverAddress
    private static int port = 4444;
    private DataInputStream dataInputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private DataOutputStream dataOutputStream;
    public Client() {

    }

    public Player findMatch(Player player) {
        return sendObjectToServer(player, "find-match");
    }

    public Player sendObjectToServer(Object object, String requestType) {
        try {
            socket = new Socket(serverAddress, port);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Pair<String, Object> requestObject = new Pair<String, Object>(requestType, object);
            objectOutputStream.writeObject(requestObject);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Player response = null;
            long timeoutMillis = System.currentTimeMillis() + 10000;
            while(System.currentTimeMillis() < timeoutMillis) {
                response = (Player) objectInputStream.readObject();
                if(response != null) break;

            }
            if (response != null) return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pair<String, Player> receiveObjectInstruction() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Pair<String, Player> instruction = (Pair<String, Player>)objectInputStream.readObject();

            return instruction;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String receiveInstruction() {
        try {
            String instruction = dataInputStream.readUTF();
            return instruction;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void sendCoordinate(Cell cell) {
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(cell);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Cell receiveCoordinate(){
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Cell cell = (Cell) objectInputStream.readObject();
            return cell;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
