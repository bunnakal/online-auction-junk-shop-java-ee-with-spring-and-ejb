package form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFiles(MultipartFile file) {
		this.file = file;
	}
}
