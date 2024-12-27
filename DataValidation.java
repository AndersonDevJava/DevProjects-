package Estudo.java.br.com.progressojava.Study.Class.dominio;

import java.util.List;

public class DataValidation {

    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid Name! Add a name");
        } else if (!name.matches("[a-zA-ZÀ-Ýà-ý]+(?:\\s[a-zA-ZÀ-Ýà-ý]+)*")) {
            throw new IllegalArgumentException("Invalid Name! Add only letters");
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\(\\d{2}\\) ?9\\d{4}-\\d{4}")) {
            throw new IllegalArgumentException("Number of phone invalid! Formation (xx) 9xxxx-xxxx");
        }
    }

    public static void validateEmail(String email, List<ContactList> contactLists) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid Email! Add a valid e-mail.");
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid Email! Add a valid e-mail with '@' and '.' ");
        }

        for(ContactList contactList : contactLists){
            if (email.equalsIgnoreCase(contactList.getEmail())){
                throw new IllegalArgumentException("Invalid! This email already exists.");
            }
        }
    }
}
