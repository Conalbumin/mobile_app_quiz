package com.example.final_app;

public class Folder {
    private String FolderName, FolderDescription, userName, FolderId;

    public Folder(){

    }

    public Folder(String folderName, String folderDescription, String userName, String folderId) {
        this.FolderName = folderName;
        this.FolderDescription = folderDescription;
        this.userName = userName;
        this.FolderId=folderId;
    }

    public String getFolderId() {
        return FolderId;
    }

    public void setFolderId(String folderId) {
        FolderId = folderId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
