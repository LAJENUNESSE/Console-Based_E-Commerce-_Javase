package main.com;

import main.com.model.Good;
import main.com.service.AdminService;
import main.com.service.GoodService;
import main.com.service.UserService;
import main.com.util.TxtUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static AdminService adminService = new AdminService();
    private static GoodService goodService = new GoodService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("欢迎来到电子商城系统");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 管理员登录");
            System.out.println("4. 找回密码"); // 添加找回密码选项
            System.out.println("5. 退出系统");
            System.out.print("请选择操作: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    userService.register();
                    break;
                case 2:
                    if (userService.login()) {
                        userMenu();
                    } else {
                        System.out.println("登录失败，用户名或密码错误。");
                    }
                    break;
                case 3:
                    if (adminService.login()) {
                        adminMenu();
                    } else {
                        System.out.println("管理员登录失败，用户名或密码错误。");
                    }
                    break;
                case 4:
                    userService.forgotPassword(); // 调用找回密码的方法
                    break;
                case 5:
                    System.out.println("感谢使用电子商城系统，再见！");
                    return; // 退出程序
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("用户菜单");
            System.out.println("1. 查看商城");
            System.out.println("2. 查看我的购物车");
            System.out.println("3. 添加商品至购物车");
            System.out.println("4. 结算");
            System.out.println("5. 用户退出");
            System.out.print("请选择操作: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    GoodService.viewGoods(); // 查看所有商品
                    break;

                case 2:
                    userService.viewCart(); // 查看购物车
                    break;

                case 3:
                    GoodService.viewGoods(); // 获取所有商品列表

                    System.out.print("请输入要添加到购物车的商品ID: ");
                    String goodId = scanner.nextLine();

                    System.out.print("请输入购买数量: ");
                    int quantity = scanner.nextInt(); // 获取用户输入的数量
                    scanner.nextLine(); // 清除输入缓冲区

                    userService.addGoodToCart(goodId, quantity); // 添加商品到购物车
                    break;
                case 4:
                    userService.checkout(); // 结算
                    break;
                case 5:
                    return; // 返回主菜单
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("管理员菜单");
            System.out.println("1. 添加商品");
            System.out.println("2. 修改商品");
            System.out.println("3. 删除商品");
            System.out.println("4. 查看商品列表");
            System.out.println("5. 查看用户列表");
            System.out.println("6. 删除用户");
            System.out.println("7. 退出");
            System.out.print("请选择操作: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    goodService.addGood(); // 调用添加商品的方法
                    break;
                case 2:
                    goodService.modifyGood(); // 调用修改商品的方法
                    break;
                case 3:
                    goodService.deleteGood(); // 调用删除商品的方法
                    break;
                case 4:
                    goodService.viewGoods(); // 调用查看商品列表的方法
                    break;
                case 5:
                    adminService.viewUsers(); // 有一个方法查看用户列表
                    break;
                case 6:
                    adminService.deleteUser(); // 调用删除用户的方法
                    break;
                case 7:
                    return; // 返回主菜单
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }
}
/**
 * Console-Based_E_Commerce_Javase/
 * │
 * ├── src/
 * │    ├── main/
 * │    │    ├── com/
 * │    │    │   ├── model/
 * │    │    │   │   ├── User.java          // 用户类
 * │    │    │   │   ├── Good.java          // 商品类
 * │    │    │   │   └── Admin.java         // 管理员类
 * │    │    │   ├── service/
 * │    │    │   │   ├── UserService.java   // 用户服务类
 * │    │    │   │   ├── GoodService.java   // 商品服务类
 * │    │    │   │   └── AdminService.java  // 管理员服务类
 * │    │    │   ├── util/
 * │    │    │   │   └── TxtUtil.java     // 处理Excel文件的工具类
 * │    │    │   └── Main.java               // 主程序入口
 * │    │    └── resources/
 * │    │           └── goods.xlsx                       // 存储商品信息的Excel文件
 * │    └── test/
 * │          └── com/
 * │              └── service/
 * │                     └── GoodServiceTest.java // 商品服务测试类
 * │
 * └── README.md                                    // 项目说明文件
 */