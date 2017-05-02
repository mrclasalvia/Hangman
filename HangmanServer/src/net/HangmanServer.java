/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

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
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
