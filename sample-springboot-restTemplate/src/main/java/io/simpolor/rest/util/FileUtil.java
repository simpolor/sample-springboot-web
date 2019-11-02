package io.simpolor.rest.util;

public class FileUtil {

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

}
