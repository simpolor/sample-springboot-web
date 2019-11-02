package io.simpolor.http.util;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class RequestUtil {
	
	public static String getRealRootPath(HttpServletRequest request) {
		return request.getServletContext().getRealPath(File.separator);
	}
	
	public static String getRealPath(HttpServletRequest request, String filePath) {
		return request.getServletContext().getRealPath(filePath);
	}
	
	public static String getRealRootPath(MultipartHttpServletRequest request) {
		return request.getServletContext().getRealPath(File.separator);
	}
	
	public static String getRealPath(MultipartHttpServletRequest request, String filePath) {
		return request.getServletContext().getRealPath(filePath);
	}
	
}
