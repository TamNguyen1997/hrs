package hrs.parcel.management.api.exception.handler;

import hrs.parcel.management.api.exception.GuestNotCheckedInException;
import hrs.parcel.management.api.exception.InvalidGuestException;
import hrs.parcel.management.api.exception.ResourceNotFoundException;
import hrs.parcel.management.api.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            GuestNotCheckedInException.class,
            InvalidGuestException.class
    })
    public ResponseEntity<ErrorResponse> handleGuestException(Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse("invalid.guest", e.getMessage())
        );
    }

    @ExceptionHandler(value = {
            ResourceNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleResourceNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse("resource.not.found", e.getMessage())
        );
    }
}
