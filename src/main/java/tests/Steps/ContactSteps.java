package tests.Steps;

import tests.Pages.ContactPage;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class ContactSteps {
    private static ContactPage contactPage=new ContactPage();

    private static final Map<String, Consumer<String>> fieldActions = new HashMap<>();

    static {
        fieldActions.put("Email", email -> {
            contactPage.contactEmailField.click();
            contactPage.contactEmailField.sendKeys(email);
        });
        fieldActions.put("Contact Name", name -> {
            contactPage.contactNameField.click();
            contactPage.contactNameField.sendKeys(name);
        });
        fieldActions.put("Message", message -> {
            contactPage.messageField.click();
            contactPage.messageField.sendKeys(message);
        });
    }




    public void theUserEntersValueInField(String value, String field) {

        Consumer<String> action = fieldActions.get(field);
        if (action != null) {
            action.accept(value);
            Selenide.sleep(1000);
        } else {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }


    @And("fill in the New Message Popup with the contact email, contact name, and message")
    public void fillInTheNewMessagePopupWithTheContactEmailContactNameAndMessage() {
       theUserEntersValueInField("ecaterinah@mail.ru","Email");
       theUserEntersValueInField("Ecaterina","Contact Name");
       theUserEntersValueInField("test","Message");

    }

    @And("fill in the New Message Popup with the contact name, and message")
    public void fillInTheNewMessagePopupWithTheContactNameAndMessage() {
        theUserEntersValueInField("Ecaterina","Contact Name");
        theUserEntersValueInField("test","Message");
    }

    @And("fill in the New Message Popup with the contact email and message")
    public void fillInTheNewMessagePopupWithTheContactEmailAndMessage() {
        theUserEntersValueInField("ecaterinah@mail.ru","Email");
        theUserEntersValueInField("test","Message");
    }

    @And("fill in the New Message Popup with the contact email and contact name")
    public void fillInTheNewMessagePopupWithTheContactEmailAndContactName() {
        theUserEntersValueInField("ecaterinah@mail.ru","Email");
        theUserEntersValueInField("Ecaterina","Contact Name");
    }

    @And("fill in the New Message Popup with the invalid email format, valid contact name, and valid message")
    public void fillInTheNewMessagePopupWithTheInvalidEmailFormatValidContactNameAndValidMessage() {
        theUserEntersValueInField("123","Email");
        theUserEntersValueInField("Ecaterina","Contact Name");
        theUserEntersValueInField("test","Message");

    }
}
