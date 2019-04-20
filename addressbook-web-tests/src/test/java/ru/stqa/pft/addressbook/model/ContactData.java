package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String title;
    private String address;
    private String mobilephone;
    private String homephone;
    private String workphone;
    private String allPhones;
    private String email1;
    private String email2;
    private String email3;
    private String allEmails;
    private String homepage;
    private String birthdayDay;
    private String birthdayMonth;
    private String birthdayYear;
    private String group;



    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }


    public ContactData withHomePhone(String homephone) {
        this.homephone = homephone;
        return this;
    }


    public ContactData withWorkPhone(String workphone) {
        this.workphone = workphone;
        return this;
    }


    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
        return this;
    }

    public ContactData withBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
        return this;
    }

    public ContactData withBirthdayYear(String birthdayYear) {
        this.birthdayYear = birthdayYear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }


    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilephone;
    }

    public String getHomePhone() { return homephone; }

    public String getWorkPhone() { return workphone; }

    public String getAllPhones() { return allPhones; }

    public String getEmail1() { return email1; }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllEmails() { return allEmails; }

    public String getHomepage() {
        return homepage;
    }

    public String getBirthdayDay() {
        return birthdayDay;
    }

    public String getBirthdayMonth() {
        return birthdayMonth;
    }

    public String getBirthdayYear() {
        return birthdayYear;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

}

