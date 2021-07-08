package io.simpolor.graphql.repository.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReportCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long seq;

	@ManyToOne(fetch = FetchType.EAGER)
	private Student student;

	private Integer korean;
	private Integer english;
	private Integer math;

}
