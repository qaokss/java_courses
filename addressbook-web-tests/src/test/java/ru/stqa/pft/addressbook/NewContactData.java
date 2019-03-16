package ru.stqa.pft.addressbook;

public class NewContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String namecompany;
    private final String address;
    private final String homephone;
    private final String mobilephone;
    private final String workphone;
    private final String fax;
    private final String email1;
    private final String email2;
    private final String email3;
    private final String homepage;
    private final String birthdayDay;
    private final String birthdayMonth;
    private final String birthdayYear;
    private final String annyDay;
    private final String annyMonth;
    private final String annyYear;
    private final String group;
    private final String address2;
    private final String home;
    private final String notes;

    public NewContactData(String firstname, String middlename, String lastname, String nickname, String title,
                          String namecompany, String address, String homephone, String mobilephone, String workphone,
                          String fax, String email1, String email2, String email3, String homepage, String birthdayDay,
                          String birthdayMonth, String birthdayYear, String annyDay, String annyMonth, String annyYear,
                          String group, String address2, String home, String notes) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.namecompany = namecompany;
        this.address = address;
        this.homephone = homephone;
        this.mobilephone = mobilephone;
        this.workphone = workphone;
        this.fax = fax;
        this.email1 = email1;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
        this.annyDay = annyDay;
        this.annyMonth = annyMonth;
        this.annyYear = annyYear;
        this.group = group;
        this.address2 = address2;
        this.home = home;
        this.notes = notes;
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

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getNamecompany() {
        return namecompany;
    }

    public String getAddress() {
        return address;
    }

    public String getHomephone() {
        return homephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getFax() {
        return fax;
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

    public String getAnnyDay() {
        return annyDay;
    }

    public String getAnnyMonth() {
        return annyMonth;
    }

    public String getAnnyYear() {
        return annyYear;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress2() {
        return address2;
    }

    public String getHome() {
        return home;
    }

    public String getNotes() {
        return notes;
    }
}
