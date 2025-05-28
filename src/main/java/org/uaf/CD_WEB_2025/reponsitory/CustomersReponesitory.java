package org.uaf.CD_WEB_2025.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.uaf.CD_WEB_2025.entity.Customers;

@Repository
public interface CustomersReponesitory extends JpaRepository<Customers,Integer > {
}
