package com.securevaultclient.controller;

import com.securevaultclient.MainApp;
import com.securevaultclient.service.ApiService;
import com.securevaultclient.util.AlertUtil;
import com.securevaultclient.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    public void initialize() {
        usernameField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleLogin();
            }
        });
    }
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        
        if (username.isEmpty()) {
            AlertUtil.showWarning("Login Error", "Please enter a username");
            return;
        }
        
        // Store username in MainApp and ApiService
        MainApp.getInstance().setCurrentUsername(username);
        ApiService.getInstance().setCurrentUsername(username);
        
        AlertUtil.showInfo("Login Successful", "Welcome, " + username + "!");
        SceneSwitcher.switchTo("dashboard");
    }
}