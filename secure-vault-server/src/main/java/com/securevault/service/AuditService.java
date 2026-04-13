package com.securevault.service;

import com.securevault.model.AuditLog;
import com.securevault.repository.AuditLogRepository;
import com.securevault.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditService {
    
    @Autowired
    private AuditLogRepository auditLogRepository;
    
    public void logAction(String username, String action) {
        AuditLog log = new AuditLog(username, action, DateUtil.getCurrentDateTime());
        auditLogRepository.save(log);
    }
    
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }
    
    public List<AuditLog> getAuditLogsByUser(String username) {
        return auditLogRepository.findByUsername(username);
    }
}