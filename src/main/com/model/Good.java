/**
 * @file Good.java
 * @brief 商品实体类，定义商品的基本属性和操作
 * @package main.com.model
 */
package main.com.model;

import main.com.util.TxtUtil;

/**
 * @class Good
 * @brief 商品实体类，表示电子商城中的商品信息
 * @details 包含商品的基本属性和库存管理方法
 */
public class Good {
    /** @brief 商品ID，产品的唯一标识符 */
    private String id;          // 商品ID（产品的唯一标识符）
    /** @brief 商品名称 */
    private String name;        // 商品名称
    /** @brief 商品描述 */
    private String description; // 商品描述
    /** @brief 商品价格 */
    private double price;       // 商品价格
    /** @brief 商品库存数量 */
    private int stock;          // 商品库存数量
    /** @brief 商品类别（如电子产品、服装等） */
    private String category;     // 商品类别（如电子产品、服装等）


    /**
     * @brief 构造函数
     * @param id 商品ID
     * @param name 商品名称
     * @param description 商品描述
     * @param price 商品价格
     * @param stock 商品库存数量
     * @param category 商品类别
     */
    public Good(String id, String name, String description, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    /**
     * @brief 获取商品ID
     * @return 商品ID
     */
    public String getId() {
        return id;
    }

    /**
     * @brief 设置商品ID
     * @param id 新的商品ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @brief 获取商品名称
     * @return 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * @brief 设置商品名称
     * @param name 新的商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief 获取商品描述
     * @return 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @brief 设置商品描述
     * @param description 新的商品描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @brief 获取商品价格
     * @return 商品价格
     */
    public double getPrice() {
        return price;
    }

    /**
     * @brief 设置商品价格
     * @param price 新的商品价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @brief 获取商品库存数量
     * @return 商品库存数量
     */
    public int getStock() {
        return stock;
    }

    /**
     * @brief 设置商品库存数量
     * @param stock 新的库存数量
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @brief 获取商品类别
     * @return 商品类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * @brief 设置商品类别
     * @param category 新的商品类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @brief 减少商品库存
     * @param quantity 要减少的库存数量
     * @details 检查库存是否充足，如果充足则减少库存并更新文件
     */
    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity; // 减少库存
            TxtUtil.updateGoodInTxt(this); // 更新文件中的商品信息
        } else {
            System.out.println("库存不足，无法减少该数量。");
        }
    }

    /**
     * @brief 重写toString方法，提供商品信息的字符串表示
     * @return 商品信息的字符串描述
     */
    @Override
    public String toString() {
        return "商品{" +
                "id='" + id + '\'' +
                ", 名称='" + name + '\'' +
                ", 描述='" + description + '\'' +
                ", 价格=" + price +
                ", 库存=" + stock +
                ", 商品类别='" + category + '\'' +
                '}';
    }
}