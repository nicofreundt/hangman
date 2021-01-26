package com.company;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server {

    ServerSocket server = new ServerSocket(4567);

    String[] words = {"monster", "katze", "pferd", "hund", "tastatur"};

    boolean gameState = false;
    String activeWord = "";
    ArrayList<Character> guessedChars = new ArrayList<>();
    Socket client;
    int fails = 0;

    BufferedReader in;
    PrintWriter out;

    public ServerSocket getServer() {
        return server;
    }

    public boolean isGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public String getActiveWord() {
        return activeWord;
    }

    public void setActiveWord(String activeWord) {
        this.activeWord = activeWord;
    }

    public ArrayList<Character> getGuessedChars() {
        return guessedChars;
    }

    public void setGuessedChars(ArrayList<Character> guessedChars) {
        this.guessedChars = guessedChars;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    private void startGame() {
        this.setGameState(true);
        int i = (int) ((Math.random() * 10) % this.words.length);
        this.setActiveWord(this.words[i]);
        out.println("Game started and new word created. Start guessing...");
    }

    public Server() throws IOException {

        while(true) {
            this.client = this.server.accept();

            in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            out = new PrintWriter(this.client.getOutputStream(), true);

            String inputLine;

            while((inputLine = in.readLine()) != null) {

                if(this.gameState) {
                    if (inputLine.length() == 1) {
                        if(this.activeWord.contains(inputLine)) {
                            this.guessedChars.add(inputLine.charAt(0));
                            this.setActiveWord(this.activeWord.replaceAll(inputLine.toLowerCase(), ""));
                            if(this.activeWord.length() == 0) {
                                out.println("Congratulations! All letters were guessed!");
                                this.setGameState(false);
                                this.fails = 0;
                            } else {
                                out.println("Right! The word contains " + inputLine);
                            }
                        } else {
                            this.fails++;
                            if(fails >= 3) {
                                out.println("Wrong! Too many fails!!! Type 'Start Game' to try again...");
                                this.setGameState(false);
                                this.fails = 0;
                            } else {
                                out.println("The word doesn't contain your guess: " + inputLine);
                            }
                        }
                    } else {
                        out.println("Wrong Input. Try again...");
                    }
                } else if(inputLine.equals("Start Game")) {
                    this.startGame();
                } else {
                    out.println("Something's wrong. Try again...");
                }

            }
        }
    }
}
