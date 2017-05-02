/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package console;

import hangman.Game;
import hangman.Player;
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
public class OnlinePlayer extends Player{
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public OnlinePlayer(Socket socket) {
        this.socket = socket;
        
    }
    private void openInputStream() throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    private void closeInputStream() throws IOException{
        this.in.close();
    }
    private void openOutputStream() throws IOException{
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }
    private void closeOutputStream() throws IOException{
        this.out.close();
    }

    @Override
    public void update(Game game) {
        switch(game.getResult()) {
            case FAILED:
        {
            try {
                printBanner("Hai perso!  La parola da indovinare era '" +
                            game.getSecretWord() + "'");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
                break;
            case SOLVED:
        {
            try {
                printBanner("Hai indovinato!   (" + game.getSecretWord() + ")");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
                break;
            case OPEN:
            {
                try {
                    this.openOutputStream();
                    int rem = Game.MAX_FAILED_ATTEMPTS - game.countFailedAttempts();
                    this.out.print("\n" + rem + " tentativi rimasti\n");
                    this.out.print(this.gameRepresentation(game));
                    this.out.print(game.getKnownLetters());
                    this.closeOutputStream();
                    break;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
                
        }
    }
    private String gameRepresentation(Game game) {
        int a = game.countFailedAttempts();
        
        String s = "   ___________\n  /       |   \n  |       ";
        s += (a == 0 ? "\n" : "O\n");
        s += "  |     " + (a == 0 ? "\n" : (a < 5
                ? "  +\n"
                : (a == 5 ? "--+\n" : "--+--\n")));
        s += "  |       " + (a < 2 ? "\n" : "|\n");
        s += "  |      " + (a < 3 ? "\n" : (a == 3 ? "/\n" : "/ \\\n"));
        s += "  |\n================\n";
        return s;
    }
    
    private void printBanner(String message) throws IOException {
        this.openOutputStream();
        this.out.print("");
        for (int i = 0; i < 80; i++)
            this.out.print("*");
        this.out.print("\n***  " + message);
        for (int i = 0; i < 80; i++)
            this.out.print("*");
        this.out.print("\n");
        this.closeOutputStream();
    }

    /**
     * Ask the user to guess a letter.
     * 
     * @param game
     * @return
     */
    @Override
    public char chooseLetter(Game game) {
        for (;;) {
            try {
                this.openOutputStream();
                this.out.print("Inserisci una lettera: ");
                this.closeOutputStream();

                this.openInputStream();
                String line = this.in.readLine().trim();
                this.closeInputStream();
                if (line.length() == 1 && Character.isLetter(line.charAt(0))) {
                return line.charAt(0);
                } else {
                    System.out.println("Lettera non valida.");
                }
            } catch (IOException ex) {
                Logger.getLogger(OnlinePlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
