package com.javarush.cryptoanalyser.workalgorithm;

public class IllegalCharacterException extends RuntimeException {

    private String errorMessage;

    public IllegalCharacterException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
