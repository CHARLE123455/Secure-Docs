package dev.charles.Secure_Docs.entity;

import java.time.LocalDateTime;

public class User extends  Auditable{
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer loginAttempts;
    private LocalDateTime lastLogin;
    private String bio;
    private String phone;
    private String imageUrl;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean mfa;
    private String qrCodeSecret;
    private String qrCodeUri;
    private  String roles;
}
