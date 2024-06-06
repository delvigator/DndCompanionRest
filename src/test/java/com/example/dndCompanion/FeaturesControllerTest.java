package com.example.dndCompanion;

import com.example.dndCompanion.controllers.FeaturesController;
import com.example.dndCompanion.controllers.ItemController;
import com.example.dndCompanion.dto.FeatureDto;
import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.entity.Feature;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.entity.Peculiarity;
import com.example.dndCompanion.repository.FeatureRepository;
import com.example.dndCompanion.repository.ItemRepository;
import com.example.dndCompanion.services.FeatureService;
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
public class FeaturesControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private FeatureService service;
    @Mock
    private FeatureRepository repository;
    @InjectMocks
    private FeaturesController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllFeatures_ReturnsValidResponseEntity() throws Exception {
        Feature feature = new Feature();
        List<Feature> features = List.of(feature);

        Mockito.when(service.getAll()).thenReturn(features);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/features/getAll")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }

    @Test
    public void createFeature_success() throws Exception {
        FeatureDto dto = new FeatureDto();
        Feature feature = new Feature();
        Mockito.when(service.create(dto)).thenReturn(feature);

        String content = objectWriter.writeValueAsString(dto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/features/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void changeFeature_success() throws Exception {
        Feature feature = new Feature();
        Mockito.when(service.change(feature)).thenReturn(feature);

        String content = objectWriter.writeValueAsString(feature);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/features/change")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFeature_success() throws Exception {
        Feature feature = new Feature(Long.parseLong("1"),"имя","описание",List.of(mock(Peculiarity.class)),Map.of("skill",5));
        when(repository.findById(feature.getId())).thenReturn(Optional.of(feature));
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/features/delete/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
