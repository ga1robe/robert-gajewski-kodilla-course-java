package com.kodilla.patterns2.adapter.company;

import com.kodilla.patterns2.adapter.company.oldhrsystem.Workers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalaryAdapterTestSuite {

    @Test
    public void testTotalSalary() {
        /*
        * Given
        * */
        Workers workers = new Workers();                      // tworzenie obiektu przechowujący dane ze starego systemu kadrowo-płacowego
        SalaryAdapter salaryAdapter = new SalaryAdapter();    // tworzenie obiekt z adaptera
        /*
        * When
        * */
        double totalSalary = salaryAdapter.totalSalary(workers.getWorkers(), workers.getSalaries());   /* Wywołanie z adaptera metody,
                                                                                                        * która wewnętrznie transformuje dane do nowego formatu
                                                                                                        * i wywołuje kalkulator wynagrodzeń z nowego system */
        /*
        * Then
        * */
        System.out.println(totalSalary);
        assertEquals(totalSalary, 27750, 0);                  // weryfikowanie otrzymanych wartości wynikowej na zgodność z wartością oczekiwaną
    }
}
