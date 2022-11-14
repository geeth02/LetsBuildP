package com.example.letsbuild;

public class Product {
    private String userId;
    private String title;
    private String description;
    private String photoUri;
    private String price;
    private String category;
    private String phonenumber;
    private String status;

    public Product() {
    }

    public Product(String userId, String title, String description, String photoUri, String price, String category, String phonenumber, String status) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.photoUri = photoUri;
        this.price = price;
        this.category = category;
        this.phonenumber = phonenumber;
        this.status = status;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
