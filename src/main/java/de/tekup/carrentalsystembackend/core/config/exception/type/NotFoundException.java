package de.tekup.carrentalsystembackend.core.config.exception.type;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}