package teste.vr.server.dtos.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import teste.vr.server.entities.Order;
import teste.vr.server.entities.Products;
import teste.vr.server.entities.ShoppingItems;
import teste.vr.server.entities.ShoppingItemsId;

@Getter
@Setter
@RequiredArgsConstructor
public class ShoppingItemsRequestDTO {

        private Long orderId;
        private Long productId;
        private Integer quantity;

        public ShoppingItems converterToEntity() {
            ShoppingItems shoppingItems = new ShoppingItems();

            ShoppingItemsId shoppingItemsId = new ShoppingItemsId();
            shoppingItemsId.setOrderId(this.orderId);
            shoppingItemsId.setProductId(this.productId);

            Order order = new Order();
            order.setId(this.orderId);
            shoppingItems.setOrder(order);

            Products products = new Products();
            products.setId(this.productId);
            shoppingItems.setProduct(products);

            shoppingItems.setId(shoppingItemsId);
            shoppingItems.setQuantity(this.quantity);
            return shoppingItems;
        }
}
