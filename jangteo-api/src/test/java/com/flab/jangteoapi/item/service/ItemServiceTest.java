package com.flab.jangteoapi.item.service;

import com.flab.jangteoapi.item.domain.Item;
import com.flab.jangteoapi.item.dto.ItemSaveRequest;
import com.flab.jangteoapi.item.exception.ItemNotFoundException;
import com.flab.jangteoapi.item.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemService itemService;

    @Test
    @DisplayName("물품 생성에 성공합니다.")
    void createItemSuccess() {

        ItemSaveRequest request = ItemSaveRequest.builder()
                .itemId(UUID.randomUUID())
                .itemName("테스트 물품")
                .description("테스트 물품의 설명입니다.")
                .category("Electronics")
                .imageUrl("http://example.com/image.png")
                .originalPrice(100000)
                .condition("NEW")
                .status("ACTIVE")
                .build();

        Item savedItem = Item.builder()
                .itemId(request.getItemId())
                .itemName(request.getItemName())
                .description(request.getDescription())
                .category(request.getCategory())
                .imageUrl(request.getImageUrl())
                .originalPrice(request.getOriginalPrice())
                .condition(request.getCondition())
                .status(request.getStatus())
                .build();

        given(itemRepository.save(any(Item.class))).willReturn(savedItem);

        Item result = itemService.createItem(request);

        assertThat(result).isEqualTo(savedItem);

        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    @DisplayName("물품 조회에 성공합니다.")
    void getItemSuccess() {

        UUID itemId = UUID.randomUUID();

        Item foundItem = Item.builder()
                .itemId(itemId)
                .itemName("테스트 물품")
                .build();

        given(itemRepository.findById(itemId)).willReturn(Optional.of(foundItem));

        Item result = itemService.getItem(itemId);

        assertThat(result).isEqualTo(foundItem);

        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    @DisplayName("물품이 존재하지 않아, 조회에 실패합니다.")
    void getItemFailItemNotFound() {

        UUID itemId = UUID.randomUUID();

        given(itemRepository.findById(itemId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.getItem(itemId))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("물품이 존재하지 않습니다.");

        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    @DisplayName("물품 수정에 성공합니다.")
    void updateItemSuccess() {

        UUID itemId = UUID.randomUUID();

        Item existingItem = Item.builder()
                .itemId(UUID.randomUUID())
                .itemName("기존 물품명")
                .build();

        ItemSaveRequest updateRequest = ItemSaveRequest.builder()
                .itemName("변경 물품명")
                .build();

        Item updatedItem = existingItem.toBuilder()
                .itemName("변경 물품명")
                .build();

        given(itemRepository.findById(itemId)).willReturn(Optional.of(existingItem));
        given(itemRepository.save(any(Item.class))).willReturn(updatedItem);

        Item result = itemService.updateItem(itemId, updateRequest);

        assertThat(result.getItemName()).isEqualTo("변경 물품명");

        verify(itemRepository, times(1)).findById(itemId);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    @DisplayName("물품이 존재하지 않아 수정에 실패합니다.")
    void updateItemFailItemNotFound() {

        UUID itemId = UUID.randomUUID();

        ItemSaveRequest updateRequest = new ItemSaveRequest();

        given(itemRepository.findById(itemId)).willReturn(Optional.empty());

        assertThatThrownBy(() -> itemService.updateItem(itemId, updateRequest))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("물품이 존재하지 않습니다.");

        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    @DisplayName("물품 삭제에 성공합니다.")
    void deleteItemSuccess() {

        UUID itemId = UUID.randomUUID();
        given(itemRepository.existsById(itemId)).willReturn(true);

        itemService.deleteItem(itemId);

        verify(itemRepository, times(1)).existsById(itemId);
        verify(itemRepository, times(1)).deleteById(itemId);
    }

    @Test
    @DisplayName("물품이 존재하지 않아 삭제에 실패합니다.")
    void deleteItemFailItemNotFound() {

        UUID itemId = UUID.randomUUID();

        given(itemRepository.existsById(itemId)).willReturn(false);

        assertThatThrownBy(() -> itemService.deleteItem(itemId))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("물품이 존재하지 않습니다.");

        verify(itemRepository, times(1)).existsById(itemId);
        verify(itemRepository, never()).deleteById(any(UUID.class));
    }
}
