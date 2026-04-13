package com.securevault.service;

import com.securevault.dto.FileRequestDTO;
import com.securevault.dto.FileResponseDTO;
import com.securevault.dto.VerifyResponseDTO;
import com.securevault.model.FileMetadata;
import com.securevault.repository.FileMetadataRepository;
import com.securevault.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaultService {
    
    @Autowired
    private FileMetadataRepository fileMetadataRepository;
    
    @Autowired
    private HashService hashService;
    
    @Autowired
    private RiskAnalysisService riskAnalysisService;
    
    @Autowired
    private AuditService auditService;
    
    @Transactional
    public FileResponseDTO addFile(FileRequestDTO request, String username) {
        // Generate hash
        String hash = hashService.generateFileHash(
            request.getFileName(), 
            request.getOwner(), 
            request.getSize()
        );
        
        // Analyze risk
        String riskLevel = riskAnalysisService.analyzeRiskLevel(request.getFileName());
        
        // Create file metadata
        FileMetadata metadata = new FileMetadata(
            request.getFileName(),
            request.getOwner(),
            request.getFileType(),
            request.getSize(),
            DateUtil.getCurrentDateTime(),
            hash,
            riskLevel
        );
        
        FileMetadata saved = fileMetadataRepository.save(metadata);
        
        // Log action
        auditService.logAction(username, "Added file: " + request.getFileName() + 
            " (Risk: " + riskLevel + ")");
        
        return convertToDTO(saved);
    }
    
    public List<FileResponseDTO> getAllFiles() {
        return fileMetadataRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public VerifyResponseDTO verifyFile(Long fileId, String username) {
        FileMetadata file = fileMetadataRepository.findById(fileId)
            .orElseThrow(() -> new RuntimeException("File not found with id: " + fileId));
        
        // Recalculate hash using the same method
        String currentHash = hashService.generateFileHash(
            file.getFileName(),
            file.getOwner(),
            file.getSize()
        );
        
        String status = currentHash.equals(file.getHashValue()) ? "VERIFIED" : "TAMPERED";
        String message = status.equals("VERIFIED") ? 
            "File integrity is intact" : 
            "File has been modified!";
        
        // Log action
        auditService.logAction(username, "Verified file: " + file.getFileName() + 
            " - Status: " + status);
        
        return new VerifyResponseDTO(
            fileId,
            file.getFileName(),
            file.getHashValue(),
            currentHash,
            status,
            message
        );
    }
    
    private FileResponseDTO convertToDTO(FileMetadata metadata) {
        return new FileResponseDTO(
            metadata.getId(),
            metadata.getFileName(),
            metadata.getOwner(),
            metadata.getFileType(),
            metadata.getSize(),
            metadata.getUploadDate(),
            metadata.getHashValue(),
            metadata.getRiskLevel()
        );
    }
}