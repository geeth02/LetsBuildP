package com.example.letsbuild;

public class UserInfo {

    private String userId;
    private String nicNumber;
    private String name;
    private String phoneNumber;
    private String addressLine01;
    private String addressLine02;
    private String city;
    private String nicBack;
    private String nicFront;
    private String status;

    public UserInfo(String userId, String nicNumber, String name, String phoneNumber, String addressLine01, String addressLine02, String city, String nicBack, String nicFront, String status) {
        this.userId = userId;
        this.nicNumber = nicNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressLine01 = addressLine01;
        this.addressLine02 = addressLine02;
        this.city = city;
        this.nicBack = nicBack;
        this.nicFront = nicFront;
        this.status = status;
    }

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine01() {
        return addressLine01;
    }

    public void setAddressLine01(String addressLine01) {
        this.addressLine01 = addressLine01;
    }

    public String getAddressLine02() {
        return addressLine02;
    }

    public void setAddressLine02(String addressLine02) {
        this.addressLine02 = addressLine02;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNicBack() {
        return nicBack;
    }

    public void setNicBack(String nicBack) {
        this.nicBack = nicBack;
    }

    public String getNicFront() {
        return nicFront;
    }

    public void setNicFront(String nicFront) {
        this.nicFront = nicFront;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
