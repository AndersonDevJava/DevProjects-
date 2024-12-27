package Estudo.java.br.com.progressojava.Study.Class.dominio;

import java.util.ArrayList;
import java.util.Iterator;

public class ContactListManipulation {
    private ArrayList<ContactList> ListContact = new ArrayList<>();

    private static int cont = 1;

    // Add a new contact to the List/ validation if number and equals and if null
    public String addNewContact(ContactList contact) {
        if (contact == null || contact.getPhone() == null) {
            return "Contact or Phone invalid";
        }

        //Trim validation of phone
        String trimmedPhone = contact.getPhone().trim();
        if (trimmedPhone.isEmpty()) {
            return "Inválid! Add a valid phone number ";
        }

        //Duplicate Number verification in the list
        for (ContactList contactList : ListContact) {
            if (contactList.getPhone().equals(trimmedPhone)) {
                throw new IllegalArgumentException("Invalid! This number  already exists");
            }
        }

        DataValidation.validateEmail(contact.getEmail(), ListContact);

        contact.setId(cont++);
        ListContact.add(contact);
        return "Contact added: " + contact;
    }

    // Print Numbers fromm the List

    public String printListContact() {
        StringBuilder sb = new StringBuilder();
        if (ListContact.isEmpty()) {
            return "Empty List";
        }
        for (ContactList contact : ListContact) {
            sb.append("Id: ").append(contact.getId()).append("\n")
                    .append("Name: ").append(contact.getName()).append("\n")
                    .append("Phone: ").append(contact.getPhone()).append("\n")
                    .append("Email: ").append(contact.getEmail()).append("\n\n");

        }
        return sb.toString();
    }

    // Search by name a
    public String SearchByName(String name) {
        DataValidation.validateName(name);
        ArrayList<ContactList> foundContacts = new ArrayList<>();

        for (ContactList contact : ListContact) {
            if (contact.getName().equalsIgnoreCase(name.trim())) {
                foundContacts.add(contact);
            }
        }
        if (foundContacts.isEmpty()) {
            return "Contact not found with name: " + name;
        }
        StringBuilder sb = new StringBuilder("Found contact with name" + name + "\n");

        for (ContactList contact : foundContacts) {
            sb.append("Id: ").append(contact.getId()).append("\n")
                    .append("Name: ").append(contact.getName()).append("\n")
                    .append("Phone: ").append(contact.getPhone()).append("\n")
                    .append("Email: ").append(contact.getEmail()).append("\n\n");
        }
        return sb.toString();
    }

    //update data contact by name
    public String updateData(String name, String phone, String email) {
        DataValidation.validateName(name);
        DataValidation.validatePhone(phone);
        DataValidation.validateEmail(email, ListContact);

        for (ContactList contact : ListContact) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contact.setPhone(phone.trim());
                contact.setEmail(email.trim());
                return String.format("Contact updated: Name: %s, Phone: %s, Email: %s",
                        contact.getName(), contact.getPhone(), contact.getEmail());
            }
        }
        return String.format("No contact found with the name %s", name);
    }

    public String updateDataById(int id, String phone, String newEmail, String newPhone) {
        DataValidation.validatePhone(phone);
        DataValidation.validateEmail(newEmail, ListContact);
        DataValidation.validatePhone(newPhone);

        ContactList contactToUpdate = null;

        for (ContactList contactList : ListContact) {
            if (contactList.getPhone().equalsIgnoreCase(phone)) {
                contactToUpdate = contactList;
                break;
            }
        }
        if (contactToUpdate != null) {
            String oldEmail = contactToUpdate.getEmail();
            String oldPhone = contactToUpdate.getPhone();

            contactToUpdate.setEmail(newEmail);
            contactToUpdate.setPhone(newPhone);

            // Retornando os dados antes e depois da atualização
            return String.format("Found Contact:\nName: %s\nEmail: %s\nPhone: %s\n\n" +
                            "Updated data:\nNew Email: %s\nNew Phone: %s",
                    contactToUpdate.getName(), oldEmail, oldPhone, newEmail, newPhone);
        }
        // Caso o contato não seja encontrado
        return "Contact not found with number: " + phone;
    }

    //Delete name if in list
    public String nameDelete(String name) {
        DataValidation.validateName(name);
        ArrayList<ContactList> foundContact = new ArrayList<>();

        for (ContactList contactList : ListContact) {
            if (contactList.getName().equalsIgnoreCase(name)) {
                foundContact.add(contactList);
            }
        }

        if (foundContact.isEmpty()) {
            return "Found contact not with name: " + name;
        }

        if (foundContact.size() == 1) {
            ContactList contactRemove = foundContact.get(0);
            ListContact.remove(contactRemove);
            return "Deleted contact " + contactRemove;
        }

        // Se houver múltiplos contatos com o mesmo nome
        StringBuilder sb = new StringBuilder("Vários contatos encontrados com o nome: " + name + "\n");

        for (ContactList contact : foundContact) {
            sb.append("ID: ").append(contact.getId()).append(", Name: ").append(contact.getName())
                    .append(", Phone: ").append(contact.getPhone()).append(", Email: ").append(contact.getEmail()).append("\n");
        }
        sb.append("Information the Id of contact to remove \n.");

        return sb.toString();
    }

    //Delete Contact by id
    public String deleteContactById(int id) {
       Iterator<ContactList> iterator = ListContact.iterator();
       while (iterator.hasNext()){
           ContactList contactList = iterator.next();
           if(contactList.getId() == id){
               iterator.remove();
               return "Contact with ID "+ id + "Remove Success!";
           }
       }
       return "Contact with Id "+ id +" not found";
    }

    public ArrayList<ContactList> getListContact() {
        return ListContact;
    }

    public void setListContact(ArrayList<ContactList> listContact) {
        ListContact = listContact;
    }

    public static int getCont() {
        return cont;
    }

    public static void setCont(int cont) {
        ContactListManipulation.cont = cont;
    }
}