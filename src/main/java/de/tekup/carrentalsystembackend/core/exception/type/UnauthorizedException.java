package de.tekup.carrentalsystembackend.core.exception.type;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

}
