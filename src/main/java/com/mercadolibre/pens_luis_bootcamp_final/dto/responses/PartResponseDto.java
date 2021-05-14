package com.mercadolibre.pens_luis_bootcamp_final.dto.responses;

import com.mercadolibre.pens_luis_bootcamp_final.dto.PartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PartResponseDto {
    private List<PartDto> parts;
}
