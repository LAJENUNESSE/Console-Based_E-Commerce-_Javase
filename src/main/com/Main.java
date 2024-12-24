/**
 * @file Main.java
 * @brief 电子商城系统的主入口类
 * @package main.com
 */
package main.com;

import main.com.model.Good;
import main.com.service.AdminService;
import main.com.service.GoodService;
import main.com.service.UserService;
import main.com.util.TxtUtil;

import java.util.List;
import java.util.Scanner;

/**
 * @class Main
 * @brief 电子商城系统的主类，负责用户交互和菜单管理
 * @details 提供用户、管理员登录和各种功能菜单的入口
 */
public class Main {

    /** @brief 系统输入扫描器 */
    private static Scanner scanner = new Scanner(System.in);

    /** @brief 用户服务对象 */
    private static UserService userService = new UserService();

    /** @brief 管理员服务对象 */
    private static AdminService adminService = new AdminService();

    /** @brief 商品服务对象 */
    private static GoodService goodService = new GoodService();

    /**
     * @brief 主程序入口方法
     * @param args 命令行参数
     * @details 显示主菜单，提供用户注册、登录、管理员登录等功能
     */
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

    /**
     * @brief 用户菜单方法
     * @details 提供用户可用的功能，包括查看商品、购物车操作、结算等
     */
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

    /**
     * @brief 管理员菜单方法
     * @details 提供管理员可用的功能，包括商品管理、用户管理等
     */
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
 * Console-Based_E-Commerce_Javase
 * ├──.idea
 * ├── out
 * ├── src
 * │   ├── main
 * │   │   └── com
 * │   │       ├── model # 数据模型类
 * │   │       │    ├── AdminService
 * │   │       │    ├── GoodService
 * │   │       │    └── UserService
 * │   │       ├── service # 服务类，处理业务逻辑
 * │   │       │    ├── AdminService
 * │   │       │    ├── GoodService
 * │   │       │    └── UserService
 * │   │       └── util # 工具类
 * │   │       │    ├── AdminUtil
 * │   │       │    ├── TxtUtil
 * │   │       │    └── UserUtil
 * │   │       └── Main # 应用入口
 * │   └── resources # 资源文件
 * │       ├── Admin.txt # 管理员相关数据
 * │       ├── goods.txt # 商品信息
 * │       ├── purchases.txt # 购买记录
 * │       └── users.txt # 用户信息
 * └── test
 *     └── com
 *         └── service # 服务层测试
 *              └── GoodServiceTest
 *
 */