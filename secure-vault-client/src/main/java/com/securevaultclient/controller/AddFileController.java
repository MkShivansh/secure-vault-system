package com.securevaultclient.controller;

import com.securevaultclient.MainApp;
import com.securevaultclient.service.ApiService;
import com.securevaultclient.util.AlertUtil;
import com.securevaultclient.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddFileController {
    
    @FXML
    private TextField fileNameField;
    
    @FXML
    private TextField fileTypeField;
    
    @FXML
    private TextField fileSizeField;
    
    @FXML
    private TextField ownerField;
    
    @FXML
    public void initialize() {
        ownerField.setText(MainApp.getInstance().getCurrentUsername());
    }
    
    @FXML
    private void handleSubmit() {
        try {
            String fileName = fileNameField.getText().trim();
            String fileType = fileTypeField.getText().trim();
            String sizeText = fileSizeField.getText().trim();
            String owner = ownerField.getText().trim();
            
            // Validate inputs
            if (fileName.isEmpty() || fileType.isEmpty() || sizeText.isEmpty() || owner.isEmpty()) {
                AlertUtil.showWarning("Validation Error", "All fields are required");
                return;
            }
            
            Long fileSize;
            try {
                fileSize = Long.parseLong(sizeText);
                if (fileSize <= 0) {
                    AlertUtil.showWarning("Validation Error", "File size must be positive");
                    return;
                }
            } catch (NumberFormatException e) {
                AlertUtil.showWarning("Validation Error", "File size must be a valid number");
                return;
            }
            
            // Call API
            ApiService.getInstance().addFile(fileName, fileType, fileSize, owner);
            
            AlertUtil.showInfo("Success", "File metadata added successfully!");
            handleBack();
            
        } catch (Exception e) {
            AlertUtil.showError("Error", "Failed to add file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBack() {
        SceneSwitcher.switchTo("dashboard");
    }
}