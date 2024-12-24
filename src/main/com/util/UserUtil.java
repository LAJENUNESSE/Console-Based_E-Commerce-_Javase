/**
 * @file UserUtil.java
 * @brief 用户工具类，提供用户相关的文件操作和管理功能
 * @package main.com.util
 */
package main.com.util;

import main.com.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @class UserUtil
 * @brief 用户工具类，处理用户信息的文件读写和管理操作
 * @details 提供用户的增删改查等基本操作，使用文本文件作为数据存储
 */
public class UserUtil {
    /** @brief 用户信息文件路径 */
    private static final String USER_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\users.txt";

    /**
     * @brief 添加用户到文本文件
     * @param user 要添加的用户对象
     * @details 将用户信息追加到用户文件末尾
     * @throws IOException 写入文件时可能发生的IO异常
     */
    public static void addUserToTxt(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
            bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhone());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief 验证用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录是否成功
     * @details 从文件中读取用户信息，匹配用户名和密码
     */
    public static boolean validateUser(String username, String password) {
        List<User> users = readUsersFromTxt();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // 登录成功
            }
        }
        return false; // 登录失败
    }

    /**
     * @brief 从文本文件读取用户信息
     * @return 用户列表
     * @details 解析文本文件，将每行数据转换为User对象
     * @throws IOException 读取文件时可能发生的IO异常
     */
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

    /**
     * @brief 根据用户名查找用户
     * @param username 要查找的用户名
     * @return 查找到的用户对象，未找到返回null
     */
    public static User getUserByUsername(String username) {
        List<User> users = readUsersFromTxt();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user; // 找到用户
            }
        }
        return null; // 未找到用户
    }

    /**
     * @brief 更新用户密码
     * @param user 包含更新信息的用户对象
     * @details 更新文件中对应用户的信息
     * @throws IOException 写入文件时可能发生的IO异常
     */
    public static void updateUserPassword(User user) {
        // 添加输入验证
        validateUserInput(user);

        List<User> users = readUsersFromTxt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            for (User currentUser : users) {
                if (currentUser.getUsername().equals(user.getUsername())) {
                    // 更新用户密码
                    bw.write(user.getUsername() + "," +
                            user.getPassword() + "," +
                            user.getEmail() + "," +
                            user.getPhone());
                } else {
                    // 其他用户信息保持不变
                    bw.write(currentUser.getUsername() + "," +
                            currentUser.getPassword() + "," +
                            currentUser.getEmail() + "," +
                            currentUser.getPhone());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("更新用户密码失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @brief 根据用户名删除用户
     * @param username 要删除的用户名
     * @return 是否成功删除用户
     * @details 从文件中移除指定用户
     * @throws IOException 写入文件时可能发生的IO异常
     */
    public static boolean deleteUserByUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        List<User> users = readUsersFromTxt();
        boolean userDeleted = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            for (User user : users) {
                if (!user.getUsername().equals(username)) {
                    // 只保留不匹配的用户
                    bw.write(user.getUsername() + ","
                            + user.getPassword()
                            + "," + user.getEmail()
                            + "," + user.getPhone());
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

    /**
     * @brief 用户输入验证方法
     * @param user 待验证的用户对象
     * @throws IllegalArgumentException 当用户输入不合法时
     */
    private static void validateUserInput(User user) {
        // 用户名验证：不能为空，长度3-20
        if (user.getUsername() == null ||
                user.getUsername().trim().length() < 3 ||
                user.getUsername().trim().length() > 20) {
            throw new IllegalArgumentException("用户名长度必须在3-20个字符之间");
        }

        // 密码验证：不能为空，长度6-20
        if (user.getPassword() == null ||
                user.getPassword().trim().length() < 6 ||
                user.getPassword().trim().length() > 20) {
            throw new IllegalArgumentException("密码长度必须在6-20个字符之间");
        }

        // 邮箱验证：使用正则表达式
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (user.getEmail() == null ||
                !Pattern.matches(emailRegex, user.getEmail())) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }

        // 电话号码验证：只能包含数字，长度11位
        String phoneRegex = "^\\d{11}$";
        if (user.getPhone() == null ||
                !Pattern.matches(phoneRegex, user.getPhone())) {
            throw new IllegalArgumentException("电话号码必须是11位数字");
        }
    }
}