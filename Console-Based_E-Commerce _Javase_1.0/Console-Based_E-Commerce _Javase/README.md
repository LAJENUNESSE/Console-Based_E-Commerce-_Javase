# E-Commerce Application  

这是一个简单的电子商务应用程序，用户可以浏览商品、将商品添加到购物车、查看已购买的商品，以及进行结算。该应用程序实现了用户管理、商品管理和购物车管理的基本功能。  

## 项目结构  

- **Users**: 处理用户的相关信息，如用户名、密码和购物车。  
- **Goods**: 表示商品的类，包含商品信息如ID、名称、描述、价格和库存。  
- **Cart**: 处理购物车功能，允许用户将商品添加到购物车和查看购物车内容。  
- **TxtUtil**: 用于读写商品和用户信息的工具类。  
- **UserService**: 处理用户相关操作的服务类。  

## 功能特性  

- 用户注册与登录  
- 浏览商品列表  
- 将商品添加到购物车  
- 查看购物车内商品  
- 查看已购买商品  
- 商品库存管理  

## 环境要求  

- JDK 8 或更高版本  
- Maven（可选，用于依赖管理）  

## 安装与运行  

1. **克隆项目**  

   ```bash  
   git clone https://github.com/your-username/ecommerce-app.git  
   cd ecommerce-app  
编译项目

使用 Maven 构建项目（如果使用 Maven 管理依赖）：

mvn install  
运行应用程序

运行包含 main 方法的主类（例如 ECommerceApp）：

java -cp target/ecommerce-app-1.0-SNAPSHOT.jar com.yourpackage.ECommerceApp  
使用说明
启动应用程序后，用户可以根据指示选择操作。
在商品列表中，用户可以输入商品ID以将商品添加到购物车。
用户可以查看当前购物车的内容和已购买商品的列表。
代码示例
以下是如何加载用户信息的示例：

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
问题与解决方案
用户ID丢失: 如果发现用户ID在加载时丢失，请确保输入数据格式正确并包含所有必要字段。
购物车为空: 确保在添加商品到购物车后，用户能够调用对应的方法来检查购物车内容。
贡献
欢迎贡献代码或提出问题。请提交 PR 或在问题跟踪中报告bug。

许可证
本项目以 MIT 许可证发布。

感谢您使用本电子商务应用！


### 使用说明  

1. **修改项目链接**：将 `https://github.com/your-username/ecommerce-app.git` 修改为实际的项目链接。  
2. **填充代码示例**：根据需要调整代码示例，添加更多相关代码片段或使用说明。  
3. **补充功能与问题**：根据具体实现认真补充和更新功能特性及遇到的问题与解决方案部分。  

您可以根据项目的实际需求和功能进行调整，确保文档准确反映应用程序的使用和功能。
