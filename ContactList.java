package Estudo.java.br.com.progressojava.Study.Class.dominio;

import java.util.List;

public class ContactList {
    private String name;
    private String phone;
    private String email;
    private int id;

    public ContactList(String name, String phone, String email, List<ContactList> contactLists) {
        DataValidation.validateName(name);
        DataValidation.validatePhone(phone);
        DataValidation.validateEmail(email, contactLists);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    @Override
    public String toString() {
        return String.format("Contact{name='%s', Phone='%s', Email='%s'}", name, phone, email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
