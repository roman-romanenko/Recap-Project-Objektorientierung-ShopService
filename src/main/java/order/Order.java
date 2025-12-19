package order;

import lombok.With;
import product.Product;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        @With OrderStatus orderStatus
) {
}
