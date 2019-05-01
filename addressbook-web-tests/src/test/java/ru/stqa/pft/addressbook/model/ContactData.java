package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "middlename")
    private String middlename;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Transient
    private String title;

    @Expose
    @Transient
    private String address;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workphone;

    @Transient
    private String allPhones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(title, that.title) &&
                Objects.equals(address, that.address) &&
                Objects.equals(mobilephone, that.mobilephone) &&
                Objects.equals(homephone, that.homephone) &&
                Objects.equals(workphone, that.workphone) &&
                Objects.equals(email1, that.email1) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(homepage, that.homepage) &&
                Objects.equals(birthdayDay, that.birthdayDay) &&
                Objects.equals(birthdayMonth, that.birthdayMonth) &&
                Objects.equals(birthdayYear, that.birthdayYear) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename, lastname, title, address, mobilephone, homephone, workphone, email1, email2, email3, homepage, birthdayDay, birthdayMonth, birthdayYear, group);
    }

    @Expose
    @Transient
    private String email1;

    @Expose
    @Transient
    private String email2;

    @Expose
    @Transient
    private String email3;

    @Transient
    private String allEmails;

    @Expose
    @Transient
    private String homepage;

    @Expose
    @Transient
    private String birthdayDay;

    @Expose
    @Transient
    private String birthdayMonth;

    @Expose
    @Transient
    private String birthdayYear;

    @Expose
    @Transient
    private String group;

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;


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

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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

    public String getHomePhone() {
        return homephone;
    }

    public String getWorkPhone() {
        return workphone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

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

    public File getPhoto() {
        return new File(photo);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}

