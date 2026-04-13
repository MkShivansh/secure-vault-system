package com.securevault.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_metadata")
public class FileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "file_name", nullable = false)
    private String fileName;
    
    @Column(name = "owner", nullable = false)
    private String owner;
    
    @Column(name = "file_type", nullable = false)
    private String fileType;
    
    @Column(name = "size", nullable = false)
    private Long size;
    
    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;
    
    @Column(name = "hash_value", nullable = false, length = 64)
    private String hashValue;
    
    @Column(name = "risk_level", nullable = false)
    private String riskLevel;
    
    public FileMetadata() {}
    
    public FileMetadata(String fileName, String owner, String fileType, Long size, 
                       LocalDateTime uploadDate, String hashValue, String riskLevel) {
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