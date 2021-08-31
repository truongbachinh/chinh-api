package util;
class User{
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = a; // tham trị
        a = 2;

        User user1 = new User("Chinh");
        User user2 = user1;
        user1.setName("Linh");

        System.out.println(b); // 1
        System.out.println(user2.getName());// Linh
    }
}
