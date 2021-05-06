package com.kodilla.good.patterns.food2door;

import java.util.ArrayList;
import java.util.List;

public class ExtraFoodShop implements Producer {
    private static final String NAME = "Extra Food Shop";
    private List<Product> productsList = new ArrayList<>();

    public void addProduct(Product product) {
        productsList.add(product);
    }

    @Override
    public boolean process(Service service) {
        SendOrder sendOrder = new SendOrder();
        return sendOrder.isResult(NAME, service, productsList);
    }

    @Override
    public List<Product> getProductsList() {
        return productsList;
    }
}
