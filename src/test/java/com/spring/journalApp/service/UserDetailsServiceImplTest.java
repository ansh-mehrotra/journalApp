package com.spring.journalApp.service;

import com.spring.journalApp.repository.UserRepository;
import com.spring.journalApp.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import com.spring.journalApp.entity.User;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        // Create a mock User entity
        User mockUser = new User();
        mockUser.setUserName("ram");
        mockUser.setPassword("fadad");
        mockUser.setRoles(Collections.singletonList("User"));

        // Mock the repository to return the mock User entity
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        // Call the service method and assert the result
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("ram", userDetails.getUsername());
    }
}
