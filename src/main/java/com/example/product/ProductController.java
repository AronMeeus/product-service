package com.example.product;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@CrossOrigin(origins = "http://shopping-cart-git-ucllteam07.ocp-ucll-40cb0df2b03969eabb3fac6e80373775-0000.eu-de.containers.appdomain.cloud", allowCredentials = "true")
@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return Arrays.asList(
                new Product(
                        "1",
                        "Cooking 1",
                        "beginner class",
                       "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/foodnavigator-usa.com/news/markets/survey-cooking-at-home-will-become-the-new-normal-post-pandemic/10914660-1-eng-GB/Survey-Cooking-at-home-will-become-the-new-normal-post-pandemic_wrbm_large.jpg",
                        1,
                        20
                ),
                new Product(
                        "2",
                        "Cooking 2",
                        "intermediate class",
                        "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/foodnavigator-usa.com/news/markets/survey-cooking-at-home-will-become-the-new-normal-post-pandemic/10914660-1-eng-GB/Survey-Cooking-at-home-will-become-the-new-normal-post-pandemic_wrbm_large.jpg",
                        1,
                        50
                ),
                new Product(
                        "3",
                        "Cooking 3",
                        "advanced class",
                        "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/foodnavigator-usa.com/news/markets/survey-cooking-at-home-will-become-the-new-normal-post-pandemic/10914660-1-eng-GB/Survey-Cooking-at-home-will-become-the-new-normal-post-pandemic_wrbm_large.jpg",
                        1,
                        100
                ),new Product(
                        "4",
                        "Construction 1",
                        "beginner class",
                        "https://turanizmir.com/en/wp-content/uploads/2021/03/https___specials-images.forbesimg.com_imageserve_1171642478_0x0.jpg",
                        1,
                        70
                ),new Product(
                        "5",
                        "Electricity 1",
                        "beginner class",
                        "https://alis.alberta.ca/media/697347/electrician-istock-487018428.jpg",
                        1,
                        60
                ),new Product(
                        "6",
                        "Nursing 1",
                        "beginner class",
                        "https://www.purdueglobal.edu/blog/nursing/purgshouldyoubecomeanurse-image.jpg",
                        1,
                        80
                )
        );
    }

    @GetMapping("/check")
    public boolean greeting(@RequestParam(value = "name", defaultValue = "World") String name,
                            @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In GET Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
        if (partnerRole) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public String addProduct(@RequestBody Product product, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In POST Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
            return "Product added";
        } else {
            return "Not Authorized to add product";
        }
    }

}