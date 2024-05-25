package com.example.dndCompanion.services;

import com.example.dndCompanion.dto.ItemDto;
import com.example.dndCompanion.entity.Item;
import com.example.dndCompanion.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
  private final  ItemRepository itemRepository;
  public List<Item> getAll(){
      return itemRepository.findAll();
  }
  public Item create(ItemDto dto){
      return itemRepository.save(Item.builder()
              .type(dto.getType())
              .name(dto.getName())
              .weight(dto.getWeight())
              .equipable(dto.isEquipable())
              .rarity(dto.getRarity())
              .description(dto.getDescription())
              .build());
  }

  public Item change(Item item){
      return itemRepository.save(item);
  }
  public void delete(Long id){
      itemRepository.deleteById(id);
  }
}
