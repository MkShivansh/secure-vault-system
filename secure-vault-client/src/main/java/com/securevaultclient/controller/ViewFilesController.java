package com.securevaultclient.controller;

import com.securevaultclient.model.FileMetadataModel;
import com.securevaultclient.service.ApiService;
import com.securevaultclient.util.AlertUtil;
import com.securevaultclient.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewFilesController {

    @FXML
    private TableView<FileMetadataModel> filesTable;

    @FXML
    private TableColumn<FileMetadataModel, String> fileNameColumn;

    @FXML
    private TableColumn<FileMetadataModel, String> ownerColumn;

    @FXML
    private TableColumn<FileMetadataModel, String> fileTypeColumn;

    @FXML
    private TableColumn<FileMetadataModel, String> riskLevelColumn;

    private final ObservableList<FileMetadataModel> filesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        // connect table columns with model properties
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        fileTypeColumn.setCellValueFactory(new PropertyValueFactory<>("fileType"));
        riskLevelColumn.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));

        loadFiles();
    }

    private void loadFiles() {
        try {
            filesList.clear();
            filesList.addAll(ApiService.getInstance().getAllFiles());
            filesTable.setItems(filesList);
        } catch (Exception e) {
            AlertUtil.showError("Error", "Failed to load files: " + e.getMessage());
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