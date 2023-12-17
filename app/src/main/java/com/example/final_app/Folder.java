package com.example.final_app;

import java.util.Date;

public class Folder {
    private String FolderName, FolderDescription, userName, FolderId, userId;
    private Date dateCreated;
    public Folder(){

    }

    public Folder(String folderName, String folderDescription, String userName, String folderId, Date DateCreated,String userId) {
        this.FolderName = folderName;
        this.FolderDescription = folderDescription;
        this.userName = userName;
        this.FolderId=folderId;
        this.dateCreated=DateCreated;
        this.userId=userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
