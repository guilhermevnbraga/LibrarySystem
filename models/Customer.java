package models;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date birthDate;
    private Boolean isAdmin;

    public Customer(int id, String name, String email, String password, Date birthDate, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.isAdmin = isAdmin;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDateString = (birthDate != null) ? dateFormat.format(birthDate) : "N/A";
        return """
            Customer Details:
            ID: %d
            Name: %s
            Email: %s
            Password: %s
            Birth Date: %s
            Admin: %s
            """.formatted(id, name, email, password, birthDateString, isAdmin ? "Yes" : "No");
    }
}
