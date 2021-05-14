package com.mercadolibre.pens_luis_bootcamp_final.services;

import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.BasicResponseDto;

public interface UserService {

    BasicResponseDto createUser(String username, String password) throws Exception;
    BasicResponseDto changeRole(String s, String username) throws Exception;


}
