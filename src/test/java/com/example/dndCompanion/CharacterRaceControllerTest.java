package com.example.dndCompanion;

import com.example.dndCompanion.controllers.CharacterRacesController;
import com.example.dndCompanion.controllers.ItemController;
import com.example.dndCompanion.dto.CharacterRaceDto;
import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.entity.CharacterRace;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.entity.Peculiarity;
import com.example.dndCompanion.entity.SkillMastery;
import com.example.dndCompanion.repository.CharacterRaceRepository;
import com.example.dndCompanion.repository.ItemRepository;
import com.example.dndCompanion.services.CharacterRaceService;
import com.example.dndCompanion.services.ItemService;
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
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CharacterRaceControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private CharacterRaceService service;
    @Mock
    private CharacterRaceRepository repository;
    @InjectMocks
    private CharacterRacesController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void getAllRaces_ReturnsValidResponseEntity() throws Exception {
        CharacterRace characterRace = new CharacterRace();
        List<CharacterRace> characterRaces = List.of(characterRace);

        Mockito.when(service.getAll()).thenReturn(characterRaces);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/races/getAll")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }
    @Test
    public void createRace_success() throws Exception {
        CharacterRaceDto dto = new CharacterRaceDto();
        CharacterRace characterRace = new CharacterRace();
        Mockito.when(service.create(dto)).thenReturn(characterRace);

        String content = objectWriter.writeValueAsString(dto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/races/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void changeRace_success() throws Exception {
        CharacterRace race = new CharacterRace();
        Mockito.when(service.change(race)).thenReturn(race);

        String content = objectWriter.writeValueAsString(race);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/races/change")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteRace_success() throws Exception {
        CharacterRace race = new CharacterRace(Long.parseLong("1"),"имя",3,"",true,List.of(mock(CharacterRace.class)),List.of(mock(Peculiarity.class)),List.of(mock(SkillMastery.class)), Map.of("",4));
        when(repository.findById(race.getId())).thenReturn(Optional.of(race));
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/races/delete/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
