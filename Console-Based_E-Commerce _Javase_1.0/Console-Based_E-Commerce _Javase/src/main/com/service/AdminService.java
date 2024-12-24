package main.com.service;

import main.com.model.Admin;
import main.com.model.Good;
import main.com.model.User;
import main.com.util.AdminUtil;
import main.com.util.TxtUtil;
import main.com.util.UserUtil;

import java.util.List;
import java.util.Scanner;

public class AdminService {
    private Scanner scanner = new Scanner(System.in);

    // 管理员登录
    public boolean login() {
        System.out.print("请输入管理员用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入管理员密码: ");
        String password = scanner.nextLine();

        return AdminUtil.validateAdmin(username, password);
    }

    // 添加商品
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

    // 修改商品
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

    // 删除商品
    public void deleteGood() {
        System.out.print("请输入要删除的商品ID: ");
        String id = scanner.nextLine();
        TxtUtil.deleteGoodFromTxt(id); // 从TXT文件中删除商品
        System.out.println("商品删除成功！");
    }

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

    // 查看用户列表
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

    // 删除用户
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