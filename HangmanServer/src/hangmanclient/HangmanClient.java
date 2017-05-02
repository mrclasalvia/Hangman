/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marco La Salvia
 */
public class HangmanClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String hostName = "192.168.56.1";
            Socket clientSocket = new Socket(hostName, 8861);
            ClientPlayer cp = new ClientPlayer(clientSocket);
            cp.playGame();    
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
