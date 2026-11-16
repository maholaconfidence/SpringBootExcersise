package org.example.springbootexcersise.Exceptions;

public class DuplicateResourceException extends RuntimeException {
public  DuplicateResourceException(String message){
    super(message);
}

}
