package com.javarush.cryptoanalyser.programinterface;

import com.javarush.cryptoanalyser.workalgorithm.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInterface {

    public static final String EXIT_MODE = "exit";
    public static final String GOOD_RESULT = "ok";
    private static final String[] IGNORED_FILES = {".bash_history", ".bash_logout", ".bash_profile",
        ".bashrc", ".gtkrc", ".login", ".logout", ".profile", ".viminfo", ".wm_style", ".Xdefaults", ".Xresources",
        ".xinitrc'", ".xsession", "/etc", "/boot"};

    private ConsoleInterface() {}

    public static String encrypt() {

        HashMap<String, String> parameters = getParameters();

        if (parameters.get("exit").equals(EXIT_MODE)) {
            return EXIT_MODE;
        }

        Cryptanalyser.encrypt(
                parameters.get("inputFile"),
                parameters.get("outputFile"),
                Integer.parseInt(parameters.get("strKey")));

        System.out.println("The selected file has been encrypted.");
        return GOOD_RESULT;

    }

    public static String decrypt() {

        HashMap<String, String> parameters = getParameters();

        if (parameters.get("exit").equals(EXIT_MODE)) {
            return EXIT_MODE;
        }

        try {
            Cryptanalyser.decrypt(
                    parameters.get("inputFile"),
                    parameters.get("outputFile"),
                    Integer.parseInt(parameters.get("strKey")));
        } catch (IllegalCharacter ex) {
            ex.printStackTrace();
            return EXIT_MODE;
        }

        System.out.println("The selected file has been decrypted.");
        return GOOD_RESULT;

    }

    public static String hackBruteForce() {

        HashMap<String, String> parameters = getParametersForHack();

        if (parameters.get("exit").equals(EXIT_MODE)) {
            return EXIT_MODE;
        }

        try {
            Cryptanalyser.hackBruteForce(parameters.get("inputFile"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return EXIT_MODE;
        }

        System.out.println("The selected file has been hacked by brute force.");
        return GOOD_RESULT;

    }

    private static boolean isFilePathValid(String filePath) {

        if (filePath.length() == 0) {
            return false;
        }

        boolean isValid = true;
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            System.out.println("File doesn't exist. Please, select another file.");
            isValid = false;
        } else if (Files.isDirectory(path)) {
            System.out.println("It is a directory. Please, select file.");
            isValid = false;
        } else {
            for (String ignoredFile : IGNORED_FILES) {
                if (filePath.contains(ignoredFile)) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                System.out.println("File path contains contains invalid characters. Please, select another file.");
            }
        }

        return isValid;

    }

    private static boolean isKeyValid(String strKey) {

        if (strKey.length() == 0) {
            return false;
        }

        int key = 0;

        try {
            key = Integer.parseInt(strKey);
        } catch (NumberFormatException ex) {
            System.out.println("The specified key is not a number. Please, specify number key.");
            return false;
        }

        if (key < 0) {
            System.out.println("The specified key is less than zero. Please, specify another key.");
            return false;
        }

        return true;

    }

    private static HashMap<String, String> getParameters() {

        HashMap<String, String> result = new HashMap<>();
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
        } while (!isFilePathValid(inputFile));

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
        } while (!isFilePathValid(outputFile) || outputFile.equals(inputFile));

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
        } while (!isKeyValid(strKey));

        if (isExit(strKey)) {
            return result;
        } else {
            result.put("strKey", strKey);
        }

        return result;

    }

    private static HashMap<String, String> getParametersForHack() {

        HashMap<String, String> result = new HashMap<>();
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
        } while (!isFilePathValid(inputFile));

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
