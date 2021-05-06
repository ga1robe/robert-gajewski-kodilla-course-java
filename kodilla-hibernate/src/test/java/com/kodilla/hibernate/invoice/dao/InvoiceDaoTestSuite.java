package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    void testInvoiceDaoSave() {
        /**
         * Given
         */
        Product firstProduct = new Product("First Product");
        Product secondProduct = new Product("Second Product");
        Item firstItem = new Item(firstProduct, BigDecimal.TEN,2);
        Item secondItem = new Item(secondProduct,BigDecimal.valueOf(30.0),1);
        Invoice invoice= new Invoice("No.001");
        firstItem.setInvoice(invoice);
        secondItem.setInvoice(invoice);
        List<Item> testedItems = new ArrayList<>();
        testedItems.add(firstItem);
        testedItems.add(secondItem);
        invoice.setItems(testedItems);
        /**
         * When
         */
        invoiceDao.save(invoice);
        int invoiceId = invoice.getId();
        int itemsSize = invoice.getItems().size();
        Invoice invoiceFromDB = invoiceDao.findById(invoiceId);
        String productFromDB = invoiceFromDB.getItems().get(0).getProduct().getName();
        /**
         * Then
         */
        assertEquals(invoiceId,invoiceFromDB.getId());
        assertEquals("First Product",productFromDB);
        assertEquals(itemsSize,invoiceFromDB.getItems().size());
        /**
         * CleanUp
         */
        invoiceDao.deleteById(invoiceId);
    }
}