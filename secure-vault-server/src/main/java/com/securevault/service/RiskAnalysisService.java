package com.securevault.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class RiskAnalysisService {
    
    private static final List<String> HIGH_RISK_KEYWORDS = Arrays.asList(
    "password", "bank", "salary", "confidential", 
    "medical", "patient", "ssn", "marksheet",
    "secret", "private", "financial", "tax", "credit", "debit",
    "passport", "aadhaar", "pan", "criminal", "lawsuit"
);

private static final List<String> MEDIUM_RISK_KEYWORDS = Arrays.asList(
    "report", "invoice", "result", "exam",
    "meeting", "minutes", "proposal", "contract", "agreement",
    "employee", "hr", "performance", "review"
);
    
    public String analyzeRiskLevel(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "LOW";
        }
        
        String lowerFileName = fileName.toLowerCase();
        
        // Check for high risk keywords
        for (String keyword : HIGH_RISK_KEYWORDS) {
            if (lowerFileName.contains(keyword)) {
                return "HIGH";
            }
        }
        
        // Check for medium risk keywords
        for (String keyword : MEDIUM_RISK_KEYWORDS) {
            if (lowerFileName.contains(keyword)) {
                return "MEDIUM";
            }
        }
        
        return "LOW";
    }
    
    public String getRiskDescription(String riskLevel) {
        return switch (riskLevel) {
            case "HIGH" -> "Contains sensitive information - Handle with extreme care";
            case "MEDIUM" -> "Contains business data - Standard protection required";
            case "LOW" -> "General document - Regular handling sufficient";
            default -> "Unknown risk level";
        };
    }
}