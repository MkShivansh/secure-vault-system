package com.securevaultclient.controller;

import com.securevaultclient.model.FileMetadataModel;
import com.securevaultclient.service.ApiService;
import com.securevaultclient.util.AlertUtil;
import com.securevaultclient.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class VerifyController {
    
    @FXML
    private ComboBox<FileMetadataModel> fileComboBox;
    
    @FXML
    private TextArea resultArea;
    
    @FXML
    public void initialize() {
        loadFiles();
        
        // Configure ComboBox to display file names
        fileComboBox.setConverter(new javafx.util.StringConverter<FileMetadataModel>() {
            @Override
            public String toString(FileMetadataModel file) {
                return file != null ? file.getFileName() + " (" + file.getId() + ")" : "";
            }
            
            @Override
            public FileMetadataModel fromString(String string) {
                return null;
            }
        });
    }
    
    private void loadFiles() {
        try {
            fileComboBox.setItems(
                FXCollections.observableArrayList(ApiService.getInstance().getAllFiles())
            );
        } catch (Exception e) {
            AlertUtil.showError("Error", "Failed to load files: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleVerify() {
        FileMetadataModel selected = fileComboBox.getValue();
        
        if (selected == null) {
            AlertUtil.showWarning("Selection Error", "Please select a file to verify");
            return;
        }
        
        try {
            String result = ApiService.getInstance().verifyFile(selected.getId());
            resultArea.setText("File: " + selected.getFileName() + "\n" +
                              "Current Hash: " + selected.getHashValue() + "\n" +
                              "Verification Result: " + result);
            
            AlertUtil.showInfo("Verification Complete", "File integrity check completed");
        } catch (Exception e) {
            AlertUtil.showError("Error", "Failed to verify file: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleRefresh() {
        loadFiles();
    }
    
    @FXML
    private void handleBack() {
        SceneSwitcher.switchTo("dashboard");
    }
}