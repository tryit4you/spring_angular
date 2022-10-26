package pilab.com.takeleaf.services;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import pilab.com.takeleaf.constants.ValidateFileCode;

public interface FileValidationService {
    
    FileValidationService init(MultipartFile file);

    boolean fileNull(String filePath);

    boolean filesize();

    boolean fileformat();

    boolean fileExist(String fileName);

    FileValidationService validate();

    HashMap<ValidateFileCode, String> validateDetail();

    boolean validateResult();

}
