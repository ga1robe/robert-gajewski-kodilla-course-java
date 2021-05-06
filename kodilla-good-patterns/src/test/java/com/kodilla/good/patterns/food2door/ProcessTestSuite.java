package com.kodilla.good.patterns.food2door;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessTestSuite {
    @Test
    public void testExtraFoodShop() {
        /**
         * given
         */
        Purchaser buyer11 = new Purchaser("Buyer 11", "street 11111", "02-123", "Nowe kąty");
        Purchaser buyer12 = new Purchaser("Buyer 12", "street 121212", "32-123", "Stary Zjazd");
        Product product11 = new Product("Extra Food Pack 01", 40);
        Product product12 = new Product("Extra Food Pack 02", 1100);
        Product product13 = new Product("Extra Food 03", 5243);
        Product product14 = new Product("product Pack 04", 1234);
        Product product15 = new Product("product Pack 05", 111);
        Product product16 = new Product("product Pack 06", 6001);

        ExtraFoodShop extraFoodShop = new ExtraFoodShop();
        extraFoodShop.addProduct(product11);
        extraFoodShop.addProduct(product12);
        extraFoodShop.addProduct(product13);
        extraFoodShop.addProduct(product14);

        DistributionService distributionService = new DistributionService();

        distributionService.addNewOrder(buyer11, product11);
        distributionService.addNewOrder(buyer11, product12);
        distributionService.addNewOrder(buyer11, product15);
        distributionService.addNewOrder(buyer11, product16);
        distributionService.addNewOrder(buyer12, product13);
        distributionService.addNewOrder(buyer12, product16);
        /**
         * when
         */
        extraFoodShop.process(distributionService);
        /**
         * then
         */
        assertEquals(3, distributionService.getActiveOrders().size());
    }

    @Test
    public void testGlutenFreeShop() {
        /**
         * given
         */
        Purchaser buyer22 = new Purchaser("Buyer 22", "street 22222", "32-123", "Stary Zjazd");
        Purchaser buyer21 = new Purchaser("Buyer 21", "street 212121", "00-676", "Miasteczkowo");
        Product product21 = new Product("gluten Free Packet 01", 3);
        Product product22 = new Product("gluten Free Packet 02", 1);
        Product product23 = new Product("gluten Free Packet 03", 2);
        Product product24 = new Product("gluten Free Packet 04", 2);
        Product product25 = new Product("gluten Free Packet 05", 5);
        Product product26 = new Product("gluten Free Packet 06", 6);

        GlutenFreeShop glutenFreeShop = new GlutenFreeShop();
        glutenFreeShop.addProduct(product26);
        glutenFreeShop.addProduct(product23);

        DistributionService distributionService = new DistributionService();

        distributionService.addNewOrder(buyer21, product21);
        distributionService.addNewOrder(buyer21, product22);
        distributionService.addNewOrder(buyer21, product25);
        distributionService.addNewOrder(buyer21, product26);
        distributionService.addNewOrder(buyer22, product24);
        distributionService.addNewOrder(buyer22, product26);
        /**
         * when
         */
        glutenFreeShop.process(distributionService);
        /**
         * then
         */
        assertEquals(4, distributionService.getActiveOrders().size());
    }

    @Test
    public void testHealthyShop() {
        /**
         * given
         */
        Purchaser buyer31 = new Purchaser("Buyer 31", "street 313131", "02-123", "Nowe kąty");
        Purchaser buyer32 = new Purchaser("Buyer 32", "street 323232", "32-123", "Stary Zjazd");
        Product product31 = new Product("Healthy Packet 01", 4);
        Product product32 = new Product("Healthy Packet 02", 8);
        Product product33 = new Product("Healthy Packet 03", 8);
        Product product44 = new Product("Healthy Packet 04", 7);
        Product product55 = new Product("Healthy Packet 05", 9);
        Product product66 = new Product("Healthy Packet 06", 1);
        Product product77 = new Product("Healthy Packet 07", 2);

        HealthyShop healthyShop = new HealthyShop();
        healthyShop.addProduct(product44);
        healthyShop.addProduct(product55);

        DistributionService distributionService = new DistributionService();

        distributionService.addNewOrder(buyer31, product31);
        distributionService.addNewOrder(buyer31, product32);
        distributionService.addNewOrder(buyer31, product55);
        distributionService.addNewOrder(buyer31, product77);
        distributionService.addNewOrder(buyer32, product33);
        distributionService.addNewOrder(buyer32, product77);
        distributionService.addNewOrder(buyer32, product66);
        /**
         * when
         */
        healthyShop.process(distributionService);
        /**
         * then
         * */
        assertEquals(6, distributionService.getActiveOrders().size());
    }
}
