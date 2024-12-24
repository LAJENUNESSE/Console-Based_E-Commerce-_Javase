/**
 * @file AdminService.java
 * @brief 管理员服务类，提供管理员相关的操作方法
 * @package main.com.service
 */  package main.com.service;

import main.com.model.Admin;
import main.com.model.Good;
import main.com.model.User;
import main.com.util.AdminUtil;
import main.com.util.TxtUtil;
import main.com.util.UserUtil;

import java.util.List;
import java.util.Scanner;

/**
 * @class AdminService
 * @brief 管理员服务类，提供管理员的登录和系统管理功能
 * @details 包含商品管理、用户管理等管理员操作
 */
public class AdminService {
    /** @brief 系统输入扫描器 */
    private Scanner scanner = new Scanner(System.in);

    /**
     * @brief 管理员登录方法
     * @return 登录是否成功
     * @details 提示输入用户名和密码，并验证管理员身份
     */
    public boolean login() {
        System.out.print("请输入管理员用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入管理员密码: ");
        String password = scanner.nextLine();

        return AdminUtil.validateAdmin(username, password);
    }

    /**
     * @brief 添加商品方法
     * @details 通过控制台输入商品信息，并将商品保存到系统
     */
    public void addGood() {
        System.out.print("请输入商品ID: ");
        String id = scanner.nextLine();
        System.out.print("请输入商品名称: ");
        String name = scanner.nextLine();
        System.out.print("请输入商品描述: ");
        String description = scanner.nextLine();
        System.out.print("请输入商品价格: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("请输入商品库存: ");
        int stock = Integer.parseInt(scanner.nextLine());
        System.out.print("请输入商品类别: ");
        String category = scanner.nextLine();

        Good good = new Good(id, name, description, price, stock, category);
        TxtUtil.addGoodToTxt(good); // 将商品添加到TXT文件
        System.out.println("商品添加成功！");
    }

    /**
     * @brief 修改商品信息方法
     * @details 根据商品ID查找并更新商品信息
     */
    public void modifyGood() {
        System.out.print("请输入要修改的商品ID: ");
        String id = scanner.nextLine();
        Good good = TxtUtil.getGoodById(id); // 根据ID获取商品
        if (good != null) {
            System.out.print("请输入新的商品名称 (当前: " + good.getName() + "): ");
            String name = scanner.nextLine();
            System.out.print("请输入新的商品描述 (当前: " + good.getDescription() + "): ");
            String description = scanner.nextLine();
            System.out.print("请输入新的商品价格 (当前: " + good.getPrice() + "): ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("请输入新的商品库存 (当前: " + good.getStock() + "): ");
            int stock = Integer.parseInt(scanner.nextLine());
            System.out.print("请输入新的商品类别 (当前: " + good.getCategory() + "): ");
            String category = scanner.nextLine();

            good = new Good(id, name, description, price, stock, category);
            TxtUtil.updateGoodInTxt(good); // 更新商品信息
            System.out.println("商品修改成功！");
        } else {
            System.out.println("未找到该商品。");
        }
    }

    /**
     * @brief 删除商品方法
     * @details 根据商品ID从系统中删除商品
     */
    public void deleteGood() {
        System.out.print("请输入要删除的商品ID: ");
        String id = scanner.nextLine();
        TxtUtil.deleteGoodFromTxt(id); // 从TXT文件中删除商品
        System.out.println("商品删除成功！");
    }

    /**
     * @brief 删除商品方法
     * @details 根据商品ID从系统中删除商品
     */
    public static void viewGoods() {
        // 确保商品数据已经加载
        TxtUtil.loadGoods(); // 重新加载商品列表

        List<Good> goods = TxtUtil.getGoodsList(); // 获取静态商品列表
        if (goods.isEmpty()) {
            System.out.println("当前没有商品信息。");
        } else {
            for (Good good : goods) {
                System.out.println(good);
            }
        }
    }

    /**
     * @brief 查看用户列表方法
     * @details 读取并显示系统中的所有用户
     */
    public void viewUsers() {
        List<User> users = UserUtil.readUsersFromTxt(); // 读取所有用户
        if (users.isEmpty()) {
            System.out.println("没有找到任何用户。");
        } else {
            System.out.println("用户列表：");
            for (User user : users) {
                System.out.println(user.getUsername()); // 打印每个用户的用户名
            }
        }
    }

    /**
     * @brief 删除用户方法
     * @details 根据用户名从系统中删除用户
     */
    public void deleteUser() {
        System.out.print("请输入要删除的用户用户名: ");
        String username = scanner.nextLine();

        if (UserUtil.deleteUserByUsername(username)) {
            System.out.println("用户删除成功！");
        } else {
            System.out.println("未找到该用户或删除失败。");
        }
    }
}