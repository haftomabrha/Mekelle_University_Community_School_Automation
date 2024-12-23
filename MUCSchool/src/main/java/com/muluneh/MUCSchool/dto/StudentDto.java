package com.muluneh.MUCSchool.dto;

public class StudentDto extends UserDto{
    private String parentContact;

    public String getParentContact() {
        return parentContact;
    }

    public void setParentContact(String parentContact) {
        this.parentContact = parentContact;
    }
}
