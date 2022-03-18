package com.javarush.cryptoanalyser;

import com.javarush.cryptoanalyser.programinterface.*;
import java.util.Scanner;

public class Main {

    public static final String ENCRYPT_MODE = "encrypt";
    public static final String DECRYPT_MODE = "decrypt";
    public static final String BRUTE_FORCE_MODE = "brute force";
    public static final String EXIT_MODE = "exit";

    public static void main(String[] args) {

        System.out.printf("""
                Hello, friend! If you want to use Caesar cipher program, please, select the mode:
                1. to encrypt a file - '%s'
                2. to decrypt a file - '%s'
                3. for brute force hacking - '%s'
                4. to exit - '%s'%n""",
                ENCRYPT_MODE, DECRYPT_MODE, BRUTE_FORCE_MODE, EXIT_MODE);

        Scanner scanner = new Scanner(System.in);
        String line = "";
        String result = "";

        do {

            line = scanner.nextLine();

            result = switch (line) {
                case ENCRYPT_MODE:
                    yield ConsoleInterface.encrypt();
                case DECRYPT_MODE:
                    yield ConsoleInterface.decrypt();
                case BRUTE_FORCE_MODE:
                    yield ConsoleInterface.hackBruteForce();
                case EXIT_MODE:
                    yield EXIT_MODE;
                default:
                    System.out.println("Wrong command! Try again.");
                    yield "";
            };

        }
        while(!line.equals(EXIT_MODE) && !result.equals(EXIT_MODE));

}

}
