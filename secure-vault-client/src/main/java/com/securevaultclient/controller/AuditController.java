package com.securevaultclient.controller;

import com.securevaultclient.model.AuditLogModel;
import com.securevaultclient.service.ApiService;
import com.securevaultclient.util.AlertUtil;
import com.securevaultclient.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuditController {
    
    @FXML
    private TableView<AuditLogModel> auditTable;
    
    @FXML
    private TableColumn<AuditLogModel, Long> idColumn;
    
    @FXML
    private TableColumn<AuditLogModel, String> usernameColumn;
    
    @FXML
    private TableColumn<AuditLogModel, String> actionColumn;
    
    @FXML
    private TableColumn<AuditLogModel, String> timestampColumn;
    
    private ObservableList<AuditLogModel> auditList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        // Setup columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        
        loadAuditLogs();
    }
    
    private void loadAuditLogs() {
        try {
            auditList.clear();
            auditList.addAll(ApiService.getInstance().getAllAuditLogs());
            auditTable.setItems(auditList);
        } catch (Exception e) {
            AlertUtil.showError("Error", "Failed to load audit logs: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleRefresh() {
        loadAuditLogs();
    }
    
    @FXML
    private void handleBack() {
        SceneSwitcher.switchTo("dashboard");
    }
}