package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportDTO>> getReport(@RequestParam(defaultValue = "") String minDate,
													 @RequestParam(defaultValue = "") String maxDate,
													 @RequestParam(defaultValue = "") String name,
													 Pageable pageable) {
		Page<ReportDTO> reportDTO = service.getReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(reportDTO);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SummaryDTO>> getSummary(@RequestParam(defaultValue = "") String minDate,
													  @RequestParam(defaultValue = "") String maxDate,
													   Pageable pageable) {
		Page<SummaryDTO> summaryDTO = service.getSummary(minDate, maxDate, pageable);
		return ResponseEntity.ok(summaryDTO);
	}
}
