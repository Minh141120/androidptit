package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Policy;

import model.Board;
import model.Cell;
import model.Pair;
import model.Player;
import model.User;

public class Client {
    private static Socket socket;
    private static String serverAddress = "26.83.233.30";
    private static int port = 4444;
    private static DataInputStream dataInputStream;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static DataOutputStream dataOutputStream;
    public Client() {

    }


    public Player findMatch(Player player) {
        try {
            socket = new Socket(serverAddress, port);
            sendRequestType("find-match");

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(player);
            objectOutputStream.flush();

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Player response = null;
            long timeoutMillis = System.currentTimeMillis() + 10000;
            while(System.currentTimeMillis() < timeoutMillis) {
                if(dataInputStream.available() > 0) {
                    response = (Player)objectInputStream.readObject();
                    if(response != null) break;
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (response != null) return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendRequestType(String requestType) {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(requestType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String receiveInstruction() {
        try {
            String instruction = dataInputStream.readUTF();
            return instruction;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void sendCoordinate(Cell cell) {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(cell.toString());
            dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cell receiveCoordinate() {
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