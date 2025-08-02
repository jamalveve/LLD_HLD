package src.BuilderDesignPattern;

public class UserBuilder {
    public static void main(String[] args) {
        User user1 = new User.UserBuilder("John", "Doe")
                .age(30)
                .phone("1234567890")
                .address("123 Main Street")
                .build();

        User user2 = new User.UserBuilder("Jane", "Smith").build(); // no optional fields

        System.out.println(user1);
        System.out.println(user2);
    }

}

// 1. Product class
class User {
    // mandatory fields
    private final String firstName;
    private final String lastName;

    // optional fields
    private final int age;
    private final String phone;
    private final String address;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Getters (no setters to maintain immutability)
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName +
                ", age=" + age + ", phone=" + phone + ", address=" + address + "]";
    }

    // 2. Builder class
    public static class UserBuilder {
        private final String firstName;
        private final String lastName;

        private int age;
        private String phone;
        private String address;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this; // enables chaining
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
