package com.mercadolibre.pens_luis_bootcamp_final.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponseDto {
    private HttpStatus httpStatus;
    private String message;
}
