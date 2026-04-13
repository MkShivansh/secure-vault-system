package com.securevault.dto;

public class VerifyResponseDTO {
    private Long fileId;
    private String fileName;
    private String storedHash;
    private String currentHash;
    private String status;
    private String message;
    
    public VerifyResponseDTO() {}
    
    public VerifyResponseDTO(Long fileId, String fileName, String storedHash, 
                            String currentHash, String status, String message) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.storedHash = storedHash;
        this.currentHash = currentHash;
        this.status = status;
        this.message = message;
    }
    
    // Getters and Setters
    public Long getFileId() { return fileId; }
    public void setFileId(Long fileId) { this.fileId = fileId; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getStoredHash() { return storedHash; }
    public void setStoredHash(String storedHash) { this.storedHash = storedHash; }
    
    public String getCurrentHash() { return currentHash; }
    public void setCurrentHash(String currentHash) { this.currentHash = currentHash; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}