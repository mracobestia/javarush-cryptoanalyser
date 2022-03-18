package com.javarush.cryptoanalyser.workalgorithm;

public class IllegalCharacter extends RuntimeException {

    public IllegalCharacter() {
        System.out.println("An unknown character was passed. The program is being hacked!");
    }

}
