package org.example.dto;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
public class ErrorResponse implements Serializable {
    private String message;
    private String error;
    private int status;
    private String timestamp;
}