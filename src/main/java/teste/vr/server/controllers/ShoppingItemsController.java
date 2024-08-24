package teste.vr.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.vr.server.dtos.request.ShoppingItemsRequestDTO;
import teste.vr.server.dtos.response.ShoppingItemsResponseDTO;
import teste.vr.server.services.ShoppingItemsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class ShoppingItemsController {

    private final ShoppingItemsService shoppingItemsService;

    @GetMapping({"/order/{orderId}"})
    public ResponseEntity<Page<ShoppingItemsResponseDTO>> findAllByOrderId(@PathVariable Long orderId, Pageable pageable) {

        Page<ShoppingItemsResponseDTO> shoppingItems = this.shoppingItemsService.findAllByOrderId(orderId, pageable);

        return ResponseEntity.ok().body(shoppingItems);
    }

    @PostMapping
    public ResponseEntity<ShoppingItemsResponseDTO> save(@RequestBody ShoppingItemsRequestDTO shoppingItemsDTO) {

        ShoppingItemsResponseDTO shoppingItems = this.shoppingItemsService.save(shoppingItemsDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingItems);
    }
}
