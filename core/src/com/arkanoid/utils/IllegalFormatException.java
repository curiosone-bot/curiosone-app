package com.arkanoid.utils;

import com.arkanoid.objects.Level;

/**
 * Exception throwable in {@link Level#Level(int)}.
 * If the text file to load a level is not in the correct format the exception is thrown.
 * @author Simone
 *
 */
public class IllegalFormatException extends Exception {

    public IllegalFormatException(String message) {
        super(message);
    }

}
