package com.dsacademy.exception;

import java.io.IOException;

public class FileLoadException extends IOException {

    public FileLoadException(String message) {
        super(message);
    }
}
