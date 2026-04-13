package com.securevaultclient.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.securevaultclient.model.AuditLogModel;
import com.securevaultclient.model.FileMetadataModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8081/api";
    private static ApiService instance;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private String currentUsername;

    private ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public FileMetadataModel addFile(String fileName, String fileType, Long size, String owner)
            throws Exception {
        String json = objectMapper.writeValueAsString(
            new FileMetadataModel(null, fileName, owner, fileType, size, null, null, null)
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/files/add"))
            .header("Content-Type", "application/json")
            .header("username", currentUsername)
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), FileMetadataModel.class);
        } else {
            throw new RuntimeException("Error adding file: " + response.body());
        }
    }

    public List<FileMetadataModel> getAllFiles() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/files/all"))
            .header("username", currentUsername)
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(),
                new TypeReference<List<FileMetadataModel>>() {});
        } else {
            throw new RuntimeException("Error getting files: " + response.body());
        }
    }

    public String verifyFile(Long fileId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/files/verify/" + fileId))
            .header("Content-Type", "application/json")
            .header("username", currentUsername)
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            var jsonNode = objectMapper.readTree(response.body());
            return jsonNode.get("status").asText() + ": " + jsonNode.get("message").asText();
        } else {
            throw new RuntimeException("Error verifying file: " + response.body());
        }
    }

    // Fixed: use HttpClient instead of HttpURLConnection
    public String callServlet() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/hello"))
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public List<AuditLogModel> getAllAuditLogs() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/audit/all"))
            .header("username", currentUsername)
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request,
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(),
                new TypeReference<List<AuditLogModel>>() {});
        } else {
            throw new RuntimeException("Error getting audit logs: " + response.body());
        }
    }
}