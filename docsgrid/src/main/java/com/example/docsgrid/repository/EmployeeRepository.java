package com.example.docsgrid.repository;

import com.example.docsgrid.entity.Company;
import com.example.docsgrid.entity.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends GenericRepository<Employee> {
    List<Employee> findEmployeesByNameIgnoreCaseIn(Collection<String> names);
    List<Employee> findEmployeesByJobIn(Collection<String> jobs);
    List<Employee> findEmployeesByCompany(Company company);
    List<Employee> findEmployeesByCompanyAndJob(Company company, String job);
    List<Employee> findEmployeesByCompanyAndNameIgnoreCase(Company company, String name);
    Optional<Employee> findEmployeeByCompanyAndNameAndJob(Company company, String name, String job);
}
