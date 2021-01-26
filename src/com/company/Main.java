package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Client client = new Client();
        int i = 0;
        while (true) {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getServer().getInputStream()));

            Scanner scanner = new Scanner(System.in);

            System.out.print("Send message: ");
            String message = scanner.nextLine();
            client.sendtoServer(message);
            i++;
            String inline;
            inline = in.readLine();
            System.out.println("Received message: " + inline);
            if( i == 15 ) {
                break;
            }
        }
    }
}
