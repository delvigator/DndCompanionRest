package com.example.dndCompanion;


import com.example.dndCompanion.controllers.AuthController;
import com.example.dndCompanion.dto.RegistrationUserDto;
import com.example.dndCompanion.dto.jwt.JwtRequest;
import com.example.dndCompanion.dto.jwt.JwtResponseDto;
import com.example.dndCompanion.entity.GameCharacter;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.services.AuthService;
import com.example.dndCompanion.services.UserService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
   private AuthService authService;

    @Mock
    private UserService userService;

    @InjectMocks
   private AuthController controller;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void createNewUser_ReturnsValidResponseEntity() throws Exception {
        RegistrationUserDto dto=new RegistrationUserDto("slpvs2002@gmail.com","test","test");
        String content = objectWriter.writeValueAsString(dto);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void auth_ReturnsValidResponseEntity() throws Exception {
        JwtRequest request=new JwtRequest("slpvs2002@gmail.com","test");
        JwtResponseDto jwtResponseDto=new JwtResponseDto("", List.of(mock(GameCharacter.class)),1,List.of(mock(Item.class)));
        ResponseEntity responseEntity= new ResponseEntity(HttpStatus.OK);
        when(authService.createAuthToken(request)).thenReturn(responseEntity);
        String content = objectWriter.writeValueAsString(request);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());
    }

}
