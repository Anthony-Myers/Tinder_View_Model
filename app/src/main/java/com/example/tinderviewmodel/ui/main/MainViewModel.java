package com.example.tinderviewmodel.ui.main;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private String email = "";
    private String name = "";
    private String birthMonth = "";
    private String birthDay = "";
    private String birthYear = "";
    private String gender = "";
    private String school = "";

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBirthMonth() { return birthMonth; }

    public void setBirthMonth(String birthMonth) { this.birthMonth = birthMonth; }

    public String getBirthDay() { return birthDay; }

    public void setBirthDay(String birthDay) { this.birthDay = birthDay; }

    public String getBirthYear() { return birthYear; }

    public void setBirthYear(String birthYear) { this.birthYear = birthYear; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getSchool() { return school; }

    public void setSchool(String school) { this.school = school; }
}
