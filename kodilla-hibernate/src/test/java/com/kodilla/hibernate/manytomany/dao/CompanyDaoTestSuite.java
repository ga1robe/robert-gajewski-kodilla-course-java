package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyDaoTestSuite {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void testSaveManyToMany() {
        /**
         * Given
         */
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);
        /**
         * When
         */
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();
        /**
         * Then
         */
        assertNotEquals(0, softwareMachineId);
        assertNotEquals(0, dataMaestersId);
        assertNotEquals(0, greyMatterId);
        /**
         * ClearUp
         */
        try {
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(greyMatterId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testSearchByLastname() {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");
        Employee eveSmith = new Employee("Eve", "Smith");

        //When
        employeeDao.save(johnSmith);
        employeeDao.save(stephanieClarckson);
        employeeDao.save(lindaKovalsky);
        employeeDao.save(eveSmith);

        int johnId = johnSmith.getId();
        int stephanieId = stephanieClarckson.getId();
        int lindaId = lindaKovalsky.getId();
        int eveId = eveSmith.getId();

        List<Employee> foundInDB = employeeDao.retrievByLastName("Smith");
        //Then
        try {
            assertEquals(2, foundInDB.size());
        } finally {
            employeeDao.deleteById(johnId);
            employeeDao.deleteById(stephanieId);
            employeeDao.deleteById(lindaId);
            employeeDao.deleteById(eveId);
        }
    }

    @Test
    public void testSearchCompaniesByCharset(){
        /**
         * Given
         */
        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company dataMatter = new Company("Data Matter");
        Company dataKingdom = new Company("Data Kingdom");
        /**
         * When
         */
        companyDao.save(softwareMachine);
        companyDao.save(dataMaesters);
        companyDao.save(dataMatter);
        companyDao.save(dataKingdom);

        int softwareMachineId = softwareMachine.getId();
        int dataMaestersId = dataMaesters.getId();
        int dataMatterId = dataMatter.getId();
        int dataKingdomId = dataKingdom.getId();

        List<Company> companiesByCharset = companyDao.searchCompaniesBy3LeftCharset("Sof");
        /**
         * Then
         */
        try {
            assertEquals(1,companiesByCharset.size());
        }finally {
            /** CleanUp */
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(dataMatterId);
            companyDao.deleteById(dataKingdomId);
        }
    }

}