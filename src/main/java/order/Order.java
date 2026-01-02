package order;

import lombok.Builder;
import lombok.With;
import product.Product;
import java.util.List;
@Builder
public record Order(
        String id,
        List<Product> products,
        @With OrderStatus orderStatus
) {
}
