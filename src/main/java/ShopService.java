import order.Order;
import order.OrderMapRepo;
import order.OrderRepo;
import order.OrderStatus;
import product.Product;
import product.ProductNotFoundException;
import product.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productRepo.getProductById(productId)
                    .orElseThrow(() ->
                            new ProductNotFoundException(productId)
                    );

            products.add(product);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus().equals(orderStatus))
                .collect(Collectors.toList());
    }
}
