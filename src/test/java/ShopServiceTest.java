import order.Order;
import order.OrderStatus;
import org.junit.jupiter.api.Test;
import product.Product;
import product.ProductNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrder_throwsException_whenProductNotFound() {
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        assertThrows(
                ProductNotFoundException.class,
                () -> shopService.addOrder(productsIds)
        );
    }

    @Test
    void getOrdersByStatus_returnsOnlyProcessingOrders() {
        // GIVEN
        ShopService shopService = new ShopService();
        List<String> productIds = List.of("1");

        // WHEN
        shopService.addOrder(productIds);
        List<Order> processingOrders =
                shopService.getOrdersByStatus(OrderStatus.PROCESSING);

        // THEN
        assertEquals(1, processingOrders.size());
        assertEquals(OrderStatus.PROCESSING, processingOrders.get(0).orderStatus());
    }

    @Test
    void getOrdersByStatus_returnsEmptyArrayListOrders_whenThereAreNoOrdersWithCorrectStatus() {
        // GIVEN
        ShopService shopService = new ShopService();
        List<String> productIds = List.of("1");

        // WHEN
        shopService.addOrder(productIds);
        List<Order> completedOrders = shopService.getOrdersByStatus(OrderStatus.COMPLETED);

        // THEN
        assertEquals(0, completedOrders.size());
    }
}
