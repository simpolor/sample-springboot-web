package io.simpolor.upload.controller;

import io.simpolor.upload.component.FileComponent;
import io.simpolor.upload.component.NewFileComponent;
import io.simpolor.upload.model.FileDto;
import io.simpolor.upload.repository.entity.FileUpload;
import io.simpolor.upload.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(("/file-upload"))
@RequiredArgsConstructor
public class FileUploadController {

	private final FileUploadService fileUploadService;
	private final FileComponent fileComponent;
	private final NewFileComponent newFileComponent;

	@GetMapping("/list")
	public ModelAndView list(ModelAndView mav){

		List<FileUpload> fileUploads = fileUploadService.getAll();

		mav.addObject("fileUploadList", FileDto.FileResponse.of(fileUploads));
		mav.setViewName("file_upload_list");
		return mav;
	}

	@GetMapping("/single")
	public ModelAndView singleForm(ModelAndView mav){

		mav.setViewName("file_upload_single");
		return mav;
	}

	@PostMapping("/single")
	public ModelAndView singleUpload(ModelAndView mav,
									 @Validated MultipartFile uploadFile) {

		FileUpload fileUpload = fileComponent.create(uploadFile);

		fileUploadService.create(fileUpload);

		mav.setViewName("redirect:/file-upload/list");
		return mav;
	}

	@GetMapping("/new-upload")
	public ModelAndView newUploadForm(ModelAndView mav){

		mav.setViewName("file_upload_new");
		return mav;
	}

	@PostMapping("/new-upload")
	public ModelAndView newUpload(ModelAndView mav,
								  @Validated MultipartFile uploadFile) {

		FileUpload fileUpload = newFileComponent.create(uploadFile);

		fileUploadService.create(fileUpload);

		mav.setViewName("redirect:/file-upload/list");
		return mav;
	}

	@GetMapping("/multi-upload")
	public ModelAndView multiUploadForm(ModelAndView mav){

		mav.setViewName("file_upload_multi");
		return mav;
	}

	@PostMapping("/multi-upload")
	public ModelAndView multiUpload(ModelAndView mav,
									@Validated MultipartFile[] uploadFiles) {

		List<FileUpload> fileUploads = fileComponent.create(uploadFiles);

		fileUploadService.bulkUpsert(fileUploads);

		mav.setViewName("redirect:/file-upload/list");
		return mav;
	}
}

