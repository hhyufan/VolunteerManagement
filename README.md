
# Volunteer Management System

这是一个基于 Vite + React 的前端和 Java Servlet 后端的志愿者管理系统。该项目使用了 Axios 进行 HTTP 请求，Font Awesome 图标库用于 UI 图标展示，后端使用了 MySQL 数据库，通过 JDBC 进行连接，Maven 管理项目依赖，Tomcat 作为应用服务器，Lombok 简化 Java 代码，Log4j 用于日志记录。

## 项目结构

- **前端**：
  - Vite + React
  - Axios 进行 API 调用
  - Font Awesome 图标库

- **后端**：
  - Java Servlet
  - Maven 管理依赖
  - Tomcat 作为服务器
  - JDBC 连接 MySQL
  - Lombok 简化 Java 代码
  - Log4j 进行日志记录

## 功能特性

- 查看志愿者列表
- 添加新志愿者
- 编辑志愿者信息
- 删除志愿者

## 前端设置

### 安装依赖

确保您已经安装了 Node.js 和 npm。然后在项目的前端目录下运行：

```bash
npm install
```

### 运行开发服务器

在项目的前端目录下运行以下命令启动开发服务器：

```bash
npm run dev
```

### 构建项目

运行以下命令构建项目：

```bash
npm run build
```

## 后端设置

### 环境要求

- Java 8 或更高版本
- Apache Maven
- MySQL 数据库
- Tomcat 服务器

### 数据库设置

1. 创建数据库和表：

```sql
CREATE DATABASE volunteer CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE volunteer;
CREATE TABLE Volunteer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);
```

2. 插入初始数据：

```sql
INSERT INTO Volunteer (name, email, phone) VALUES 
('张三', 'zhangsan@example.com', '13800138000'), 
('李四', 'lisi@example.com', '13900139000'), 
('王五', 'wangwu@example.com', '13700137000');
```

### 配置文件

在 `src/main/resources` 中创建 `db.properties` 文件，配置数据库连接信息：

```properties
db.url=jdbc:mysql://localhost:3306/volunteer?useUnicode=true&characterEncoding=utf8mb4
db.username=your_username
db.password=your_password
db.driver=com.mysql.cj.jdbc.Driver
```

### 编译和部署

使用 Maven 编译项目：

```bash
mvn clean install
```

将生成的 WAR 文件部署到 Tomcat 的 `webapps` 目录。

### 启动服务器

启动 Tomcat 服务器，访问 `http://localhost:8080/` 查看应用。

## 日志记录

项目使用 Log4j 进行日志记录。确保在 `src/main/resources` 中有 `log4j2.xml` 配置文件，配置日志输出。

## 注意事项

- **数据库配置**：确保 `db.properties` 文件中的数据库连接信息正确。
- **编码**：确保所有文件使用 UTF-8 编码，以避免字符显示问题。
- **依赖管理**：确保 Maven 和 npm 依赖正确安装。
- **日志配置**：确保 Log4j 配置文件存在并正确配置。

## 许可证

本项目使用 MIT 许可证。详情请参阅 `LICENSE` 文件。

---

### 开发者指南

#### 前端开发

- **组件开发**：前端组件位于 `src/components` 目录下，确保新组件的命名和风格一致。
- **状态管理**：使用 React 的 `useState` 和 `useEffect` 钩子进行状态管理。
- **样式**：CSS 样式文件位于 `src/styles` 目录下，可以使用 CSS-in-JS 或 CSS 模块。

#### 后端开发

- **Servlet**：Servlet 文件位于 `src/main/java/com/example/servlet` 目录下，确保每个 Servlet 的 URL 映射正确。
- **数据访问**：使用 JDBC 连接数据库，DAO（Data Access Object）层位于 `src/main/java/com/comexample/dao` 目录下。

---

### 常见问题解决

1. **数据库连接失败**：检查 `db.properties` 文件的配置是否正确，以及是否执行resource下的`InitVolunteerData.sql`文件。并确保 MySQL 服务，数据库驱动正在运行。
2. **前端编译失败**：确保 Node.js 和 npm 版本兼容，检查 `package.json` 中的依赖是否正确安装。
3. **Tomcat 启动失败**：检查 Tomcat 端口是否被占用，检查 WAR 文件是否部署正确。
