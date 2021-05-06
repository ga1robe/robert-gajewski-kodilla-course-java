package com.kodilla.good.patterns.food2door;

import java.util.List;

public interface Producer {
    boolean process(Service service);
    List<Product> getProductsList();
}
