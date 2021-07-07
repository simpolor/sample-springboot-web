package io.simpolor.graphql.controller;

import io.simpolor.graphql.model.ReportCardDto;
import io.simpolor.graphql.repository.entity.ReportCard;
import io.simpolor.graphql.service.ReportCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/report-cards")
@RequiredArgsConstructor
public class ReportCardController {

	private final ReportCardService reportCardService;

	@GetMapping(value="/total-count")
	public Long totalCount() {

		return reportCardService.getTotalCount();
	}

	@GetMapping
	public List<ReportCardDto> list() {

		return ReportCardDto.of(reportCardService.getAll());
	}

	@GetMapping(value="/{seq}")
	public ReportCardDto detail(@PathVariable Long seq) {

		ReportCard reportCard = reportCardService.get(seq);

		return ReportCardDto.of(reportCard);
	}

	@PostMapping
	public void register(@RequestBody ReportCardDto reportCardDto) {

		reportCardService.create(reportCardDto.toEntity());
	}

	@PutMapping(value="/{seq}")
	public void modify(@PathVariable Long seq,
					   @RequestBody ReportCardDto reportCardDto) {

		reportCardDto.setSeq(seq);
		reportCardService.update(reportCardDto.toEntity());
	}

	@DeleteMapping(value="/{seq}")
	public void delete(@PathVariable Long seq) {

		reportCardService.delete(seq);
	}

}
