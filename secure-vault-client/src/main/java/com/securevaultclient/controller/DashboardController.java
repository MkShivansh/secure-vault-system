package com.securevaultclient.controller;

import com.securevaultclient.MainApp;
import com.securevaultclient.service.ApiService;
import com.securevaultclient.util.AlertUtil;
import com.securevaultclient.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class DashboardController {
    
    @FXML
    private Label welcomeLabel;
    
    @FXML
    public void initialize() {
        String username = MainApp.getInstance().getCurrentUsername();
        welcomeLabel.setText("Welcome, " + username + "!");
    }
    
//    @FXML
//    private void handleServlet() {
//        String result = ApiService.getInstance().callServlet();
//        AlertUtil.showInfo("Servlet Response", result);
//    }
    
    
//    web view for call servlet
    @FXML
    private void handleServlet() {

    String result = ApiService.getInstance().callServlet();

    WebView webView = new WebView();
    webView.getEngine().loadContent(result);

    Stage stage = new Stage();
    stage.setTitle("Servlet Output");

    Scene scene = new Scene(webView, 600, 400);
    stage.setScene(scene);

    stage.show();
}

    @FXML
    private void handleAddFile() {
        SceneSwitcher.switchTo("add_file");
    }
    
    @FXML
    private void handleViewFiles() {
        SceneSwitcher.switchTo("view_files");
    }
    
    @FXML
    private void handleVerifyFile() {
        SceneSwitcher.switchTo("verify");
    }
    
    @FXML
    private void handleViewAudit() {
        SceneSwitcher.switchTo("audit");
    }
    
    @FXML
    private void handleLogout() {
        if (AlertUtil.showConfirmation("Logout", "Are you sure you want to logout?")) {
            MainApp.getInstance().setCurrentUsername(null);
            ApiService.getInstance().setCurrentUsername(null);
            SceneSwitcher.switchTo("login");
        }
    }
}