package edu.hw3;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Task5 {

    public static final String ASC_PARAMETER = "ASC";
    public static final String DESC_PARAMETER = "DESC";

    private Task5() {

    }

    public static List<Contact> parseContacts(List<String> names, String order) {

        if (!order.equals(ASC_PARAMETER) && !order.equals(DESC_PARAMETER)) {
            throw new IllegalArgumentException();
        }

        if (names == null) {
            return List.of();
        }

        Comparator<Contact> comparator = (contact1, contact2) -> {
            String lastName1 = contact1.getLastName();
            String lastName2 = contact2.getLastName();

            if (lastName1 == null) {
                lastName1 = contact1.getFirstName();
            }

            if (lastName2 == null) {
                lastName2 = contact2.getFirstName();
            }

            return order.equals(ASC_PARAMETER)
                ? lastName1.compareTo(lastName2) : lastName2.compareTo(lastName1);
        };

        return names.stream()
            .map(Contact::new)
            .sorted(comparator)
            .toList();
    }

    public static class Contact {
        private String firstName;
        private String lastName;

        public Contact(String fullName) {
            String[] parts = fullName.split(" ");
            if (parts.length >= 2) {
                this.firstName = parts[0];
                this.lastName = parts[1];
            } else {
                this.firstName = fullName;
            }
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Contact contact = (Contact) o;
            return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }
    }
}
