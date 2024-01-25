package de.tekup.carrentalsystembackend.core.exception.type;

public class AlreadyExistsException extends RuntimeException {

        public AlreadyExistsException(String message){
            super(message);
        }

}
