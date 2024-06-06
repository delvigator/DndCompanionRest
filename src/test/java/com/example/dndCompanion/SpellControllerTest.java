package com.example.dndCompanion;

import com.example.dndCompanion.controllers.CharacterClassesController;
import com.example.dndCompanion.controllers.SpellController;
import com.example.dndCompanion.dto.CharacterClassDto;
import com.example.dndCompanion.dto.SpellDto;
import com.example.dndCompanion.entity.CharacterClass;
import com.example.dndCompanion.entity.ClassSkillsPerLevel;
import com.example.dndCompanion.entity.Peculiarity;
import com.example.dndCompanion.entity.Spell;
import com.example.dndCompanion.repository.CharacterClassRepository;
import com.example.dndCompanion.repository.SpellRepository;
import com.example.dndCompanion.services.CharacterClassService;
import com.example.dndCompanion.services.SpellService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SpellControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private SpellService service;
    @Mock
    private SpellRepository repository;
    @InjectMocks
    private SpellController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllSpells_ReturnsValidResponseEntity() throws Exception {
        Spell spell1 = new Spell(Long.getLong("1"), "Название", "Описание",
                "Дистанция", List.of("Класс1", "Класс2"), "Школа", 5, true, Map.of("", true), "", "");
        List<Spell> spells = List.of(spell1);

        Mockito.when(service.getAll()).thenReturn(spells);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/spells/getAll")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }

    @Test
    public void createSpell_success() throws Exception {
        SpellDto dto = new SpellDto();
        Spell spell = new Spell();
        Mockito.when(service.create(dto)).thenReturn(spell);

        String content = objectWriter.writeValueAsString(dto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/spells/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void changeSpell_success() throws Exception {
        Spell spell = new Spell();
        Mockito.when(service.change(spell)).thenReturn(spell);

        String content = objectWriter.writeValueAsString(spell);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/spells/change")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteSpell_success() throws Exception {
        Spell spell = new Spell(Long.getLong("1"), "Название", "Описание",
                "Дистанция", List.of("Класс1", "Класс2"), "Школа", 5,
                true, Map.of("", true), "", "");
        when(repository.findById(spell.getId())).thenReturn(Optional.of(spell));
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/spells/delete/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
