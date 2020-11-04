import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Tshirt;
import ru.netology.domain.exception.NotFoundException;
import ru.netology.domain.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();

    Product first = new Book(1, "novel", 500, "Lermontov", 256, 1995);
    Product second = new Book(2, "poem", 200, "Pushkin", 300, 2012);
    Product third = new Tshirt(3, "brooklyn", 5500, "white", "xs");
    Product fourth = new Tshirt(4, "aeropostale", 3300, "black", "l");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    void shouldRemoveById() {
        int idToRemove = 2;
        repository.removeById(idToRemove);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, third, fourth};
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldShowIfIdNoExists () {
        int idToRemove = 5;
        repository.removeById(idToRemove);
        assertThrows (NotFoundException.class, ()-> repository.removeById(5));

    }
}
