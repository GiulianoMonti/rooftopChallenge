package com.giulian.challenge.exception;

import com.sun.xml.bind.v2.TODO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private long fieldValue;

    // viendo como devolver 3 datos en la excepcion
    // TODO tambien puedo usar el messagesource

    public ResourceNotFoundException( long fieldValue) {
        super(String.format("text not found with id '%s", fieldValue));

        this.fieldValue = fieldValue;
    }


    public long getFieldValue() {
        return fieldValue;
    }
}
