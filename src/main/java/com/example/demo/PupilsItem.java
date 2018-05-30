package com.example.demo;

public class PupilsItem {

    private String id, name, gender, years, course, grade;
    private ContactInfo contactInfo;

    public PupilsItem() {

    }

    public PupilsItem (String id, String name, String gender, String years, String grade, String course, ContactInfo contactInfo) {

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.years = years;
        this.grade = grade;
        this.course = course;
        this.contactInfo = contactInfo;
    }

    public PupilsItem (String id, String name, String gender, String years, String grade, String course) {

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.years = years;
        this.grade = grade;
        this.course = course;
    }

    public ContactInfo getContactInfo() {

        return contactInfo;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    class ContactInfo {

        private String name;
        private String homePage;
        private String phone;

        public ContactInfo() {}

        public ContactInfo(String homePage, String phone) {

            this.homePage = homePage;
            this.phone = phone;
        }

        public String getHomePage() {

            return homePage;
        }

        public String getPhone() {

            return phone;
        }


    }


}
