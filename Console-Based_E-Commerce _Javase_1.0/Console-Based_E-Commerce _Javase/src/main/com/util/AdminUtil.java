package main.com.util;

import main.com.model.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminUtil {

    private static final String ADMIN_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\Admin.txt";

    // 从TXT文件读取管理员信息
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

    // 验证管理员登录
    public static boolean validateAdmin(String username, String password) {
        List<Admin> admins = readAdminsFromTxt();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return true; // 登录成功
            }
        }
        return false; // 登录失败
    }

    // 根据用户名删除管理员
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