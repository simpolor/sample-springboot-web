package io.simpolor.graphql.model;

import io.simpolor.graphql.repository.entity.ReportCard;
import io.simpolor.graphql.repository.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Getter
public class ReportCardDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long seq;

	private StudentDto student;
	private long studentSeq;
	private String studentName;

	private Integer korean;
	private Integer english;
	private Integer math;

	public ReportCard toEntity(){

		ReportCard reportCard = new ReportCard();
		reportCard.setSeq(this.seq);
		reportCard.setKorean(this.korean);
		reportCard.setEnglish(this.english);
		reportCard.setMath(this.math);

		reportCard.setStudent(this.student.toEntity());

		return reportCard;
	}

	public static ReportCardDto of(ReportCard reportCard){

		ReportCardDto reportCardDto = new ReportCardDto();
		reportCardDto.setSeq(reportCard.getSeq());

		reportCardDto.setKorean(reportCard.getKorean());
		reportCardDto.setEnglish(reportCard.getEnglish());
		reportCardDto.setMath(reportCard.getMath());

		if(Objects.nonNull(reportCard.getStudent())){
			reportCardDto.setStudentSeq(reportCard.getStudent().getSeq());
			reportCardDto.setStudentName(reportCard.getStudent().getName());
		}

		return reportCardDto;
	}

	public static List<ReportCardDto> of(List<ReportCard> reportCards){

		return reportCards.stream()
				.map(ReportCardDto::of)
				.collect(Collectors.toList());
	}
}
