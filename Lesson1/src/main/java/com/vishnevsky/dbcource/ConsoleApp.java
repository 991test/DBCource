package com.vishnevsky.dbcource;

import com.vishnevsky.dbcource.base.DataBase;
import com.vishnevsky.dbcource.exceptions.DBException;
import com.vishnevsky.dbcource.model.Contact;
import com.vishnevsky.dbcource.model.ContactImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleApp {

    private DataBase dataBase = new DataBase();
    private Pattern pattern = Pattern.compile("((\\\"[^\"]+\\\")|\\S+)");
    private Matcher matcher;

    public void initialize() {

        Contact contact1 = new ContactImpl("Vasya", "555-55-55", "some info");

        try {
            dataBase.create(contact1);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public String create(String input) {

        String answer;

        String[] pResult = parseCommand(input);

        if (pResult.length == 4){

            try {
                dataBase.create(new ContactImpl(pResult[1], pResult[2], pResult[3]));
                answer = "Success";
            } catch (DBException e) {
                answer = e.getMessage();
            }
        } else {
            answer = "CREATE \"name\" \"phone\" \"info\"";
        }

        return answer;
    }

    public String get(String input) {

        String answer;

        String[] pResult = parseCommand(input);

        if (pResult.length != 2) {
            answer = "GET \"name\"";
        } else {
            try {
                Contact contact = dataBase.get(pResult[1]);
                answer = contact.toString();
            } catch (DBException e) {
                answer = e.getMessage();
            }
        }

        return answer;
    }

    public String delete(String input) {

        String answer;

        String[] pResult = parseCommand(input);

        if (pResult.length != 2) {
            answer = "DELETE \"name\"";
        } else {
            try {
                dataBase.delete(new ContactImpl(pResult[1], "", ""));
                answer = "Success";
            } catch (DBException e) {
                answer = e.getMessage();
            }
        }

        return answer;
    }

    public String update(String input) {

        String answer;

        String[] pResult = parseCommand(input);

        if (pResult.length != 5) {
            answer = "UPDATE \"oldName\" \"newName\" \"newPhone\" \"newInfo\"";
        } else {
            try {
                Contact contact = dataBase.update(pResult[1],new ContactImpl(pResult[2], pResult[3], pResult[4]));
                answer = contact.toString();
            } catch (DBException e) {
                answer = e.getMessage();
            }
        }

        return answer;
    }

    private String[] parseCommand(String command) {

        List<String> result = new ArrayList<String>();

        matcher = pattern.matcher(command);
        while (matcher.find()) {
            result.add( matcher.group().replace("\"","") );
        }

        return result.toArray(new String[1]);
    }

}
