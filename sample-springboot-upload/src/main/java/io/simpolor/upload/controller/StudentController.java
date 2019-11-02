package io.simpolor.upload.controller;

import io.simpolor.upload.component.FileUploader;
import io.simpolor.upload.domain.Result;
import io.simpolor.upload.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/student")
@RestController
public class StudentController {

	@Autowired
	private FileUploader fileUploader;

	@Value("${application.file.path}")
	String filePath;

	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public ModelAndView studentProfileUploadForm(ModelAndView mav){

		mav.setViewName("upload");

		return mav;
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public Result studentProfileUpload(
			// MultipartHttpServletRequest request
			@Valid Student student
	) {
		// System.out.println("getOriginalFilename : "+request.getFile("profile").getOriginalFilename());
		// System.out.println("getParameter : "+request.getParameter("name"));

		// System.out.println("getOriginalFilename : "+student.getProfile().getOriginalFilename());
		// System.out.println("getParameter : "+student.getName());

		/*System.out.println("student : "+student.toString());
		MultipartFile multipartFile = student.getProfile();
		if(multipartFile != null){
			try {
				File savedFilePath = new File(filePath);
				if(savedFilePath.exists()){
					savedFilePath.mkdirs();
				}
				File image = new File(filePath + File.separator + student.getProfile().getOriginalFilename());
				multipartFile.transferTo(image);
			}catch (IOException ioe){
				ioe.printStackTrace();
			}
		}*/

		FileUploader.Files files = fileUploader.createFile(student.getProfile(), "student");
		if(files != null) {
			return Result.builder()
					.name(student.getName())
					.orgFileName(files.getOrg_file_name())
					.savedFileName(files.getSaved_file_name())
					.fileSize(files.getFile_size())
					.fileExt(files.getFile_ext())
					.build();
		}

		return Result.builder().name(student.getName()).build();
	}


}
