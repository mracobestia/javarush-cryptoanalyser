package com.javarush.cryptoanalyser.programinterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParameterValidation {

    private static final String[] IGNORED_FILES = {".bash_history", ".bash_logout", ".bash_profile",
            ".bashrc", ".gtkrc", ".login", ".logout", ".profile", ".viminfo", ".wm_style", ".Xdefaults", ".Xresources",
            ".xinitrc'", ".xsession", "/etc", "/boot"};

    private ParameterValidation() { }

    public static boolean isFilePathValid(String filePath) {

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

    public static boolean isKeyValid(String strKey) {

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


}
