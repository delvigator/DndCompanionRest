package com.example.dndCompanion;

import com.example.dndCompanion.controllers.CharacterClassesController;
import com.example.dndCompanion.dto.CharacterClassDto;
import com.example.dndCompanion.dto.ClassSkillsPerLevelDto;
import com.example.dndCompanion.dto.ClassSkillsTextDto;
import com.example.dndCompanion.entity.CharacterClass;
import com.example.dndCompanion.entity.ClassSkillsPerLevel;
import com.example.dndCompanion.entity.Peculiarity;
import com.example.dndCompanion.repository.CharacterClassRepository;
import com.example.dndCompanion.services.CharacterClassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class CharacterClassControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private CharacterClassService service;
    @Mock
    private CharacterClassRepository classRepository;
    @InjectMocks
    private CharacterClassesController characterClassesController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(characterClassesController).build();
    }

    @Test
    public void getAllClasses_valid() throws Exception {

        CharacterClass characterClass1 = new CharacterClass();

        List<CharacterClass> characterClasses = List.of(characterClass1);

        Mockito.when(service.getAll()).thenReturn(characterClasses);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/classes/getAll")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }

    @Test
    public void createClass_success() throws Exception {
        CharacterClassDto dto = new CharacterClassDto();
        CharacterClass characterClass = new CharacterClass();
        Mockito.when(service.create(dto)).thenReturn(characterClass);

        String content = objectWriter.writeValueAsString(dto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/classes/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void changeCharacterClass_success() throws Exception {
        CharacterClass characterClass = new CharacterClass();
        Mockito.when(service.change(characterClass)).thenReturn(characterClass);

        String content = objectWriter.writeValueAsString(characterClass);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/classes/change")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCharacterClass_success() throws Exception {
        CharacterClass characterClass = new CharacterClass(Long.parseLong("1"), "имя", 1, "описание", 4,
                4, List.of(""), List.of(""),
                4, List.of(mock(ClassSkillsPerLevel.class)), List.of(mock(Peculiarity.class)));
        int id=characterClass.getId().intValue();
        when(classRepository.findById(characterClass.getId())).thenReturn(Optional.of(characterClass));
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/classes/delete/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}