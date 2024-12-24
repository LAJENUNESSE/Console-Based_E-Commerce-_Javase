package main.com.model;

import main.com.util.AdminUtil;
import main.com.util.TxtUtil;

import java.util.List;
import java.util.Scanner;

public class Admin {

    private String username;
    private String password;
    private Scanner scanner = new Scanner(System.in);

    // 构造函数
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 获取用户名
    public String getUsername() {
        return username;
    }

    // 获取密码
    public String getPassword() {
        return password;
    }

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
        TxtUtil.addGoodToTxt(good); // 假设有一个方法将商品添加到TXT文件
        System.out.println("商品添加成功！");
    }

    // 修改商品
    public void modifyGood() {
        System.out.print("请输入要修改的商品ID: ");
        String id = scanner.nextLine();
        Good good = TxtUtil.getGoodById(id); // 假设有一个方法根据ID获取商品
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
            TxtUtil.updateGoodInTxt(good); // 假设有一个方法更新商品信息
            System.out.println("商品修改成功！");
        } else {
            System.out.println("未找到该商品。");
        }
    }

    // 删除商品
    public void deleteGood() {
        System.out.print("请输入要删除的商品ID: ");
        String id = scanner.nextLine();
        TxtUtil.deleteGoodFromTxt(id); // 假设有一个方法从TXT文件中删除商品
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
        List<Admin> admins = AdminUtil.readAdminsFromTxt(); // 假设有一个方法读取所有管理员
        for (Admin admin : admins) {
            System.out.println(admin.getUsername());
        }
    }
}