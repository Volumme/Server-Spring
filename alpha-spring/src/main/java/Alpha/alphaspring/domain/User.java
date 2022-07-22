package Alpha.alphaspring.domain;

public class User {
    private String id;
    private String pw;
    private String name;
    private String gender;
    private String phone_number;
    private int age;

    public User(String id, String pw, String name, String gender, String phone_number, int age) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.gender = gender;
        this.phone_number = phone_number;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
