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

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    public ShopService() {
        this(new ProductRepo(), new OrderMapRepo());
    }

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = getProductsByIds(productIds);

        Order newOrder = new Order(
                UUID.randomUUID().toString(),
                products,
                OrderStatus.PROCESSING
        );

        return orderRepo.addOrder(newOrder);
    }

    private List<Product> getProductsByIds(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productRepo.getProductById(productId)
                    .orElseThrow(() ->
                            new ProductNotFoundException(productId)
                    );

            products.add(product);
        }

        return products;
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus().equals(orderStatus))
                .toList();
    }

    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        return orderRepo.updateOrder(order.withOrderStatus(orderStatus));
    };

    public Order updateOrderStatusById(String orderId, OrderStatus orderStatus) {
        Order order = orderRepo.getOrderById(orderId);

        return updateOrderStatus(order, orderStatus);
    }
}
