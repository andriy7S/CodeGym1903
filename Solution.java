package com.codegym.task.task19.task1903;

/* 
Adapting multiple interfaces

*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("US", "United States");
        countries.put("FR", "France");
    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            String countryCode = data.getCountryCode();
            return Solution.countries.get(countryCode);
        }

        @Override
        public String getName() {
            String firstName = data.getContactFirstName();
            String lastName = data.getContactLastName();
            return lastName + ", " + firstName;
        }

        @Override
        public String getPhoneNumber() {
            String phoneNumber = String.format("%010d", data.getPhoneNumber());
            phoneNumber = phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d{2})(\\d{2})", "($1)$2-$3-$4");
            phoneNumber = "+" + data.getCountryPhoneCode() + phoneNumber;
            return phoneNumber;
        }
    }


    public static interface IncomeData {
        String getCountryCode();        // For example: US

        String getCompany();            // For example: CodeGym Ltd.

        String getContactFirstName();   // For example: John

        String getContactLastName();    // For example: Smith

        int getCountryPhoneCode();      // For example: 1

        int getPhoneNumber();           // For example: 991234567
    }

    public static interface Customer {
        String getCompanyName();        // For example: CodeGym Ltd.

        String getCountryName();        // For example: United States
    }

    public static interface Contact {
        String getName();               // For example: Smith, John

        String getPhoneNumber();        // For example: +1(099)123-45-67
    }
}