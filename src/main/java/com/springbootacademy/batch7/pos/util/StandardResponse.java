package com.springbootacademy.batch7.pos.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardResponse {
    private int code; // send a code to the frontend.
    private String message;
    private Object data; // By taking the data type as the Object, it can send any data type.

    public StandardResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        // To fulfill the constructor, data must be sent to these data(parameters) from the StandardResponse.
    }
}
