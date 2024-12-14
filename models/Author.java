package models;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Author {
    private int id;
    private String name;
    private Date birthDate;

    public Author(int id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDateString = (birthDate != null) ? dateFormat.format(birthDate) : "N/A";
        return """
               Author Details:
               ID: %d
               Name: %s
               Birth Date: %s
               """.formatted(id, name, birthDateString);
    }
}
