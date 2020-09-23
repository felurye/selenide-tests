package common;

import com.codeborne.selenide.Configuration;

import pages.LoginPage;
import pages.MoviePage;
import pages.SideBar;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.screenshot;

public class BaseTest {
    protected static LoginPage login;
    protected static SideBar side;
    protected static MoviePage movie;

    @BeforeMethod
    public void start() {
        Properties prop = new Properties();

        InputStream inputFile = getClass().getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(inputFile);
        } catch (Exception ex) {
            System.out.println("Erro ao ler arquivo config.properties. Trace=> " + ex.getMessage());
        }

        Configuration.browser = prop.getProperty("browser");
        Configuration.baseUrl = prop.getProperty("urlQA");
        Configuration.timeout = Long.parseLong(prop.getProperty("timeout"));

        login = new LoginPage();
        side = new SideBar();
        movie = new MoviePage();
    }

    @AfterMethod
    public void finish() {
        // Print tirado pelo Selenide
        String tempShot = screenshot("temp_shot");

        // Transformando a img em binário para anexar no Allure
        try {
            assert tempShot != null;
            BufferedImage bimage = ImageIO.read(new File(tempShot));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(bimage, "png", baos);

            byte[] finalShot = baos.toByteArray();
            io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(finalShot));

        } catch (Exception ex) {
            System.out.println("Erro ao anexar evidência. Trace =>" + ex);
        }
    }
}
