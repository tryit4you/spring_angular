package pilab.com.takeleaf.models;

import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import pilab.com.takeleaf.constants.ValidateFileCode;

public class UploadFileResult {

    public UploadFileResult() {
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        setCreatedTime(now);
    }

    public UploadFileResult(boolean status, HashMap<ValidateFileCode, String> details, String createdBy) {
        this.status = status;
        this.details = details;
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        setCreatedTime(now);
    }

    public String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean status;

    public HashMap<ValidateFileCode, String> details;

    public LocalDateTime createdTime;

    public String createdBy;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public HashMap<ValidateFileCode, String> getDetails() {
        return details;
    }

    public void setDetails(HashMap<ValidateFileCode, String> details) {
        this.details = details;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
