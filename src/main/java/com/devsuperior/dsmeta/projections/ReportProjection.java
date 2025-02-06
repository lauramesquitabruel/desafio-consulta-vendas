package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface ReportProjection {
    Integer getId();
    LocalDate getDate();
    Double getAmount();
    String getSellerName();
}
