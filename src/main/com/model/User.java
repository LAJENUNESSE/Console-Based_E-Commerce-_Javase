package main.com.model;

public class User {
    private String username; // 用户名
    private String password; // 密码
    private String email;    // 电子邮件
    private String phone;    // 电话号码

    // 构造函数
    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    // 获取用户名
    public String getUsername() {
        return username;
    }

    // 设置用户名
    public void setUsername(String username) {
        this.username = username;
    }

    // 获取密码
    public String getPassword() {
        return password;
    }

    // 设置密码
    public void setPassword(String password) {
        this.password = password;
    }

    // 获取电子邮件
    public String getEmail() {
        return email;
    }

    // 设置电子邮件
    public void setEmail(String email) {
        this.email = email;
    }

    // 获取电话号码
    public String getPhone() {
        return phone;
    }

    // 设置电话号码
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 用户登录验证
    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}