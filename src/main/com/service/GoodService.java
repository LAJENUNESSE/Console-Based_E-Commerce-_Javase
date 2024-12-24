/**
 * @file GoodService.java
 * @brief 商品服务类，提供商品管理相关的操作方法
 * @package main.com.service
 */
package main.com.service;

import main.com.model.Good;
import main.com.util.TxtUtil;

import java.util.List;
import java.util.Scanner;

/**
 * @class GoodService
 * @brief 商品服务类，提供商品的浏览、添加、修改和删除功能
 * @details 处理商品相关的业务逻辑和用户交互
 */
public class GoodService {
    /** @brief 系统输入扫描器 */
    private Scanner scanner = new Scanner(System.in);

    /**
     * @brief 查看所有商品方法
     * @details 加载并显示系统中的所有商品信息
     * @note 静态方法，可直接调用
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
     * @brief 添加商品方法
     * @details 通过控制台交互输入商品信息，并将商品保存到系统
     * @throws NumberFormatException 当价格或库存输入非法数字时
     */
    public void addGood() {
        try {
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

            // 数据验证
            validateGoodData(id, name, price, stock);

            Good good = new Good(id, name, description, price, stock, category);
            TxtUtil.addGoodToTxt(good); // 将商品添加到TXT文件
            System.out.println("商品添加成功！");
        } catch (NumberFormatException e) {
            System.out.println("错误：价格或库存输入非法，请输入有效的数字。");
        }
    }

    /**
     * @brief 修改商品信息方法
     * @details 根据商品ID查找并更新商品信息
     * @throws NumberFormatException 当价格或库存输入非法数字时
     */
    public void modifyGood() {
        try {
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

                // 数据验证
                validateGoodData(id, name, price, stock);

                good = new Good(id, name, description, price, stock, category);
                TxtUtil.updateGoodInTxt(good); // 更新商品信息
                System.out.println("商品修改成功！");
            } else {
                System.out.println("未找到该商品。");
            }
        } catch (NumberFormatException e) {
            System.out.println("错误：价格或库存输入非法，请输入有效的数字。");
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
     * @brief 商品数据验证方法
     * @param id 商品ID
     * @param name 商品名称
     * @param price 商品价格
     * @param stock 商品库存
     * @throws IllegalArgumentException 当输入的数据不合法时
     */
    private void validateGoodData(String id, String name, double price, int stock) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("商品ID不能为空");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("商品名称不能为空");
        }
        if (price < 0) {
            throw new IllegalArgumentException("商品价格不能为负数");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("商品库存不能为负数");
        }
    }
}