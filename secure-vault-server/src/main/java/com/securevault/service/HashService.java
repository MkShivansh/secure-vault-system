package com.securevault.service;

import com.securevault.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class HashService {
    
    public String generateFileHash(String fileName, String owner, Long size) {
        String input = fileName + owner + size + System.currentTimeMillis();
        return HashUtil.generateSHA256(input);
    }
    
    public String generateHashFromContent(String content) {
        return HashUtil.generateSHA256(content);
    }
    
    public boolean verifyHash(String originalContent, String storedHash) {
        String currentHash = generateHashFromContent(originalContent);
        return currentHash.equals(storedHash);
    }
}