package com.mercadolibre.pens_luis_bootcamp_final.unit.util;

import com.mercadolibre.pens_luis_bootcamp_final.dto.PartDto;
import com.mercadolibre.pens_luis_bootcamp_final.models.Part;
import com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures.PartsFixture;
import com.mercadolibre.pens_luis_bootcamp_final.util.MockitoExtension;
import com.mercadolibre.pens_luis_bootcamp_final.util.PartMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PartMapperTest {

    private PartMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PartMapper();
    }

    @Test
    @DisplayName("Map single object")
    void mapObject() {
        PartDto expected = PartsFixture.defaultPartDto();
        PartDto actual = mapper.map(PartsFixture.defaultPart1(), false);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("ReverseMap single object")
    void reverseMapObject() throws Exception {
        Part expected = PartsFixture.defaultPart5();
        Part actual = mapper.reverseMap(PartsFixture.defaultNewPartDto2(), PartsFixture.defaultProvider());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPartCode(), actual.getPartCode());
        assertEquals(expected.getLastModification(), actual.getLastModification());
    }

    @Test
    @DisplayName("Map list")
    void mapList() {
        List<PartDto> expected = PartsFixture.defaultListPartDto();
        List<PartDto> actual = mapper.mapList(PartsFixture.defaultListPartModel(), false);
        assertEquals(expected, actual);
    }

}