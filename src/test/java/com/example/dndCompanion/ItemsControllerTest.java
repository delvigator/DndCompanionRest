package com.example.dndCompanion;

import com.example.dndCompanion.controllers.ItemController;
import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.repository.ItemRepository;
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
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private ItemService service;
    @Mock
    private ItemRepository repository;
    @InjectMocks
    private ItemController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void getAllItems_ReturnsValidResponseEntity() throws Exception {
        Item item = new Item();
        List<Item> items = List.of(item);

        Mockito.when(service.getAll()).thenReturn(items);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/items/getAll")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }

    @Test
    public void createItem_success() throws Exception {
        ItemDto dto = new ItemDto();
        Item item = new Item();
        Mockito.when(service.create(dto)).thenReturn(item);

        String content = objectWriter.writeValueAsString(dto);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/items/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void changeItem_success() throws Exception {
        Item item = new Item();
        Mockito.when(service.change(item)).thenReturn(item);

        String content = objectWriter.writeValueAsString(item);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/items/change")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteItem_success() throws Exception {
        Item item = new Item(Long.parseLong("1"),"имя",8,"Тип",
                true,"описание","редкость");
        when(repository.findById(item.getId())).thenReturn(Optional.of(item));
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/items/delete/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}
