package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
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

	public List<SummaryDTO> getSummary(String minDateText, String maxDateText){
		LocalDate minDate;
		LocalDate maxDate;

		if(Objects.equals(maxDateText, "")){
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(maxDateText);
		}

		if(Objects.equals(minDateText, "")){
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse((minDateText));
		}
		List<SummaryProjection> result = repository.summary(minDate, maxDate);
		return result.stream().map(SummaryDTO::new).toList();
	}
}
