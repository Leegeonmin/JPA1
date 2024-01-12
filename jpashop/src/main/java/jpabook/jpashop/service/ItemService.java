package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public  void saveItem(Item item){
        Long saveId = itemRepository.save(item);
    }

    //update
    @Transactional
    public Item updateItem(Long itemId, Book book){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(book.getPrice());
        findItem.setName(book.getName());
        findItem.setStockQuantity(book.getStockQuantity());
        return findItem;
    }
    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
