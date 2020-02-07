package com.example.register.models.getCompanyData;

import java.util.List;

public class CompanyDataDto {
    private String code;
    private String message;
    private Boolean accept;
    private CompanyResponseDto data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public CompanyResponseDto getData() {
        return data;
    }

    public void setData(CompanyResponseDto data) {
        this.data = data;
    }
}
