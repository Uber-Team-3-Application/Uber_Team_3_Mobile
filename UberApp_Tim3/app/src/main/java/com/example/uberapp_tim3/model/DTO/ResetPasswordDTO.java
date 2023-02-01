package com.example.uberapp_tim3.model.DTO;


public class ResetPasswordDTO {

    private String newPassword;
    private String code;

    public ResetPasswordDTO() {
    }

    public ResetPasswordDTO(String newPassword, String code) {
        this.newPassword = newPassword;
        this.code = code;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
