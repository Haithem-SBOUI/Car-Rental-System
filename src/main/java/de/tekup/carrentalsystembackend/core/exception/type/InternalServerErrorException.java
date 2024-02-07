package de.tekup.carrentalsystembackend.core.exception.type;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }

}
