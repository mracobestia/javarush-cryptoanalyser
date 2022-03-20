package com.javarush.cryptoanalyser;

import com.javarush.cryptoanalyser.programinterface.*;
import java.util.Scanner;

public class Main {

    private static final String ENCRYPT_MODE = "encrypt";
    private static final String DECRYPT_MODE = "decrypt";
    private static final String BRUTE_FORCE_MODE = "brute force";
    private static final String EXIT_MODE = "exit";
    private static final String START_MESSAGE = """
                Hello, friend! If you want to use Caesar cipher program, please, select the mode:
                1. to encrypt a file - '%s'
                2. to decrypt a file - '%s'
                3. for brute force hacking - '%s'
                4. to exit - '%s'%n""";

    public static void main(String[] args) {

        System.out.printf(START_MESSAGE, ENCRYPT_MODE, DECRYPT_MODE, BRUTE_FORCE_MODE, EXIT_MODE);

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
        while(!EXIT_MODE.equals(line) && !EXIT_MODE.equals(result));

}

}
