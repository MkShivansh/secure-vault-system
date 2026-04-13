package com.securevaultclient;

import com.securevaultclient.util.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    private static MainApp instance;
    private Stage primaryStage;
    private String currentUsername;
    
    @Override
    public void start(Stage stage) {
        instance = this;
        this.primaryStage = stage;
        this.primaryStage.setTitle("Secure Digital Vault");
        this.primaryStage.setResizable(false);
        
        SceneSwitcher.switchTo("login");
    }
    
    public static MainApp getInstance() {
        return instance;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public String getCurrentUsername() {
        return currentUsername;
    }
    
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}