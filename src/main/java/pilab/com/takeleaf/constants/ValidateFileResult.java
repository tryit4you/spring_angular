package pilab.com.takeleaf.constants;

import java.util.HashMap;

public class ValidateFileResult {

    public HashMap<ValidateFileCode, String> FileError = new HashMap<>();

    // load file error end code from server
    public ValidateFileResult load() {
        FileError.put(ValidateFileCode.FILE_BIGGER_5MB, "File is bigger 5MB");
        FileError.put(ValidateFileCode.FILE_FORMAT, "File format accept type document is .pdf,.doc,.docx");
        FileError.put(ValidateFileCode.NOT_FOUND, "cannot file any file");
        FileError.put(ValidateFileCode.SUCCESS, "save file success");
        FileError.put(ValidateFileCode.FILE_EXIST, "file is exist");
        FileError.put(ValidateFileCode.FILE_NULL, "no exist file");

        return this;
    }

    public void addError(ValidateFileCode code, String message) {
        FileError.put(code, message);
        return;
    }

    public String getDetailByCode(ValidateFileCode code) {
        return FileError.get(code);
    }
}
