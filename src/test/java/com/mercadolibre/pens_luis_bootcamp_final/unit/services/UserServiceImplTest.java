package com.mercadolibre.pens_luis_bootcamp_final.unit.services;

import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.BasicResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.exceptions.ApiException;
import com.mercadolibre.pens_luis_bootcamp_final.models.User;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.*;
import com.mercadolibre.pens_luis_bootcamp_final.services.UserServiceImpl;
import com.mercadolibre.pens_luis_bootcamp_final.util.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserRepository userRepositoryMock;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepositoryMock);
    }

    @Test
    @DisplayName("Invalid credentials")
    void createUser1() throws Exception {
        Exception e = assertThrows(ApiException.class,
                () -> userService.createUser("", "1234"));
        assertEquals("Credentials can't be empty", e.getMessage());
    }

    @Test
    @DisplayName("Existing user")
    void createUser2() throws Exception {
        Mockito.when(userRepositoryMock.existsByUsername(Mockito.any()))
                .thenReturn(true);
        Exception e = assertThrows(ApiException.class,
                () -> userService.createUser("mati123", "1234"));
        assertEquals("Username already exists", e.getMessage());
    }

    @Test
    @DisplayName("Existing user")
    void createUser3() throws Exception {
        Mockito.when(userRepositoryMock.existsByUsername(Mockito.any()))
                .thenReturn(false);
        BasicResponseDto expected = new BasicResponseDto(HttpStatus.OK, "User created");
        BasicResponseDto actual = userService.createUser("ale123", "1234");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User not found")
    void changeRole1() throws Exception {
        Exception e = assertThrows(ApiException.class,
                () -> userService.changeRole("brian", "admin"));
        assertEquals("User doesn't exist", e.getMessage());
    }

    @Test
    @DisplayName("User must be admin")
    void changeRole2() throws Exception {
        User user = new User();
        user.setRole("REGULAR");
        Mockito.when(userRepositoryMock.findByUsername(Mockito.any()))
                .thenReturn(user);
        Exception e = assertThrows(ApiException.class,
                () -> userService.changeRole("brian", "pablo"));
        assertEquals("Only admins can change roles", e.getMessage());
    }

    @Test
    @DisplayName("Role changed successfully")
    void changeRole3() throws Exception {
        User user = new User();
        user.setRole("ADMIN");
        Mockito.when(userRepositoryMock.findByUsername(Mockito.any()))
                .thenReturn(user);
        BasicResponseDto expected = new BasicResponseDto(HttpStatus.OK, "User brian switched to role REGULAR");
        BasicResponseDto actual = userService.changeRole("brian", "pablo");
        assertEquals(expected, actual);
    }


}