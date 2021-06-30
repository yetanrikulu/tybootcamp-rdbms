package com.tybootcamp.ecomm;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.tybootcamp.ecomm.controllers.CustomerController;
import com.tybootcamp.ecomm.entities.*;
import com.tybootcamp.ecomm.enums.Gender;
import com.tybootcamp.ecomm.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

@EnableJpaRepositories(basePackages = "com.tybootcamp.ecomm.repositories")
@SpringBootApplication
public class EcommApplication implements CommandLineRunner {
    private final CategoryRepository _categoryRepository;
    private final ProductJpaRepository _productJpaRepository;
    private final SellerRepository _sellerRepository;
    private final CustomerRepository _customerRepository;
    private final BasketRepository _basketRepository;

    public EcommApplication(CategoryRepository _categoryRepository, ProductJpaRepository _productJpaRepository, SellerRepository _sellerRepository, CustomerRepository customerRepository, BasketRepository basketRepository) {
        this._categoryRepository = _categoryRepository;
        this._productJpaRepository = _productJpaRepository;
        this._sellerRepository = _sellerRepository;
        this._customerRepository = customerRepository;
        this._basketRepository = basketRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        _sellerRepository.getById(12l);
        //--------------Create two sellers-----------------------------------------
        Seller judy = new Seller("Judy's account id = 879");
        Profile judyProfile = new Profile("Judy", "Adams", Gender.Female);
        judyProfile.setBirthday(new SimpleDateFormat("MM/dd/yyyy").parse(("4/12/2010")));
        judyProfile.setEmailAddress("hi@demo.com");
        judy.setProfile(judyProfile);
        judy = _sellerRepository.save(judy);


        Seller michael = new Seller("Micheal's account id = 023");
        Profile michaelProfile = new Profile("Michael", "Martin", Gender.Male);
        michaelProfile.setEmailAddress("hi@demo.com");
        michael.setProfile(michaelProfile);
        michael = _sellerRepository.save(michael);


        //--------------Create 4 different categories and save them--------------------
        Category artCategory = new Category("Art");
        Category wallDecorCategory = new Category("Wall Decor");
        Category babyCategory = new Category("Baby");
        Category toysCategory = new Category("Toys");
        artCategory = _categoryRepository.save(artCategory);
        wallDecorCategory = _categoryRepository.save(wallDecorCategory);
        _categoryRepository.save(babyCategory);
        _categoryRepository.save(toysCategory);


        //--------------Create a product in wall decor and art categories--------------
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://c.pxhere.com/photos/b1/ab/fantastic_purple_trees_beautiful_jacaranda_trees_pretoria_johannesburg_south_africa-1049314.jpg!d");
        imageUrls.add("https://c.pxhere.com/photos/90/da/jacaranda_trees_flowering_purple_stand_blossom_spring_plant-922332.jpg!d");

        Seller finalMichael = michael;
        Category finalArtCategory = artCategory;
        Category finalWallDecorCategory = wallDecorCategory;



        IntStream.range(1, 50).parallel().forEach(
                i -> {
                    Book book = new Faker().book();
                    String author = book.author();
                    Product pictureProduct = new Product(author, author,
                            book.title(),
                            42.34f, imageUrls, finalMichael, new HashSet<>(Arrays.asList(finalArtCategory, finalWallDecorCategory)));
                    _productJpaRepository.save(pictureProduct);

                }
        );

        // ----- Create 1 customer (and also 1 product)  ---- //
        Book book = new Faker().book();
        String author = book.author();
        Product pictureProduct = new Product(author, author,
                book.title(),
                42.34f, imageUrls, finalMichael, new HashSet<>(Arrays.asList(finalArtCategory, finalWallDecorCategory)));
        _productJpaRepository.save(pictureProduct);

        Map<Product,Integer> icerik = new HashMap<>();
        icerik.put(pictureProduct,20);


        Profile customerProfile = new Profile("Michael", "Jordan", Gender.Male);
        Basket basket = new Basket(icerik);
        _basketRepository.save(basket);
        Customer michaelJordan = new Customer(customerProfile,"Michael Jordan", basket);
        _customerRepository.save(michaelJordan);






    }
}