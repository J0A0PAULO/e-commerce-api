package br.com.e_commerce.api.exeption;

import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler{

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> NotFound(NotFound notFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getMessage());
    };

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<String> badRequest(BadRequest badRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequest.getMessage());
    }

}
