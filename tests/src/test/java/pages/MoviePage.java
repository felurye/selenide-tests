package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.MovieModel;
import org.openqa.selenium.Keys;

import java.util.List;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MoviePage {

    public MoviePage add() {
        $(".movie-add").click();
        return this;
    }

    public MoviePage search(String value) {
        $("input[placeholder^=Pesquisar]").setValue(value);
        $("#search-movie").click();
        return this;
    }

    public MoviePage create(MovieModel movie) {
        $("input[name=title]").setValue(movie.title);
        this.selectStatus(movie.status);
        $("input[name=year]").setValue(movie.year.toString());
        $("input[name=release_date]").setValue(movie.releaseDate);
        this.inputCast(movie.cast);
        $("textarea[name=overview]").setValue(movie.plot);
        this.upload(movie.cover);
        $("#create-movie").click();

        return this;
    }

    public ElementsCollection items() {
        return $$("table tbody tr");
    }

    private void upload(File cover) {
        String jsScript = "document.getElementById('upcover').classList.remove('el-upload__input');";
        executeJavaScript(jsScript);

        $("#upcover").uploadFile(cover);
    }

    private void inputCast(List<String> cast) {
        SelenideElement element = $(".cast");

        for (String actor : cast) {
            element.setValue(actor);
            element.sendKeys(Keys.TAB);
        }
    }

    private void selectStatus(String status) {
        $("input[placeholder=Status]").click();
        $$("ul li span").findBy(text(status)).click();
    }
}
