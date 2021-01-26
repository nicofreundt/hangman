package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket server = new Socket("localhost", 4567);

    public Client() throws IOException {
        /*BufferedReader in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
        System.out.println(in.readLine());*/
    }

    public void sendtoServer(String message) throws IOException {
        PrintWriter out = new PrintWriter(this.getServer().getOutputStream(), true);
        //System.out.println(message);
        out.println(message);
    }

    public Socket getServer() {
        return server;
    }

    public void setServer(Socket server) {
        this.server = server;
    }
}
