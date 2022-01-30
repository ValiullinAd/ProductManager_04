package domain;

import org.junit.jupiter.api.Test;
import repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import domain.Book;
import domain.Product;
import domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;


class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book book1 = new Book(1, "Kniga001", 200, "Vasiliy");
    Book book2 = new Book(2, "Kniga002", 123, "John");
    Book book3 = new Book(3, "Kniga003", 456, "Sam");
    //Book book4 = new Book(1, "Kniga001", 200, "Vasily");
    Book book4 = new Book(1, "Kniga001", 200, "Vasiliy");
    Smartphone phone1 = new Smartphone(4, "Redmi 15U", 65460, "Mi");
    Smartphone phone2 = new Smartphone(5, "Mi 186 Note", 15900, "Mi");
    Smartphone phone3 = new Smartphone(6, "Samsung 9998 Pro", 80909, "Samsung");
    Smartphone phone4 = new Smartphone(4, "Redmi 15U", 65460, "Mi");
//    @BeforeEach
//    void setUp() {
//        manager = new ProductManager(repository);
//        manager.addProduct(book1);
//        manager.addProduct(book2);
//        manager.addProduct(book3);
//        manager.addProduct(book4);
//        manager.addProduct(phone1);
//        manager.addProduct(phone2);
//        manager.addProduct(phone3);
//        manager.addProduct(phone4);
//    }
//    @Test
//    void firstTry() {
//        manager = new ProductManager(repository);
//        manager.addProduct(book1);
//        manager.addProduct(book4);
//        assertEquals(book1, book4);
//    }
    @Test
    void removeById(){
        manager = new ProductManager(repository);
        //int idToRemove = 1;
        manager.addProduct(book4);
        manager.addProduct(phone4);
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{phone4};

//    assertEquals(expected, book1);
        assertArrayEquals(expected, actual);



    }

    @Test
    void shouldFindBookByNameIfExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "Kniga001";
        Product[] expected = new Product[]{book1};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByNameIfNoExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "Something";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindBookByAuthorIfExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "John";
        Product[] expected = new Product[]{book2};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindBookByAuthorIfNoExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "Whoo?";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByNameIfExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "Samsung 9998 Pro";
        Product[] expected = new Product[]{phone3};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByNameIfNoExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "Firefly";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSmartphoneByManufacturerIfExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        manager.addProduct(phone4);
        String textToFind = "Redmi 15U";
        Product[] expected = new Product[]{phone1,phone4};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindSmartphoneByManufacturerIfNoExists() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        String textToFind = "Asus";
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy(textToFind);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenMissingProduct() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("NON");
        assertArrayEquals(expected, actual);
    }

//    @Test
//    public void shouldSearchByOneProduct() {
//        manager = new ProductManager(repository);
//        manager.addProduct(book1);
//        manager.addProduct(book2);
//        manager.addProduct(book3);
//        manager.addProduct(phone1);
//        manager.addProduct(phone2);
//        manager.addProduct(phone3);
//        Product[] expected = new Product[]{};
//        Product[] actual = manager.searchBy("book1");
//        assertArrayEquals(expected, actual);
//    }

    @Test
    public void shouldSearchByPhoneAndBook() {
        manager = new ProductManager(repository);
        manager.addProduct(book1);
        manager.addProduct(book2);
        manager.addProduct(book3);
        manager.addProduct(phone1);
        manager.addProduct(phone2);
        manager.addProduct(phone3);
        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("book1, phone2");
        assertArrayEquals(expected, actual);

    }

}