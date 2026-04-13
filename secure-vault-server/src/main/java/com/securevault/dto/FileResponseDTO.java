package com.securevault.dto;

import java.time.LocalDateTime;

public class FileResponseDTO {
    private Long id;
    private String fileName;
    private String owner;
    private String fileType;
    private Long size;
    private LocalDateTime uploadDate;
    private String hashValue;
    private String riskLevel;
    
    public FileResponseDTO() {}
    
    public FileResponseDTO(Long id, String fileName, String owner, String fileType, 
                          Long size, LocalDateTime uploadDate, String hashValue, String riskLevel) {
        this.id = id;
        this.fileName = fileName;
        this.owner = owner;
        this.fileType = fileType;
        this.size = size;
        this.uploadDate = uploadDate;
        this.hashValue = hashValue;
        this.riskLevel = riskLevel;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
    
    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
    
    public String getHashValue() { return hashValue; }
    public void setHashValue(String hashValue) { this.hashValue = hashValue; }
    
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}