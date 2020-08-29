package com.muniba3.ComplainReporting.model;

public class Model {
    private int id;
    private String categorie;
    private String severity;
    private String description;
    private byte[] image;

    public Model(int id, String categorie, String severity, String description, byte[] image) {
        this.id = id;
        this.categorie = categorie;
        this.severity = severity;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}