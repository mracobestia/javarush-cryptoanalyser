package com.javarush.cryptoanalyser.workalgorithm;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Cryptanalyser {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '-', '—',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Э', 'Ю', 'Я', '\n', '\r'};

    private static final ArrayList<String> FREQUENCE_DICTIONARY = new ArrayList<>();

    private Cryptanalyser() {}

    static {
        FREQUENCE_DICTIONARY.add("и");
        FREQUENCE_DICTIONARY.add("в");
        FREQUENCE_DICTIONARY.add("не");
        FREQUENCE_DICTIONARY.add("на");
        FREQUENCE_DICTIONARY.add("что");
        FREQUENCE_DICTIONARY.add("я");
        FREQUENCE_DICTIONARY.add("с");
        FREQUENCE_DICTIONARY.add("он");
        FREQUENCE_DICTIONARY.add("а");
        FREQUENCE_DICTIONARY.add("как");
        FREQUENCE_DICTIONARY.add("но");
        FREQUENCE_DICTIONARY.add("его");
        FREQUENCE_DICTIONARY.add("к");
        FREQUENCE_DICTIONARY.add("это");
        FREQUENCE_DICTIONARY.add("все");
        FREQUENCE_DICTIONARY.add("по");
        FREQUENCE_DICTIONARY.add("из");
        FREQUENCE_DICTIONARY.add("у");
        FREQUENCE_DICTIONARY.add("она");
        FREQUENCE_DICTIONARY.add("за");
        FREQUENCE_DICTIONARY.add("от");
        FREQUENCE_DICTIONARY.add("так");
        FREQUENCE_DICTIONARY.add("же");
        FREQUENCE_DICTIONARY.add("о");
        FREQUENCE_DICTIONARY.add("ты");
        FREQUENCE_DICTIONARY.add("они");
        FREQUENCE_DICTIONARY.add("было");
        FREQUENCE_DICTIONARY.add("мы");
        FREQUENCE_DICTIONARY.add("бы");
        FREQUENCE_DICTIONARY.add("ее");
        FREQUENCE_DICTIONARY.add("вы");
        FREQUENCE_DICTIONARY.add("для");
        FREQUENCE_DICTIONARY.add("мне");
        FREQUENCE_DICTIONARY.add("если");
        FREQUENCE_DICTIONARY.add("то");
        FREQUENCE_DICTIONARY.add("только");
        FREQUENCE_DICTIONARY.add("меня");
        FREQUENCE_DICTIONARY.add("был");
        FREQUENCE_DICTIONARY.add("когда");
        FREQUENCE_DICTIONARY.add("их");
        FREQUENCE_DICTIONARY.add("или");
        FREQUENCE_DICTIONARY.add("чтобы");
        FREQUENCE_DICTIONARY.add("сказал");
        FREQUENCE_DICTIONARY.add("до");
        FREQUENCE_DICTIONARY.add("уже");
        FREQUENCE_DICTIONARY.add("ему");
        FREQUENCE_DICTIONARY.add("да");
        FREQUENCE_DICTIONARY.add("нет");
        FREQUENCE_DICTIONARY.add("может");
        FREQUENCE_DICTIONARY.add("ни");
        FREQUENCE_DICTIONARY.add("даже");
        FREQUENCE_DICTIONARY.add("чем");
        FREQUENCE_DICTIONARY.add("него");
        FREQUENCE_DICTIONARY.add("быть");
        FREQUENCE_DICTIONARY.add("были");
        FREQUENCE_DICTIONARY.add("была");
        FREQUENCE_DICTIONARY.add("вот");
        FREQUENCE_DICTIONARY.add("время");
        FREQUENCE_DICTIONARY.add("себя");
        FREQUENCE_DICTIONARY.add("ли");
        FREQUENCE_DICTIONARY.add("под");
        FREQUENCE_DICTIONARY.add("во");
        FREQUENCE_DICTIONARY.add("со");
        FREQUENCE_DICTIONARY.add("очень");
        FREQUENCE_DICTIONARY.add("есть");
        FREQUENCE_DICTIONARY.add("где");
        FREQUENCE_DICTIONARY.add("теперь");
        FREQUENCE_DICTIONARY.add("там");
        FREQUENCE_DICTIONARY.add("при");
        FREQUENCE_DICTIONARY.add("этого");
        FREQUENCE_DICTIONARY.add("того");
        FREQUENCE_DICTIONARY.add("будет");
        FREQUENCE_DICTIONARY.add("здесь");
        FREQUENCE_DICTIONARY.add("нас");
        FREQUENCE_DICTIONARY.add("раз");
        FREQUENCE_DICTIONARY.add("этот");
        FREQUENCE_DICTIONARY.add("ничего");
        FREQUENCE_DICTIONARY.add("потом");
        FREQUENCE_DICTIONARY.add("этом");
        FREQUENCE_DICTIONARY.add("можно");
        FREQUENCE_DICTIONARY.add("кто");
        FREQUENCE_DICTIONARY.add("через");
        FREQUENCE_DICTIONARY.add("вас");
        FREQUENCE_DICTIONARY.add("них");
        FREQUENCE_DICTIONARY.add("тебя");
        FREQUENCE_DICTIONARY.add("один");
        FREQUENCE_DICTIONARY.add("без");
        FREQUENCE_DICTIONARY.add("после");
        FREQUENCE_DICTIONARY.add("себе");
        FREQUENCE_DICTIONARY.add("ну");
        FREQUENCE_DICTIONARY.add("вам");
        FREQUENCE_DICTIONARY.add("тебе");
        FREQUENCE_DICTIONARY.add("больше");
        FREQUENCE_DICTIONARY.add("тут");
        FREQUENCE_DICTIONARY.add("сейчас");
        FREQUENCE_DICTIONARY.add("человек");
        FREQUENCE_DICTIONARY.add("который");
        FREQUENCE_DICTIONARY.add("том");
        FREQUENCE_DICTIONARY.add("ним");
        FREQUENCE_DICTIONARY.add("более");
        FREQUENCE_DICTIONARY.add("которые");
        FREQUENCE_DICTIONARY.add("им");
        FREQUENCE_DICTIONARY.add("тогда");
        FREQUENCE_DICTIONARY.add("несколько");
        FREQUENCE_DICTIONARY.add("глаза");
        FREQUENCE_DICTIONARY.add("нам");
        FREQUENCE_DICTIONARY.add("пока");
        FREQUENCE_DICTIONARY.add("тем");
        FREQUENCE_DICTIONARY.add("т");
        FREQUENCE_DICTIONARY.add("просто");
        FREQUENCE_DICTIONARY.add("над");
        FREQUENCE_DICTIONARY.add("тоже");
        FREQUENCE_DICTIONARY.add("всех");
        FREQUENCE_DICTIONARY.add("перед");
        FREQUENCE_DICTIONARY.add("надо");
        FREQUENCE_DICTIONARY.add("потому");
        FREQUENCE_DICTIONARY.add("тот");
        FREQUENCE_DICTIONARY.add("мог");
        FREQUENCE_DICTIONARY.add("об");
        FREQUENCE_DICTIONARY.add("ей");
        FREQUENCE_DICTIONARY.add("лишь");
        FREQUENCE_DICTIONARY.add("ведь");
    }

    public static void encrypt(String inputFilePath, String outputFilePath, int key) {

        Path inputPath = Paths.get(inputFilePath);
        Path outputPath = Paths.get(outputFilePath);

        if (key > ALPHABET.length) {
            key = key % ALPHABET.length;
        }

        if (!clearOutputFileBefore(outputFilePath)) {
            return;
        }

        try (BufferedReader buffReader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
             BufferedWriter buffWriter = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            while (buffReader.ready()) {

                char symbol = (char) buffReader.read();
                boolean isSymbolWrite = false;

                for (int i = 0; i < ALPHABET.length; i++) {
                    if (ALPHABET[i] == symbol) {
                        int newIndex = (i + key) % ALPHABET.length;
                        buffWriter.write(String.valueOf(ALPHABET[newIndex]));
                        isSymbolWrite = true;
                        break;
                    }
                }

                if (!isSymbolWrite) {
                    buffWriter.write(String.valueOf(symbol));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decrypt(String inputFilePath, String outputFilePath, int key) throws IllegalCharacter {

        Path inputPath = Paths.get(inputFilePath);
        Path outputPath = Paths.get(outputFilePath);

        if (key > ALPHABET.length) {
            key = key % ALPHABET.length;
        }

        boolean isSymbolWrite = false;

        if (!clearOutputFileBefore(outputFilePath)) {
            return;
        }

        try (BufferedReader buffReader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8);
             BufferedWriter buffWriter = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            while(buffReader.ready()) {

                char symbol = (char) buffReader.read();
                isSymbolWrite = false;

                for (int i = 0; i < ALPHABET.length; i++) {
                    if (ALPHABET[i] == symbol) {
                        int newIndex = (i - key) >= 0 ? (i - key) : (ALPHABET.length + i - key);
                        buffWriter.write(String.valueOf(ALPHABET[newIndex]));
                        isSymbolWrite = true;
                        break;
                    }
                }

                if (!isSymbolWrite) {
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isSymbolWrite) {
            throw new IllegalCharacter();
        }

    }

    public static void hackBruteForce(String inputFilePath) throws IllegalCharacter, IOException {

        ArrayList<String> filesArray = new ArrayList<>();

        String separator = FileSystems.getDefault().getSeparator();

        String newCatalog = Path.of(inputFilePath).getParent().toString() + separator + "BruteForceFiles";
        Path newCatalogPath = Path.of(newCatalog);
        if (!Files.exists(newCatalogPath)) {
            Files.createDirectory(Path.of(newCatalog));
        }

        for (int i = 1; i < ALPHABET.length; i++) {
            String outputFilePath = newCatalog + separator + "ResultFile" + i + ".txt";
            filesArray.add(outputFilePath);

            decrypt(inputFilePath, outputFilePath, i);
        }

        String maxWordsCountFile = "";
        int maxWordsCount = 0;
        for (String file : filesArray) {
            Integer wordsCount = 0;

            try (BufferedReader buffReader = Files.newBufferedReader(Path.of(file), StandardCharsets.UTF_8)) {

                while (buffReader.ready()) {
                    String textLine = buffReader.readLine();
                    StringTokenizer tokenizer = new StringTokenizer(textLine);
                    while (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken().toLowerCase();
                        if(FREQUENCE_DICTIONARY.contains(token)) {
                            wordsCount += 1;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            if (wordsCount > maxWordsCount) {
                maxWordsCount = wordsCount;
                maxWordsCountFile = file;
            }
        }

        System.out.println("The best result is in the file: " + maxWordsCountFile);
        System.out.println("Other results is in the catalog: " + newCatalog);

    }

    private static boolean clearOutputFileBefore(String outputFilePath) {
        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.print("");
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
