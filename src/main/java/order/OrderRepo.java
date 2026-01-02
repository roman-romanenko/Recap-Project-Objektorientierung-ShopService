package order;

import java.util.List;

public interface OrderRepo {

    List<Order> getOrders();

    Order getOrderById(String id);

    Order addOrder(Order newOrder);

    Order updateOrder(Order updatedOrder);

    void removeOrder(String id);
}
