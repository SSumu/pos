package com.springbootacademy.batch7.pos.advisor;

import com.springbootacademy.batch7.pos.exception.NotFoundException;
import com.springbootacademy.batch7.pos.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // @RestControllerAdvice = @ControllerAdvice + @ResponseBody. This advice to the exception class(like NotFoundException that we have created) what it should do if such kind of exception is going to be occurred.
public class AppWideExceptionHandler {
//    These exception classes have been written by the company which you are going to be occupied. You just only have to use it. This is an advisor class.
    @ExceptionHandler(NotFoundException.class) // What this annotation does is tell this annotation what class was called as soon as when the NotFoundException class is called. Otherwise, it does not know to catch it when it is called. NotFoundException must the exception class which we have created a customized class, and it is not from the Java class.
//   If there are no(null) data in the database, we must return it in a standard way.
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){ // e means the message from the NotFoundException. That means if there is a data in the message parameter in the NotFoundException constructor, that is called from the RuntimeException and then data in the message parameter is passed to this e parameter.
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error Coming",e.getMessage() + " Sadeepal"),
                HttpStatus.NOT_FOUND // Create a new object from StandardResponse. For the error response, status code is 404. Status code is 404 for error response. e.getMessage():- message comes from e instead of data. + "Sadeepal" is added to check whether this is called.
        );
    }

//  When you create an exception class in the exception package, that exception class must be written inside this class. No matter how many exception classes are created, there should only be one class file in the advisor. All exception classes created should be included in the class in that advisor.
}
