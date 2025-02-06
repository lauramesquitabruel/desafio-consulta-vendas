package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.ReportProjection;

import java.time.LocalDate;

public class ReportDTO {
    private Integer id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public ReportDTO(Integer id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public ReportDTO(ReportProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        sellerName = projection.getSellerName();
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
