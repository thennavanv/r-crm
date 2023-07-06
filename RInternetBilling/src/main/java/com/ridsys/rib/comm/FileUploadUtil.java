package com.ridsys.rib.comm;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			try {
				// must be 9 digit
				String perm = "rwxrwxrwx";// in octal = 777
				Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(perm);

				File filedir = new File(uploadDir);
				Files.setPosixFilePermissions(filedir.toPath(), permissions);

				filedir = new File(uploadDir + "/" + fileName);
				Files.setPosixFilePermissions(filedir.toPath(), permissions);
			} catch (IOException e) {
				// logger.warning("Can't set permission '" + perm + "' to " + dir.getPath());
				e.printStackTrace();
			}

		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}
}
