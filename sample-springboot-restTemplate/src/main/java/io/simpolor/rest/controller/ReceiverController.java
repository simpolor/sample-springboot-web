package io.simpolor.rest.controller;

import io.simpolor.rest.util.FileUtil;
import io.simpolor.rest.util.RandomUtil;
import io.simpolor.rest.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/receiver")
public class ReceiverController {

	@RequestMapping(value="/get", method=RequestMethod.GET)
	public String receiverGet(HttpServletRequest request) {
		
		log.info("receiver get");
		log.info("> authority : {}", request.getHeader("Authority"));
		log.info("> name : {}", request.getParameter("name"));

		return "success";
	}

	@RequestMapping(value="/get/{name}", method=RequestMethod.GET)
	public String receiverGet(HttpServletRequest request, @PathVariable String name) {

		log.info("receiver get[path]");
		log.info("> name : {}", name);

		return "success";
	}

	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String receiverPost(HttpServletRequest request) {

		log.info("receiver post");
		log.info("> authority : {}", request.getHeader("authority"));
		log.info("> name : {}", request.getParameter("name"));
		log.info("> age : {}", request.getParameter("age"));
		
		String result = "success";
		
		return result;
	}

	@RequestMapping(value="/receiver/rest/file", method=RequestMethod.POST)
	public String receiverRestFile(HttpServletRequest request) {
		
		System.out.println("-- ReceiverController > receiverRestFile");
		
		String result = "fail";
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		String authority = multipartRequest.getHeader("Authority");
		String name = multipartRequest.getParameter("name");
		String age = multipartRequest.getParameter("age");
		MultipartFile file = multipartRequest.getFile("upload_file");
		
		System.out.println("> authority : "+authority);
		System.out.println("> name : "+name);
		System.out.println("> age : "+age);
		System.out.println("> file : "+file);
		
		if(file != null && !file.isEmpty()) {
			String filePath = "upload";
			String uploadFilePath = RequestUtil.getRealPath(request, filePath);
			File uploadFile = new File(uploadFilePath);
			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			
			String randomStr = RandomUtil.getUpperCase(12);
			System.out.println("> randomStr : "+randomStr);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String currentDateTime = sdf.format(new Date());
			System.out.println("> currentDateTime : "+currentDateTime);
			
			String orgFileName = file.getOriginalFilename();
			String orgFileExtension = FileUtil.getFileExtension(orgFileName);
			String saveFileName = randomStr.concat("_").concat(currentDateTime);
			String saveFilePath = uploadFilePath.concat(File.separator).concat(saveFileName).concat(orgFileExtension);
			String saveViewPath = File.separator.concat(filePath).concat(File.separator).concat(saveFileName).concat(orgFileExtension);
			
			System.out.println("> orgFileName : "+orgFileName);
			System.out.println("> orgFileExtension : "+orgFileExtension);
			System.out.println("> saveFileName : "+saveFileName);
			System.out.println("> saveFilePath : "+saveFilePath);
			System.out.println("> saveViewPath : "+saveViewPath);
			
			try {
				file.transferTo(new File(saveFilePath));
				result = "success";
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
			
			
		
		return result;
	}
}
