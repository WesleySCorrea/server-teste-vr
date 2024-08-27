package teste.vr.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teste.vr.server.dtos.request.OrderRequestDTO;
import teste.vr.server.dtos.response.OrderResponseDTO;
import teste.vr.server.services.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> findAllPublic(Pageable pageable) {

        Page<OrderResponseDTO> orders = this.orderService.findAllOrders(pageable);

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Page<OrderResponseDTO>> findAllByClientId(Pageable pageable, @PathVariable Long id) {

        Page<OrderResponseDTO> orders = this.orderService.findAllOrdersByClientId(id, pageable);

        return ResponseEntity.ok().body(orders);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Page<OrderResponseDTO>> findAllByProductId(Pageable pageable, @PathVariable Long id) {

        Page<OrderResponseDTO> orders = this.orderService.findAllOrdersByProductId(id,pageable);

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable Long id) {

        OrderResponseDTO order = this.orderService.findOrderById(id);

        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

        OrderResponseDTO order = this.orderService.saveOrder(orderRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PatchMapping("/confirm/{id}")
    public ResponseEntity<OrderResponseDTO> confirmOrder(@PathVariable Long id) {

        OrderResponseDTO order = this.orderService.confirmOrder(id);

        return ResponseEntity.ok().body(order);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id,@RequestBody OrderRequestDTO orderResponseDTO) {
//
//        OrderResponseDTO orderPersisted = orderService.updateOrder(id, orderResponseDTO);
//
//        return ResponseEntity.ok().body(orderPersisted);
//    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {

        this.orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }
}
