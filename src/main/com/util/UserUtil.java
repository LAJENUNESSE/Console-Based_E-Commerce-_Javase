package main.com.util;

import main.com.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    private static final String USER_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\users.txt";

    // 添加用户到TXT文件
    public static void addUserToTxt(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
            bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhone());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 验证用户登录
    public static boolean validateUser(String username, String password) {
        List<User> users = readUsersFromTxt();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // 登录成功
            }
        }
        return false; // 登录失败
    }

    // 从TXT文件读取用户信息
    public static List<User> readUsersFromTxt() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 根据逗号分隔字段
                if (values.length == 4) { // 确保有四个字段
                    String username = values[0];
                    String password = values[1];
                    String email = values[2];
                    String phone = values[3];

                    User user = new User(username, password, email, phone);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    // 根据用户名查找用户
    public static User getUserByUsername(String username) {
        List<User> users = readUsersFromTxt();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user; // 找到用户
            }
        }
        return null; // 未找到用户
    }

    // 更新用户密码
    public static void updateUserPassword(User user) {
        List<User> users = readUsersFromTxt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            for (User currentUser : users) {
                if (currentUser.getUsername().equals(user.getUsername())) {
                    // 更新用户密码
                    bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhone());
                } else {
                    // 其他用户信息保持不变
                    bw.write(currentUser.getUsername() + "," + currentUser.getPassword() + "," + currentUser.getEmail() + "," + currentUser.getPhone());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 删除用户的方法
    public static boolean deleteUserByUsername(String username) {
        List<User> users = readUsersFromTxt();
        boolean userDeleted = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            for (User user : users) {
                if (!user.getUsername().equals(username)) {
                    // 只保留不匹配的用户
                    bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhone());
                    bw.newLine();
                } else {
                    // 找到并删除用户
                    userDeleted = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userDeleted; // 返回是否成功删除
    }
}