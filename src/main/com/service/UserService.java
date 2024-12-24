package main.com.service;

import main.com.model.Good;
import main.com.model.User;
import main.com.util.TxtUtil;
import main.com.util.UserUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private Scanner scanner = new Scanner(System.in);
    private String loggedInUsername; // 当前登录用户的用户名
    private List<CartItem> cart; // 用于存储购物车中的商品和数量

    public UserService() {
        cart = new ArrayList<>(); // 初始化购物车
    }

    // 用户注册
    public void register() {
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();
        System.out.print("请输入电子邮件: ");
        String email = scanner.nextLine();
        System.out.print("请输入电话号码: ");
        String phone = scanner.nextLine();

        User user = new User(username, password, email, phone);
        UserUtil.addUserToTxt(user);
        System.out.println("注册成功！");
    }

    // 用户登录
    public boolean login() {
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        if (UserUtil.validateUser(username, password)) {
            loggedInUsername = username; // 保存登录的用户名
            return true; // 登录成功
        }
        return false; // 登录失败
    }

    public String getLoggedInUsername() {
        return loggedInUsername; // 获取当前登录的用户名
    }

    // 查看用户购买的商品
    public void viewPurchasedGoods(String username) {
        List<String> purchasedGoods = TxtUtil.getPurchasedGoodsByUser(username);
        if (purchasedGoods.isEmpty()) {
            System.out.println("您尚未购买任何商品。");
        } else {
            System.out.println("您购买的商品列表：");
            for (String goodInfo : purchasedGoods) {
                System.out.println(goodInfo);
            }
        }
    }

    // 添加商品到购物车
    public void addGoodToCart(String goodId, int quantity) {
        // 根据商品ID获取商品
        Good good = TxtUtil.getGoodById(goodId);

        if (good != null) {
            if (quantity > 0) { // 检查数量有效性
                int availableStock = good.getStock(); // 获取可用库存
                if (availableStock >= quantity) { // 检查库存是否足够
                    cart.add(new CartItem(good, quantity)); // 添加商品到购物车
                    good.reduceStock(quantity); // 减少库存数量并更新文件
                    System.out.println("成功将 " + quantity + " 件商品添加到购物车: " + good.getName());
                } else {
                    System.out.println("库存不足，当前可用数量: " + availableStock);
                }
            } else {
                System.out.println("购买数量必须大于0。");
            }
        } else {
            System.out.println("未找到该商品，请检查商品ID。");
        }
    }

    // 查看购物车中的商品
    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("购物车为空。");
        } else {
            System.out.println("购物车中的商品：");
            for (CartItem item : cart) {
                System.out.println(item.getGood().getName() + " - 数量: " + item.getQuantity());
            }
        }
    }

    // 结算
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("购物车为空，无法结算。");
            return;
        }

        // 显示购物车中的商品信息
        System.out.println("购物车中的商品：");
        double totalPrice = 0; // 初始化总价格
        for (CartItem item : cart) {
            double itemTotalPrice = item.getGood().getPrice() * item.getQuantity(); // 每个商品的总价格
            totalPrice += itemTotalPrice; // 累加到总价格
            System.out.println(item.getGood().getName() + " - 数量: " + item.getQuantity() + ", 小计: " + itemTotalPrice + "元");
        }

        System.out.println("总价格: " + totalPrice + "元"); // 显示总价格

        // 计算折扣
        double discountedPrice;
        if (totalPrice < 1000) {
            double discountRate = 0.7 + (Math.random() * 0.2); // 随机生成 0.7 到 0.9 的折扣率
            discountedPrice = totalPrice * discountRate;
            System.out.printf("您享受的随机折扣率为: %.2f%% \n", discountRate * 100);
        } else {
            int discountCount = (int) (totalPrice / 1000);
            double discountRate = 1 - (0.02 * discountCount); // 每超过1000元减2%
            discountedPrice = totalPrice * discountRate;
            System.out.printf("您享受的折扣率为: %.2f%% \n", discountRate * 100);
        }

        System.out.printf("折后总价格: %.2f元\n", discountedPrice); // 显示折扣后的总价格

        System.out.print("您确认要结算吗？(y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            for (CartItem item : cart) {
                TxtUtil.addPurchasedGoodForUser(loggedInUsername, item.getGood(), item.getQuantity());
            }
            System.out.println("结算成功！感谢您的购买。");
            cart.clear(); // 结算后清空购物车
        } else {
            System.out.println("结算已取消。");
        }
    }

    // 找回密码
    public void forgotPassword() {
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();

        // 获取用户的信息
        User user = UserUtil.getUserByUsername(username);
        if (user == null) {
            System.out.println("未找到该用户。");
            return;
        }

        System.out.println("请确认您的身份：");
        System.out.print("输入注册时使用的电子邮件: ");
        String email = scanner.nextLine();
        System.out.print("输入注册时使用的电话号码: ");
        String phone = scanner.nextLine();

        // 验证用户输入的电子邮箱和手机号码
        if (email.equals(user.getEmail()) && phone.equals(user.getPhone())) {
            System.out.println("身份验证成功，您可以重置您的密码。");
            System.out.print("请输入您的新密码: ");
            String newPassword = scanner.nextLine();

            user.setPassword(newPassword);
            UserUtil.updateUserPassword(user);
            System.out.println("您的密码已成功重置！");
        } else {
            System.out.println("身份验证失败，请检查您输入的信息。");
        }
    }

    // 内部类 CartItem，用于存储购物车中的商品和数量
    private class CartItem {
        private Good good;
        private int quantity;

        public CartItem(Good good, int quantity) {
            this.good = good;
            this.quantity = quantity;
        }

        public Good getGood() {
            return good;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}