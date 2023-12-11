package com.example.final_app;

public class Folder {
    private String FolderName, FolderDescription;

    public Folder(){

    }

    public Folder(String folderName, String folderDescription) {
        FolderName = folderName;
        FolderDescription = folderDescription;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public String getFolderDescription() {
        return FolderDescription;
    }

    public void setFolderDescription(String folderDescription) {
        FolderDescription = folderDescription;
    }
}
