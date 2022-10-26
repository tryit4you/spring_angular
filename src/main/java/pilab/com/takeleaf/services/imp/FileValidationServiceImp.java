package pilab.com.takeleaf.services.imp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import antlr.collections.List;
import pilab.com.takeleaf.constants.ValidateFileCode;
import pilab.com.takeleaf.constants.ValidateFileResult;
import pilab.com.takeleaf.services.FileValidationService;
import pilab.com.takeleaf.Utility.Constants;
@Service
public class FileValidationServiceImp implements FileValidationService {

    private MultipartFile _file;

    private boolean _validateResult ;

    private HashMap<ValidateFileCode, String> _uploadFileResult ;
    @Override
    public boolean filesize() {
        long fileByte = _file.getSize();
        long fileMb = fileByte / (1024 * 1024);
        if (fileMb <= 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean fileformat() {
        String fileName = _file.getOriginalFilename();

        if (fileName != null &&
                (fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith(".docx"))) {
            return true;
        }
        return false;
    }

    @Override
    public FileValidationService validate() {
        _uploadFileResult=new HashMap<>();
        ValidateFileResult validate = new ValidateFileResult().load();
        _validateResult=false;
        String filename=_file.getOriginalFilename();
        boolean fileSizeValidate = this.filesize();
        boolean fileFormatValidate = this.fileformat();
        boolean fileExistValidate =false;
        if(filename==null){
            String nullErorr = validate.getDetailByCode(ValidateFileCode.FILE_NULL);
            _uploadFileResult.put(ValidateFileCode.FILE_NULL, nullErorr);
        }else{
            fileExistValidate= this.fileExist(filename);
        }
        if (filename!=null && ( fileSizeValidate && fileFormatValidate&&fileExistValidate==false)) {
            _validateResult = true;
            String success = validate.getDetailByCode(ValidateFileCode.SUCCESS);
            _uploadFileResult.put(ValidateFileCode.SUCCESS, success);
        }
        if (fileExistValidate) {
            String existErorr = validate.getDetailByCode(ValidateFileCode.FILE_EXIST);
            _uploadFileResult.put(ValidateFileCode.FILE_EXIST, existErorr);
        }
        if (fileSizeValidate == false) {
            String sizeErorr = validate.getDetailByCode(ValidateFileCode.FILE_BIGGER_5MB);
            _uploadFileResult.put(ValidateFileCode.FILE_BIGGER_5MB, sizeErorr);
        }
        if (fileFormatValidate == false) {
            String formatErorr = validate.getDetailByCode(ValidateFileCode.FILE_FORMAT);
            _uploadFileResult.put(ValidateFileCode.FILE_FORMAT, formatErorr);

        }
        return this;
    }

    @Override
    public HashMap<ValidateFileCode, String> validateDetail() {
        return _uploadFileResult;
    }

    @Override
    public FileValidationService init(MultipartFile file) {
        this._file = file;
        return this;
    }

    @Override
    public boolean validateResult() {

        return _validateResult;
    }
    @Override
    public boolean fileExist(String fileName) {
        Path path=Paths.get(Constants.POST_FOLDER+fileName);
        if(Files.exists(path)){
            return true;
        }
        return false;
    }

    @Override
    public boolean fileNull(String filePath) {
        if(filePath==null){
            return false;
        }
        if(filePath.isEmpty()){
            return false;   
        }
        return true;
    }

}
