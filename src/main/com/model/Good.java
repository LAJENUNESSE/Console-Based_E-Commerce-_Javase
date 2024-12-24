package main.com.model;

import main.com.util.TxtUtil;

public class Good {
    private String id;          // 商品ID（产品的唯一标识符）
    private String name;        // 商品名称
    private String description; // 商品描述
    private double price;       // 商品价格
    private int stock;          // 商品库存数量
    private String category;     // 商品类别（如电子产品、服装等）

    // 构造函数
    public Good(String id, String name, String description, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // 获取商品ID
    public String getId() {
        return id;
    }

    // 设置商品ID
    public void setId(String id) {
        this.id = id;
    }

    // 获取商品名称
    public String getName() {
        return name;
    }

    // 设置商品名称
    public void setName(String name) {
        this.name = name;
    }

    // 获取商品描述
    public String getDescription() {
        return description;
    }

    // 设置商品描述
    public void setDescription(String description) {
        this.description = description;
    }

    // 获取商品价格
    public double getPrice() {
        return price;
    }

    // 设置商品价格
    public void setPrice(double price) {
        this.price = price;
    }

    // 获取商品库存
    public int getStock() {
        return stock;
    }

    // 设置商品库存
    public void setStock(int stock) {
        this.stock = stock;
    }

    // 获取商品类别
    public String getCategory() {
        return category;
    }

    // 设置商品类别
    public void setCategory(String category) {
        this.category = category;
    }

    // 添加方法以减少库存
    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity; // 减少库存
            TxtUtil.updateGoodInTxt(this); // 更新文件中的商品信息
        } else {
            System.out.println("库存不足，无法减少该数量。");
        }
    }
    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                '}';
    }
}