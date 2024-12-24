# Console-Based E-Commerce Application

这是一个基于控制台的电子商务应用程序，它允许用户浏览商品、管理购物车以及进行交易。该应用程序实现了基本的用户和商品管理功能。

## 项目结构  
Console-Based_E-Commerce_Javase  
├── .idea  
├── out  
├── src  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── main  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── com  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── model # 数据模型类  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── AdminService  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── GoodService  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── UserService  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── service # 服务类，处理业务逻辑  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── AdminService  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── GoodService  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── UserService  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── util # 工具类  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── AdminUtil  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── TxtUtil  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── UserUtil  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; │&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── Main # 应用入口  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── resources # 资源文件  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── Admin.txt # 管理员相关数据  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── goods.txt # 商品信息  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ├── purchases.txt # 购买记录  
│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; └── users.txt # 用户信息  
└── test  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── com  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── service # 服务层测试  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── GoodServiceTest

## 功能特性

- **用户管理**：注册、登录和验证用户身份。
- **商品管理**：浏览商品列表、添加商品、查看商品详情。
- **购物车功能**：添加商品到购物车、查看购物车内容和删除购物车中的商品。
- **交易功能**：用户可以购买购物车中的商品并查看购买记录。

## 环境要求

- JDK 8 或更高版本
- Maven（可选，用于依赖管理）

## 安装与运行

1. **克隆项目**

   ```bash  
   git clone https://github.com/LAJENUNESSE/Console-Based_E-Commerce-_Javase.git  
   cd Console-Based_E-Commerce_Javase  

2. **编译项目**

使用 Maven 构建项目（如果使用 Maven 管理依赖）：


   ```bash
   mvn install  
   ```

3. **运行应用程序**

运行包含 main 方法的主类（例如 Main）：
```bash
java -cp target/Console-Based_E-Commerce_Javase-1.0-SNAPSHOT.jar com.Main 
 ```

**使用说明**  
启动应用程序后，用户可以根据提示选择操作。在商品列表中，用户可以输入商品ID以将商品添加到购物车。用户还可以查看当前购物车的内容和已购买商品的列表。


**代码示例**  
以下是如何加载用户信息的示例：

```bash
public static void Load_Users(ArrayList<Users> users, InputStream bufis) throws IOException {  
    BufferedReader reader = new BufferedReader(new InputStreamReader(bufis));  
    String line;  
    while ((line = reader.readLine()) != null) {  
        String[] userInfo = line.split(",");  
        Users u1 = new Users(userInfo[0], userInfo[1], userInfo[2]);  
        u1.setShoping_Car(new ArrayList<Goods>());  
        users.add(u1);  
    }  
} 
```

**问题与解决方案**  
- **用户ID丢失**：如果发现用户ID在加载时丢失，请确保输入数据格式正确并包含所有必要字段。
- **购物车为空**：确保在添加商品到购物车后，用户能够调用相应的方法来检查购物车内容。

**贡献**  
  欢迎贡献代码或提出问题。请提交 PR 或在问题跟踪中报告 bug。

