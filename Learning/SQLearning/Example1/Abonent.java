package Learning.SQLearning.Example1;

/**
 * Created by Денис on 25.02.2017.
 */
public class Abonent extends Entity {
    private int phone;
    private String lastname;
    public Abonent() {
    }
    public Abonent(int id, int phone, String lastname) {
        super(id);
        this.phone = phone;
        this.lastname = lastname;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String toString() {
        return "Abonent [id=" + id + ", phone=" + phone + ", lastname=" + lastname + "]";
    }
}
