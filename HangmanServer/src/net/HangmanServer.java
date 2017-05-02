/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import console.LocalPlayer;
import console.OnlinePlayer;
import hangman.Hangman;
import hangman.Player;
import hangmanclient.ClientPlayer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class HangmanServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = 8861;
            ServerSocket serverSocket;
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            Hangman game = new Hangman();
            Player player = new OnlinePlayer(socket);
            // Player player = new ArtificialPlayer();
            game.playGame(player);
            
            String hostName = "192.168.56.1";
            Socket clientSocket = new Socket(hostName, 8861);
            ClientPlayer cp = new ClientPlayer(clientSocket);
            cp.playGame();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
