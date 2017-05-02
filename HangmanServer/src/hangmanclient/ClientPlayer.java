/*
 * To change this license header, choose License Headers inOnline Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template inOnline the editor.
 */
package hangmanclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Marco La Salvia
 */
public class ClientPlayer {
    Socket socket;
    BufferedReader inOnline, in;
    PrintWriter out;
    public ClientPlayer(Socket socket) {
        this.socket = socket;
    }
    
    private void openInputStream() throws IOException{
        this.inOnline = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    private void closeInputStream() throws IOException{
        this.inOnline.close();
    }
    private void openOutputStream() throws IOException{
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }
    private void closeOutputStream() throws IOException{
        this.out.close();
    }
    
    public void playGame() throws IOException {
        while (true) {
            this.openInputStream();
            if(this.inOnline.ready()){
                String s = this.inOnline.readLine();
                System.out.println(s);
            }
            this.closeInputStream();
            this.openOutputStream();
            in = new BufferedReader(new InputStreamReader(System.in));
            String line = in.readLine().trim();
            this.out.write(line);
            this.closeOutputStream();
        }
    }
    
    
    
}
