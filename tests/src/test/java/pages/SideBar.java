package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SideBar {
    public SelenideElement loggedUser() {
        return $(".user .info span");
    }
}
