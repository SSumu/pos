package com.springbootacademy.batch7.pos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{ // RuntimeException from the Java class.
    // A constructor. Import the NotFoundException from the class has created inside the exception package, and it must not be from the Java. This NotFoundException constructor is worked according to the RuntimeException class as the NotFoundException class was inherited by the RuntimeException class.
    public NotFoundException(String message){
        super(message); // super is a constructor in the inherited RuntimeException class that is called when a message is passed.
    }
//  We must advise what should be returned to the frontend when the NOT_FOUND is called.
}
