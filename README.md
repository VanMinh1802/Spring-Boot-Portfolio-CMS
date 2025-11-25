# 🌟 Advanced Personal Portfolio & CMS

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-green)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![Thymeleaf](https://img.shields.io/badge/Frontend-Thymeleaf-lightgreen)
![Bootstrap](https://img.shields.io/badge/UI-Bootstrap_5-purple)

> **Hệ thống Website Portfolio cá nhân tích hợp CMS (Content Management System)** giúp quản lý dự án, bài viết blog, kỹ năng và tương tác với người dùng.

---

## 📸 Screenshots (Demo)

### 1. Trang chủ (Khách xem)
<img src="https://github.com/VanMinh1802/Spring-Boot-Portfolio-CMS/blob/main/user-page.png?raw=true" width="100%" alt=" User Home Page">

### 2. Trang chủ (Admin xem)
<img src="https://github.com/VanMinh1802/Spring-Boot-Portfolio-CMS/blob/main/admin-page.png?raw=true" width="100%" alt=" Admin Home Page">

### 3. Admin Dashboard (Thống kê & Quản trị)
<img src="https://github.com/VanMinh1802/Spring-Boot-Portfolio-CMS/blob/main/dashboard.png?raw=true" width="100%" alt=" Dashboard">

---

## 🚀 Tính năng chính (Key Features)

### 🔒 Phân quyền & Bảo mật (Security)
- **Authentication:** Đăng nhập, Đăng xuất, Ghi nhớ đăng nhập (Remember Me).
- **Authorization:** Phân quyền Admin (truy cập CMS) và User/Guest (chỉ xem public).
- **Encryption:** Mật khẩu được mã hóa chuẩn **BCrypt**.

### 🛠️ Quản trị nội dung (CMS)
- **Quản lý Dự án:** Thêm/Sửa/Xóa dự án, upload ảnh minh họa.
- **Quản lý Blog:** Soạn thảo bài viết với **CKEditor 5** (Rich Text Editor).
- **Quản lý Kỹ năng:** Cập nhật thanh phần trăm kỹ năng.
- **Hộp thư & Bình luận:** Nhận tin nhắn từ khách, duyệt bình luận trước khi hiển thị.

### 📊 Tiện ích & Hiệu năng
- **Dashboard:** Thống kê số liệu, biểu đồ trực quan (Chart.js).
- **Export Excel:** Xuất danh sách tin nhắn ra file Excel.
- **Đa ngôn ngữ (i18n):** Chuyển đổi Tiếng Anh / Tiếng Việt.
- **Giao diện:** Hỗ trợ **Dark Mode / Light Mode**, Responsive Mobile.
- **Performance:** Tích hợp **Caching** để tăng tốc độ tải trang.

### 🌐 RESTful API
- Cung cấp API lấy danh sách dự án cho bên thứ 3 (Mobile App).
- Tài liệu API tự động với **Swagger UI*.

---

## 🛠️ Cài đặt & Chạy dự án (Installation)

### Yêu cầu:
- Java JDK 17+
- Maven
- MySQL 8.0+

### Bước 1: Clone dự án
bash
- git clone https://github.com/VanMinh1802/Spring-Boot-Portfolio-CMS.git

### Bước 2: Cấu hình Database
- Mở MySQL Workbench hoặc HeidiSQL.
- Tạo một database trống tên là db_portfolio:
- SQL: CREATE DATABASE db_portfolio CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
- Tìm file db_portfolio.sql nằm trong thư mục gốc của dự án này.
- Import file đó vào database vừa tạo để có cấu trúc bảng và dữ liệu Admin mặc định.
- Mở file src/main/resources/application.properties.
- Sửa spring.datasource.username và spring.datasource.password thành tài khoản MySQL của bạn.
### Bước 3: Cấu hình Lưu trữ ảnh (Quan trọng)
- Dự án được cấu hình để lưu ảnh upload vào ổ cứng máy tính.
- Tạo thư mục PortfolioData trong ổ D: (Đường dẫn: D:/PortfolioData).
- Nếu bạn dùng Mac/Linux hoặc muốn đổi ổ đĩa, hãy sửa đường dẫn trong file src/main/java/.../config/MvcConfig.java và các Controller tương ứng.
### Bước 4: Chạy ứng dụng
Tại thư mục gốc dự án, chạy lệnh: mvn spring-boot:run

## Hướng dẫn sử dụng
### Truy cập Website:
- Mở trình duyệt: http://localhost:8081
  
### Đăng nhập Admin:
- URL: http://localhost:8081/login
- Username: admin
- Password: P@ss180220

### Đăng nhập User:
- URL: http://localhost:8081/login
- Username: khach
- Password: 123456

### Tài liệu API (Swagger):
- URL: http://localhost:8081/swagger-ui/index.html
