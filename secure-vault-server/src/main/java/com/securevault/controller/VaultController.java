package com.securevault.controller;

import com.securevault.dto.FileRequestDTO;
import com.securevault.dto.FileResponseDTO;
import com.securevault.dto.VerifyResponseDTO;
import com.securevault.model.AuditLog;
import com.securevault.service.AuditService;
import com.securevault.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VaultController {
    
    @Autowired
    private VaultService vaultService;
    
    @Autowired
    private AuditService auditService;
    
    @PostMapping("/files/add")
    public ResponseEntity<FileResponseDTO> addFile(@RequestBody FileRequestDTO request, 
                                                   @RequestHeader("username") String username) {
        FileResponseDTO response = vaultService.addFile(request, username);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/files/all")
    public ResponseEntity<List<FileResponseDTO>> getAllFiles() {
        List<FileResponseDTO> files = vaultService.getAllFiles();
        return ResponseEntity.ok(files);
    }
    
    @PostMapping("/files/verify/{id}")
    public ResponseEntity<VerifyResponseDTO> verifyFile(@PathVariable Long id, 
                                                        @RequestHeader("username") String username) {
        VerifyResponseDTO response = vaultService.verifyFile(id, username);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/audit/all")
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        List<AuditLog> logs = auditService.getAllAuditLogs();
        return ResponseEntity.ok(logs);
    }
}