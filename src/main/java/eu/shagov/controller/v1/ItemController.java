package eu.shagov.controller.v1;

import eu.shagov.constants.ItemConstants;
import eu.shagov.document.Item;
import eu.shagov.repository.ItemReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ItemController {

    @Autowired
    ItemReactiveRepository itemReactiveRepository;


    @GetMapping(ItemConstants.ITEM_END_POINT_V1)
    public Flux<Item> getAllItems(){

       return itemReactiveRepository.findAll();

    }

    @GetMapping(ItemConstants.ITEM_END_POINT_V1+"/{id}")
    public Mono<ResponseEntity<Item>> getOneItem(@PathVariable String id) {

        return itemReactiveRepository.findById(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(ItemConstants.ITEM_END_POINT_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Item> createItem(@RequestBody Item item){

        return itemReactiveRepository.save(item);


    }

    @DeleteMapping(ItemConstants.ITEM_END_POINT_V1 + "/{id}")
    public Mono<Void> deleteItem(@PathVariable String id){

        return itemReactiveRepository.deleteById(id);


    }

    //id and item to be updated in the req = path variable and request body - completed
    // using the id get the item from database - completed
    // updated the item retrieved with the value from the request body - completed
    // save the item - completed
    //return the saved item - completed
    @PutMapping(ItemConstants.ITEM_END_POINT_V1+"/{id}")
    public Mono<ResponseEntity<Item>> updateItem(@PathVariable String id,
                                                 @RequestBody Item item){

        return itemReactiveRepository.findById(id)
                .flatMap(currentItem -> {

                    currentItem.setPrice(item.getPrice());
                    currentItem.setDescription(item.getDescription());
                    return itemReactiveRepository.save(currentItem);
                })
                .map(updatedItem -> new ResponseEntity<>(updatedItem, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
