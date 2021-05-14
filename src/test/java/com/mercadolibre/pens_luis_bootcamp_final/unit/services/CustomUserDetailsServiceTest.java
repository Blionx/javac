package com.mercadolibre.pens_luis_bootcamp_final.unit.services;

import com.mercadolibre.pens_luis_bootcamp_final.models.User;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.UserRepository;
import com.mercadolibre.pens_luis_bootcamp_final.services.CustomUserDetails;
import com.mercadolibre.pens_luis_bootcamp_final.services.CustomUserDetailsService;
import com.mercadolibre.pens_luis_bootcamp_final.util.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    private UserRepository userRepositoryMock;
    private CustomUserDetailsService userService;

    @BeforeEach
    void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        userService = new CustomUserDetailsService(userRepositoryMock);
    }

    @Test
    @DisplayName("User not found")
    void loadUser1() throws Exception {
        Exception e = assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("brian"));
        assertEquals("User Not Found", e.getMessage());
    }

    @Test
    @DisplayName("User found")
    void loadUser2() throws Exception {
        User user = new User();
        user.setUsername("brian");
        user.setPassword("1234");
        user.setRole("ADMIN");
        user.setId(2L);
        UserDetails expected = new CustomUserDetails(user);
        Mockito.when(userRepositoryMock.findByUsername(Mockito.any()))
                .thenReturn(user);
        UserDetails actual = userService.loadUserByUsername("brian");
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
    }

}