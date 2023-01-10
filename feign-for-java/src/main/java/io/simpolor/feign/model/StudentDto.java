package io.simpolor.feign.model;

import io.simpolor.feign.remote.model.RemoteDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class StudentDto {

	@Setter
	@Getter
	public static class StudentRequest {

		private String name;
		private Integer grade;
		private Integer age;
		private List<String> hobbies;

		public RemoteDto.RemoteRequest toRequest(){

			RemoteDto.RemoteRequest remoteRequest = new RemoteDto.RemoteRequest();
			remoteRequest.setName(this.name);
			remoteRequest.setGrade(this.grade);
			remoteRequest.setAge(this.age);
			remoteRequest.setHobbies(this.hobbies);

			return remoteRequest;
		}
	}

	@Setter
	@Getter
	public static class StudentResponse {

		private Long id;
		private String name;
		private Integer grade;
		private Integer age;
		private List<String> hobbies;

		public static StudentResponse of(RemoteDto.RemoteResponse remoteResponse){

			StudentResponse response = new StudentResponse();
			response.setId(remoteResponse.getId());
			response.setName(remoteResponse.getName());
			response.setGrade(remoteResponse.getGrade());
			response.setAge(remoteResponse.getAge());
			response.setHobbies(remoteResponse.getHobbies());

			return response;
		}

		public static List<StudentResponse> of(List<RemoteDto.RemoteResponse> responses){

			return responses.stream()
					.map(StudentResponse::of)
					.collect(Collectors.toList());
		}

	}
}
