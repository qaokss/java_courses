package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String title;
    private final String address;
    private final String mobilephone;
    private final String email1;
    private final String homepage;
    private final String birthdayDay;
    private final String birthdayMonth;
    private final String birthdayYear;
    private final String group;


    public ContactData(int id, String firstname, String middlename, String lastname,  String title,
                       String address, String mobilephone,
                        String email1, String homepage, String birthdayDay,
                       String birthdayMonth, String birthdayYear,
                       String group) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.title = title;
        this.address = address;
        this.mobilephone = mobilephone;
        this.email1 = email1;
        this.homepage = homepage;
        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
        this.group = group;
    }


    public ContactData(String firstname, String middlename, String lastname,  String title,
                       String address, String mobilephone,
                       String email1, String homepage, String birthdayDay,
                       String birthdayMonth, String birthdayYear,
                       String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.title = title;
        this.address = address;
        this.mobilephone = mobilephone;
        this.email1 = email1;
        this.homepage = homepage;
        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
        this.group = group;
    }

    public ContactData(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = null;
        this.lastname = lastname;
        this.title = null;
        this.address = null;
        this.mobilephone = null;
        this.email1 = null;
        this.homepage = null;
        this.birthdayDay = null;
        this.birthdayMonth = null;
        this.birthdayYear = null;
        this.group = null;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
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

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail1() {
        return email1;
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

}

