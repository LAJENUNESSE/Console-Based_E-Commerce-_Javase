/**
 * @file AdminUtil.java
 * @brief 管理员工具类，提供管理员相关的文件操作
 * @package main.com.util
 */
package main.com.util;

import main.com.model.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @class AdminUtil
 * @brief 管理员工具类，处理管理员信息的读取、验证和删除
 * @details 提供从文本文件读取、验证和管理管理员信息的静态方法
 */
public class AdminUtil {
    /** @brief 管理员信息文件的绝对路径 */
    private static final String ADMIN_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\Admin.txt";

    /**
     * @brief 从文本文件读取管理员信息
     * @return 包含所有管理员信息的列表
     * @details 读取 Admin.txt 文件，解析每行管理员信息并创建 Admin 对象
     */
    public static List<Admin> readAdminsFromTxt() {
        List<Admin> adminList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ADMIN_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 根据逗号分隔字段
                String username = values[0];
                String password = values[1];

                Admin admin = new Admin(username, password);
                adminList.add(admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return adminList;
    }

    /**
     * @brief 验证管理员登录
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return 登录是否成功
     * @details 比对输入的用户名和密码是否与文件中的管理员信息匹配
     */
    public static boolean validateAdmin(String username, String password) {
        List<Admin> admins = readAdminsFromTxt();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true; // 登录成功
            }
        }
        return false; // 登录失败
    }

    /**
     * @brief 根据用户名删除管理员
     * @param username 要删除的管理员用户名
     * @return 是否成功删除管理员
     * @details 从 Admin.txt 文件中删除指定用户名的管理员信息
     */
    public static boolean deleteAdminByUsername(String username) {
        List<Admin> admins = readAdminsFromTxt(); // 读取当前所有管理员
        boolean found = false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_FILE_PATH))) {
            // 写入表头（可选）
            bw.write("username,password");
            bw.newLine();

            for (Admin admin : admins) {
                if (!admin.getUsername().equals(username)) {
                    // 如果不是要删除的用户，写入文件
                    bw.write(admin.getUsername() + "," + admin.getPassword());
                    bw.newLine();
                } else {
                    found = true; // 找到了要删除的用户
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return found; // 返回是否找到了用户并成功删除
    }
}