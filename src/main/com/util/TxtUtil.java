package main.com.util;

import main.com.model.Good;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtUtil {

    private static final String GOODS_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\goods.txt";
    private static final String PURCHASES_FILE_PATH = "C:\\Users\\liu69\\Desktop\\Console-Based_E-Commerce _Javase_1.0\\Console-Based_E-Commerce _Javase\\src\\main\\resources\\purchases.txt"; // 用户购买记录文件路径
    private static List<Good> goodsList = new ArrayList<>(); // 静态商品列表

    // 加载商品数据到静态列表
    public static void loadGoods() {
        goodsList = readGoodsFromTxt(); // 读取商品并填充静态列表
    }

    // 从TXT文件读取商品数据
    private static List<Good> readGoodsFromTxt() {
        List<Good> goods = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(GOODS_FILE_PATH))) {
            String line;
            br.readLine(); // 跳过表头
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return goods;
    }

    // 根据ID获取商品
    public static Good getGoodById(String id) {
        for (Good good : goodsList) {
            if (good.getId().equals(id)) {
                return good; // 返回静态列表中的商品对象
            }
        }
        return null; // 未找到商品
    }

    // 添加商品到TXT文件
    public static void addGoodToTxt(Good good) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GOODS_FILE_PATH, true))) {
            bw.write(good.getId() + "," + good.getName() + "," + good.getDescription() + "," + good.getPrice() + "," + good.getStock() + "," + good.getCategory());
            bw.newLine();
            loadGoods(); // 更新静态商品列表
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 更新商品信息
    public static void updateGoodInTxt(Good updatedGood) {
        List<Good> goods = readGoodsFromTxt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GOODS_FILE_PATH))) {
            bw.write("id,name,description,price,stock,category"); // 写入表头
            bw.newLine();
            for (Good good : goods) {
                if (good.getId().equals(updatedGood.getId())) {
                    bw.write(updatedGood.getId() + "," + updatedGood.getName() + "," + updatedGood.getDescription() + "," + updatedGood.getPrice() + "," + updatedGood.getStock() + "," + updatedGood.getCategory());
                } else {
                    bw.write(good.getId() + "," + good.getName() + "," + good.getDescription() + "," + good.getPrice() + "," + good.getStock() + "," + good.getCategory());
                }
                bw.newLine();
            }
            loadGoods(); // 更新静态商品列表
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从TXT文件中删除商品
    public static void deleteGoodFromTxt(String id) {
        List<Good> goods = readGoodsFromTxt();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(GOODS_FILE_PATH))) {
            bw.write("id,name,description,price,stock,category"); // 写入表头
            bw.newLine();
            for (Good good : goods) {
                if (!good.getId().equals(id)) {
                    bw.write(good.getId() + "," + good.getName() + "," + good.getDescription() + "," + good.getPrice() + "," + good.getStock() + "," + good.getCategory());
                    bw.newLine();
                }
            }
            loadGoods(); // 更新静态商品列表
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 记录用户购买的商品和数量
    public static void addPurchasedGoodForUser(String username, Good good, int quantity) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PURCHASES_FILE_PATH, true))) {
            bw.write(username + "," + good.getId() + "," + quantity); // 假设记录格式为 "username,goodId,quantity"
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据用户名获取用户购买的商品及数量
    public static List<String> getPurchasedGoodsByUser(String username) {
        List<String> purchasedGoods = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PURCHASES_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // 格式为 "username,goodId,quantity"
                if (values.length >= 3 && values[0].equals(username)) {
                    String goodId = values[1];
                    int quantity = Integer.parseInt(values[2]);
                    purchasedGoods.add("商品ID: " + goodId + ", 数量: " + quantity); // 记录商品ID和对应的数量
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return purchasedGoods;
    }

    // 在应用程序启动时加载商品数据
    public static void initialize() {
        loadGoods(); // 初始化时加载商品数据
    }

    // 返回静态商品列表
    public static List<Good> getGoodsList() {
        return new ArrayList<>(goodsList); // 返回一个商品列表的副本，以避免外部修改
    }
}