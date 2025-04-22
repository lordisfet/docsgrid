package com.example.docsgrid.repository;

import com.example.docsgrid.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends GenericRepository<Company> {
    Optional<Company> findCompanyByBIC(String bic);
    Optional<Company> findCompanyByNameIgnoreCase(String name);
}
