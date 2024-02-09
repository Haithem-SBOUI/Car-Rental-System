package de.tekup.carrentalsystembackend.core.exception.type;

public class InvalidStatusException extends RuntimeException {

        public InvalidStatusException(String message){
            super(message);
        }

}
