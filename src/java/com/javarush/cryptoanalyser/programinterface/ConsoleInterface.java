package com.javarush.cryptoanalyser.programinterface;

import com.javarush.cryptoanalyser.workalgorithm.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInterface {

    private static final String EXIT_MODE = "exit";
    private static final String GOOD_RESULT = "ok";
    private static final String ERROR_MESSAGE = "There are some problems with your files.";

    private ConsoleInterface() {}

    public static String encrypt() {

        Map<String, String> parameters = getParameters();

        if (EXIT_MODE.equals(parameters.get("exit"))) {
            return EXIT_MODE;
        }

        try {
            Cryptanalyser.encrypt(
                    parameters.get("inputFile"),
                    parameters.get("outputFile"),
                    Integer.parseInt(parameters.get("strKey")));
        } catch (IOException ex) {
            System.err.println(ERROR_MESSAGE);
            return EXIT_MODE;
        }

        System.out.println("The selected file has been encrypted.");
        return GOOD_RESULT;

    }

    public static String decrypt() {

        Map<String, String> parameters = getParameters();

        if (parameters.containsKey("exit") && EXIT_MODE.equals(parameters.get("exit"))) {
            return EXIT_MODE;
        }

        try {
            Cryptanalyser.decrypt(
                    parameters.get("inputFile"),
                    parameters.get("outputFile"),
                    Integer.parseInt(parameters.get("strKey")));
        } catch (IOException ex) {
            System.err.println(ERROR_MESSAGE);
            return EXIT_MODE;
        } catch (IllegalCharacterException ex) {
            System.err.println(ex.getErrorMessage());
            return EXIT_MODE;
        }

        System.out.println("The selected file has been decrypted.");
        return GOOD_RESULT;

    }

    public static String hackBruteForce() {

        Map<String, String> parameters = getParametersForHack();

        if (parameters.containsKey("exit") && EXIT_MODE.equals(parameters.get("exit"))) {
            return EXIT_MODE;
        }

        try {
            Cryptanalyser.hackBruteForce(parameters.get("inputFile"));
        } catch (IOException ex) {
            System.err.println(ERROR_MESSAGE);
            return EXIT_MODE;
        } catch (IllegalCharacterException ex) {
            System.err.println(ex.getErrorMessage());
            return EXIT_MODE;
        }

        System.out.println("The selected file has been hacked by brute force.");
        return GOOD_RESULT;

    }

    private static Map<String, String> getParameters() {

        Map<String, String> result = new HashMap<>();
        result.put("exit", "");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Specify the path to the input file:");
        String inputFile = "";
        do {
            inputFile = scanner.nextLine();
            if (isExit(inputFile)) {
                result.put("exit", EXIT_MODE);
                break;
            }
        } while (!ParameterValidation.isFilePathValid(inputFile));

        if (isExit(inputFile)) {
            return result;
        } else {
            result.put("inputFile", inputFile);
        }

        System.out.println("Specify the path to the file to write the result:");
        String outputFile = "";
        do {
            outputFile = scanner.nextLine();
            if (isExit(outputFile)) {
                result.put("exit", EXIT_MODE);
                break;
            } else if (outputFile.equals(inputFile)) {
                System.out.println("Output file can't be the same as input file. Please select another file.");
            }
        } while (!ParameterValidation.isFilePathValid(outputFile) || outputFile.equals(inputFile));

        if (isExit(outputFile)) {
            return result;
        } else {
            result.put("outputFile", outputFile);
        }

        System.out.println("Specify the key:");
        String strKey = "";
        do {
            strKey = scanner.nextLine();
            if (isExit(strKey)) {
                result.put("exit", EXIT_MODE);
                break;
            }
        } while (!ParameterValidation.isKeyValid(strKey));

        if (isExit(strKey)) {
            return result;
        } else {
            result.put("strKey", strKey);
        }

        return result;

    }

    private static Map<String, String> getParametersForHack() {

        Map<String, String> result = new HashMap<>();
        result.put("exit", "");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Specify the path to the input file:");
        String inputFile = "";
        do {
            inputFile = scanner.nextLine();
            if (isExit(inputFile)) {
                result.put("exit", EXIT_MODE);
                break;
            }
        } while (!ParameterValidation.isFilePathValid(inputFile));

        if (isExit(inputFile)) {
            return result;
        } else {
            result.put("inputFile", inputFile);
        }

        return result;

    }

    private static boolean isExit(String userLine) {
        return userLine.equals(EXIT_MODE);
    }
}
