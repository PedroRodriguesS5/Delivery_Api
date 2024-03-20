package com.deliveryproject.user;

public enum UserRole {
    Admin("ADMIN"),
    User("USER");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
