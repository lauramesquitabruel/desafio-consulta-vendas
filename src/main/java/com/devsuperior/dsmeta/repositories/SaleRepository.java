package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT t1.name AS sellerName, SUM(t2.amount) AS total\n" +
            "FROM TB_SELLER t1 \n" +
            "JOIN TB_SALES t2 ON t1.id = t2.seller_id\n" +
            "WHERE t2.date BETWEEN ':minDate' AND ':maxDate'\n" +
            "GROUP BY sellerName")
    List<SummaryProjection> summary(LocalDate minDate, LocalDate maxDate);
}
