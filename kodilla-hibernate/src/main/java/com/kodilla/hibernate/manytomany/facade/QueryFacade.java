package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryFacade {
    private final EmployeeDao employeeDao;
    private final CompanyDao companyDao;

    public QueryFacade(EmployeeDao employeeDao, CompanyDao companyDao) {
        this.employeeDao = employeeDao;
        this.companyDao = companyDao;
    }

    public List<Employee> searchEmployeesWithNameLike(String arg) {
        return employeeDao.searchEmployeeWithNameLike(arg);
    }

    public List<Company> searchCompaniesByLeftCharset(String arg) {
        return companyDao.searchCompaniesByLeftCharset(arg);
    }
}