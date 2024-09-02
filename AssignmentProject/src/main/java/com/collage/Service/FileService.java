package com.collage.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String uploadImage(MultipartFile file, String path, String userOrAssignment) throws IOException;
	String uploadFile(MultipartFile file, String path) throws IOException;

	InputStream getResource(String path, String name) throws FileNotFoundException;

	Boolean deleteFile(String path, String fileName) throws FileNotFoundException;

	boolean updateFile(MultipartFile file, String path, String fileId, String assignmentOrProfile) throws FileNotFoundException;

}
