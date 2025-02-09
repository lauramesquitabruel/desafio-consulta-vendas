package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SummaryDTO> getSummary(String minDateText, String maxDateText, Pageable pageable) {
		LocalDate minDate;
		LocalDate maxDate;

		if (Objects.equals(maxDateText, "")) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(maxDateText);
		}

		if (Objects.equals(minDateText, "")) {
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse((minDateText));
		}
		Page<SummaryProjection> result = repository.summary(minDate, maxDate, pageable);
		return result.map(SummaryDTO::new);
	}

	public Page<ReportDTO> getReport(String minDateText, String maxDateText, String sellerName, Pageable pageable){
		LocalDate minDate;
		LocalDate maxDate;

		if (Objects.equals(maxDateText, "")) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(maxDateText);
		}

		if (Objects.equals(minDateText, "")) {
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse((minDateText));
		}
		Page<ReportProjection> result = repository.report(minDate, maxDate, sellerName, pageable);
		return result.map(ReportDTO::new);
	}

}