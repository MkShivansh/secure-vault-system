package com.securevault.dto;

public class FileRequestDTO {
    private String fileName;
    private String fileType;
    private Long size;
    private String owner;
    
    public FileRequestDTO() {}
    
    public FileRequestDTO(String fileName, String fileType, Long size, String owner) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.owner = owner;
    }
    
    // Getters and Setters
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
    
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
}