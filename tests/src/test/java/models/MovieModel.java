package models;

import java.util.List;
import java.io.File;

public class MovieModel {
    public String title;
    public String status;
    public Integer year;
    public String releaseDate;
    public List<String> cast;
    public String plot;
    public File cover;

    public MovieModel(String title, String status, Integer year, String releaseDate, List<String> cast, String plot, String cover) {
        this.title = title;
        this.status = status;
        this.year = year;
        this.releaseDate = releaseDate;
        this.cast = cast;
        this.plot = plot;
        this.cover = new File(this.coverPath() + cover);
    }

    private String coverPath() {
        String executionPath = System.getProperty("user.dir");
        String os = System.getProperty("os.name");

        String target;

        if (os.contains("Windows")) {
            target = executionPath + "\\src\\main\\resources\\cover\\";
        } else {
            target = executionPath + "/src/main/resources/cover/";
        }

        return target;
    }
}
