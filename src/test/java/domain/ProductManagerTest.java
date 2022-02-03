package domain;

import manager.ProductManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;


class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book book1 = new Book(1, "Kniga001", 200, "Vasiliy");
    Book book2 = new Book(2, "Kniga002", 123, "John");
    Book book3 = new Book(3, "Kniga003", 456, "Sam");
    Book book4 = new Book(4, "Kniga004", 300, "Petia");
    Smartphone phone1 = new Smartphone(4, "Redmi 15U", 65460, "Mi");
    Smartphone phone2 = new Smartphone(5, "Mi 186 Note", 15900, "Mi");
    Smartphone phone3 = new Smartphone(6, "Samsung 9998 Pro", 80909, "Samsung");
    Smartphone phone4 = new Smartphone(7, "Redmi 15U", 65460, "Mi");

    @BeforeEach
    void setUp() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(book4);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        manager.addProduct(phone4);
    }

    //
    @Test
    void shouldFindBookByNameIfExists() {

        String textToFind = "Kniga001";
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByNameIfNoExists() {

        String textToFind = "Something";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByAuthorIfExists() {
        String textToFind = "John";
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByAuthorIfNoExists() {
        String textToFind = "Whoo?";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByNameIfExists() {
        String textToFind = "Samsung 9998 Pro";
        Product[] expected = new Product[]{phone3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByNameIfNoExists() {
        String textToFind = "Firefly";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByManufacturerIfExists() {
        String textToFind = "Redmi 15U";
        Product[] expected = new Product[]{phone1, phone4};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByManufacturerIfNoExists() {
        String textToFind = "Asus";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenMissingProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("NON");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByOneProduct() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhoneAndBook() {
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1, phone2");
        assertArrayEquals(expected, actual);

    }

}