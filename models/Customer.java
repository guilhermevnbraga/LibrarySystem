package models;
import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private int password;
    private Date birthDate;
    private Date createdAt;
    private Date updatedAt;

    public Customer(int id, String name, String email, int password, Date birthDate, Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
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

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPassword() {
        return this.password;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", birthDate=" + birthDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
