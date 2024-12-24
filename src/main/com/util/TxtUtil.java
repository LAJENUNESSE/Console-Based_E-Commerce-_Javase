/**
 * @file TxtUtil.java
 * @brief 提供文本文件操作工具类，用于商品和购买记录的管理
 * @package main.com.util
 */
package main.com.util;

import main.com.model.Good;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @class TxtUtil
 * @brief 文本文件工具类，处理商品和购买记录的读取、写入和管理
 * @details 提供商品文件和购买记录文件的各种操作方法
 */
public class TxtUtil {
    /** @brief 商品文件路径 */
    private static final String GOODS_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\goods.txt";
    /** @brief 购买记录文件路径 */
    private static final String PURCHASES_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\purchases.txt"; // 用户购买记录文件路径
    /** @brief 商品列表，用于缓存已加载的商品 */
    private static List<Good> goodsList = new ArrayList<>();

    /**
     * @brief 加载商品列表
     * @details 从文本文件中读取商品信息并加载到 goodsList 中
     */
    public static void loadGoods() {
        goodsList = readGoodsFromTxt();
    }

    /**
     * @brief 从文本文件读取商品信息
     * @return 包含所有商品的列表
     * @details 读取 goods.txt 文件，解析每行商品信息并按价格排序
     */
    private static List<Good> readGoodsFromTxt() {
        List<Good> goods = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(GOODS_FILE_PATH))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String name = values[1];
                String description = values[2];
                double price = Double.parseDouble(values[3]);
                int stock = Integer.parseInt(values[4]);
                String category = values[5];

                Good good = new Good(id, name, description, price, stock, category);
                goods.add(good);
            }
            goods.sort(Comparator.comparingDouble(Good::getPrice));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goods;
    }

    /**
     * @brief 根据商品ID获取商品对象
     * @param id 商品ID
     * @return 匹配的商品对象，如果未找到则返回 null
     */
    public static Good getGoodById(String id) {
        for (Good good : goodsList) {
            if (good.getId().equals(id)) {
                return good;
            }
        }
        return null;
    }

    /**
     * @brief 向文本文件添加新商品
     * @param good 要添加的商品对象
     * @details 将新商品信息追加到 goods.txt 文件并重新加载商品列表
     */
    public static void addGoodToTxt(Good good) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GOODS_FILE_PATH, true))) {
            bw.write(good.getId() + "," + good.getName() + "," + good.getDescription() + "," + good.getPrice() + "," + good.getStock() + "," + good.getCategory());
            bw.newLine();
            loadGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief 更新文本文件中的商品信息
     * @param updatedGood 更新后的商品对象
     * @details 替换 goods.txt 文件中指定 ID 的商品信息并重新加载商品列表
     */
    public static void updateGoodInTxt(Good updatedGood) {
        List<Good> goods = readGoodsFromTxt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GOODS_FILE_PATH))) {
            bw.write("id,name,description,price,stock,category");
            bw.newLine();
            for (Good good : goods) {
                if (good.getId().equals(updatedGood.getId())) {
                    bw.write(updatedGood.getId() + "," + updatedGood.getName() + "," + updatedGood.getDescription() + "," + updatedGood.getPrice() + "," + updatedGood.getStock() + "," + updatedGood.getCategory());
                } else {
                    bw.write(good.getId() + "," + good.getName() + "," + good.getDescription() + "," + good.getPrice() + "," + good.getStock() + "," + good.getCategory());
                }
                bw.newLine();
            }
            loadGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief 从文本文件删除指定商品
     * @param id 要删除的商品ID
     * @details 从 goods.txt 文件中移除指定 ID 的商品并重新加载商品列表
     */
    public static void deleteGoodFromTxt(String id) {
        List<Good> goods = readGoodsFromTxt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GOODS_FILE_PATH))) {
            bw.write("id,name,description,price,stock,category");
            bw.newLine();
            for (Good good : goods) {
                if (!good.getId().equals(id)) {
                    bw.write(good.getId() + "," + good.getName() + "," + good.getDescription() + "," + good.getPrice() + "," + good.getStock() + "," + good.getCategory());
                    bw.newLine();
                }
            }
            loadGoods();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief 记录用户购买的商品
     * @param username 用户名
     * @param good 购买的商品对象
     * @param quantity 购买数量
     * @details 将购买记录追加到 purchases.txt 文件
     */
    public static void addPurchasedGoodForUser(String username, Good good, int quantity) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PURCHASES_FILE_PATH, true))) {
            bw.write(username + "," + good.getId() + "," + quantity);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief 获取指定用户的购买记录
     * @param username 用户名
     * @return 用户购买商品的详细信息列表
     * @details 从 purchases.txt 文件读取指定用户的购买记录
     */
    public static List<String> getPurchasedGoodsByUser(String username) {
        List<String> purchasedGoods = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PURCHASES_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 3 && values[0].equals(username)) {
                    String goodId = values[1];
                    int quantity = Integer.parseInt(values[2]);
                    purchasedGoods.add("商品ID: " + goodId + ", 数量: " + quantity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return purchasedGoods;
    }

    /**
     * @brief 初始化方法，加载商品列表
     * @details 在系统启动时调用，确保商品列表被加载
     */
    public static void initialize() {
        loadGoods();
    }

    /**
     * @brief 获取商品列表的副本
     * @return 商品列表的深拷贝
     * @details 返回当前缓存的商品列表，防止直接修改原列表
     */
    public static List<Good> getGoodsList() {
        return new ArrayList<>(goodsList);
    }
}