/* Note:
- Dự kiến sẽ xử lý ngày tạo tự động lấy ngày bằng câu lệnh insert sql.
- Chưa rõ có cần bảng thống kê không và nếu có thì nó sẽ chứa những trường gì (bảng thống kê doanh thu)
- Nếu có bảng thống kê thì chỉ cần 1 hay nên có nhiều bảng cho nhiều loại thống kê? (doanh thu, bán chạy, mã?)
- Một số bảng đang cân nhắc thêm vào như Bình luận, Bài đăng, v.v
*/

CREATE DATABASE TS2
GO
USE TS2
GO

CREATE TABLE tai_khoan (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_tk VARCHAR(50) NOT NULL,
	mat_khau VARCHAR(50) NOT NULL,
	ho_ten NVARCHAR(50) NOT NULL,
	email VARCHAR(200),
	sdt VARCHAR(20) NOT NULL,
	dia_chi NVARCHAR(MAX),
	vai_tro VARCHAR(20) NOT NULL,
	ngay_tao DATETIME NOT NULL, --Tự động lấy ngày, không cần nhập
	id_nguoitao BIGINT, --Có thể tài khoản được tạo bởi 1 ai đó khác như quản lý
	ngay_chinh_sua DATETIME, --Tự động lấy ngày, không cần nhập khi chỉnh sửa 
	id_nguoichinhsua BIGINT,
	trang_thai BIT DEFAULT 1 NOT NULL --Hoạt động, ngừng hoạt động
)
GO

-- Bảng đơn hàng, thường được tạo bởi khách hàng, không phải giỏ hàng
CREATE TABLE don_hang (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
	ma_don_hang VARCHAR(50) UNIQUE NOT NULL,
	id_khachhang BIGINT,
	hoten_nguoinhanhang NVARCHAR(50) NOT NULL,
	sdt_nguoinhanhang VARCHAR(20) NOT NULL,
	diachi_nhanhang NVARCHAR(MAX) NOT NULL,
	ngay_tao_don DATETIME NOT NULL,
	tong_tien DECIMAL(19,0) NOT NULL, --Tổng tiền sau khi tính toán tất cả chi tiết đơn hàng
    trang_thai NVARCHAR(50) NOT NULL,  -- Trạng thái đơn hàng (Hoàn tất, chưa hoàn tất, hủy, đã giao, chưa giao?)
    ghi_chu NVARCHAR(MAX),  -- Ghi chú đơn hàng
    phuong_thuc_thanh_toan NVARCHAR(50) --Phương thức thanh toán
)
GO

-- Bảng chi_tiet_don_hang (Chi tiết đơn hàng)
CREATE TABLE chitiet_donhang (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    id_donhang BIGINT NOT NULL,  -- Đơn hàng chứa sản phẩm này
    id_sanpham BIGINT NOT NULL, -- Sản phẩm
	id_bienthe BIGINT NOT NULL, -- Biến thể của sản phẩm đó
    so_luong INT NOT NULL,  -- Số lượng sản phẩm muốn mua
	tong_tien DECIMAL(19,0) NOT NULL, --Tổng tiền (giá sp tại thời điểm đó * số lượng)
)
GO

--Bảng hóa đơn phục vụ cho việc áp mã giảm giá.
CREATE TABLE hoa_don (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_hoa_don VARCHAR(50) UNIQUE NOT NULL,
	id_khachhang BIGINT, --Nếu khách hàng đăng nhập sẽ có id, không đăng nhập sẽ không có
	id_nhanvien BIGINT NOT NULL,
	ngay_tao DATETIME NOT NULL,
	id_donhang BIGINT NOT NULL,
	id_chitiet_donhang BIGINT NOT NULL,
	giam_gia DECIMAL(19,0) DEFAULT 0 NOT NULL, --Số tiền được giảm nếu được áp mã
	phi_giao_hang DECIMAL(19,0) DEFAULT 0 NOT NULL, 
	tong_tien DECIMAL(19,0) NOT NULL, --Tổng số tiền cần phải trả sau khi tính toán toàn bộ chi phí
	ghi_chu NVARCHAR(MAX),
	trang_thai BIT DEFAULT 1 --Trạng thái của hóa đơn (Hoàn thành, đã hủy)
)
GO

--Đây là sản phẩm hiển thị trên trang mua hàng và thường chứa toàn bộ thuộc tính chung của sản phẩm
CREATE TABLE thuong_hieu (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_thuonghieu NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE kieu_dang (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_kieudang NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE chat_lieu (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_chatlieu NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE  hinh_anh (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	hinh_anh VARCHAR(MAX) NOT NULL
)
GO

CREATE TABLE mau_sac (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_mausac NVARCHAR(50) NOT NULL
)
GO

CREATE TABLE kich_co (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	kichco INT NOT NULL
)
GO

--Sản phẩm (các biến thể không làm thay đổi giá tiền)
--Có vẻ như bảng danh mục sản phẩm và sản phẩm cuả các bài hd giống với sản phẩm và các biến thể của db này
CREATE TABLE san_pham (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_sanpham VARCHAR(50) UNIQUE NOT NULL, -- Mã sản phẩm (VD: SN001) giúp khách hàng hoặc nv dễ tìm kiếm hơn
	ten_sanpham NVARCHAR(200) NOT NULL,
	id_thuonghieu BIGINT NOT NULL,
	id_kieudang BIGINT NOT NULL,
	id_chatlieu BIGINT NOT NULL,
	gia_goc DECIMAL(19,0) NOT NULL,
	gia_khuyen_mai DECIMAL(19,0), -- Giá khuyến mãi (đợt giảm giá)
	so_luong INT NOT NULL, -- Tự tính toán tổng số lượng các biến thể
	mo_ta NVARCHAR(MAX) NOT NULL, -- Mô tả chi tiết của sản phẩm
	trang_thai BIT DEFAULT 1 NOT NULL, -- Trạng thái còn hoặc hết hàng của sản phẩm
	ngay_tao DATETIME NOT NULL, --Tự động lấy ngày, không cần nhập
	id_nguoitao BIGINT NOT NULL,
	ngay_chinh_sua DATETIME, --Tự động lấy ngày, không cần nhập khi chỉnh sửa 
	id_nguoichinhsua BIGINT
/* Cân nhắc:
	slug NVARCHAR(200) --Tên sản phẩm không dấu, thường được thấy trên url của trang web
	keyword NVARCHAR(200) --Từ khóa giúp tìm kiếm sản phẩm nhanh
	danh_gia decimal(2,2), --Đánh giá của sản phẩm từ 1 đến 5, có thể dùng để xem độ hot của sp
	danhsach_hinhanh --Chứa danh sách hình ảnh của sản phẩm, hiện tại đang cân nhắc 
*/
)
GO

-- Biến thể của sản phẩm. Một sản phẩm sẽ có mặc định 1 biến thể (được hiểu là sản phẩm gốc)
CREATE TABLE bienthe_sanpham (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_sanpham BIGINT NOT NULL,
	id_hinhanh BIGINT,
	id_mausac BIGINT NOT NULL,
	id_kichco BIGINT NOT NULL,
	so_luong INT NOT NULL
)
GO

-- Bảng phieu_giam_gia (Phiếu giảm giá)
CREATE TABLE phieu_giam_gia (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ma_phieu VARCHAR(50) UNIQUE NOT NULL,  -- Mã phiếu giảm giá
    ten_phieu NVARCHAR(100),  -- Tên phiếu giảm giá
    gia_tri DECIMAL(19,0) NOT NULL,  -- Giá trị giảm (theo % hoặc số tiền)
    loai_giam_gia NVARCHAR(50) NOT NULL,  -- Loại giảm giá (phần trăm hoặc giá trị)
	gia_tri_giam_toi_da DECIMAL(19,0), --Giá trị giảm tối đa cho phiếu, đơn vị luôn ở dạng giá trị
    so_luong INT NOT NULL,  -- Số lượng phiếu giảm giá
    thoi_gian_bat_dau DATETIME NOT NULL,  -- Thời gian bắt đầu
    thoi_gian_ket_thuc DATETIME NOT NULL,  -- Thời gian kết thúc
    trang_thai BIT NOT NULL,  -- Trạng thái (đang diễn ra, hết hạn, v.v.)
    kieu NVARCHAR(50) NOT NULL,  -- Kiểu (công khai hoặc cá nhân)
	ngay_tao DATETIME NOT NULL, --Tự động lấy ngày, không cần nhập
	id_nguoitao BIGINT NOT NULL,
	ngay_chinh_sua DATETIME, --Tự động lấy ngày, không cần nhập khi chỉnh sửa 
	id_nguoichinhsua BIGINT
)
GO

-- Bảng dot_giam_gia (Đợt giảm giá)
CREATE TABLE dot_giam_gia (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ten_dot NVARCHAR(100),  -- Tên đợt giảm giá
    gia_tri DECIMAL(19,0) NOT NULL,  -- Giá trị giảm (theo % hoặc số tiền)
    loai_giam_gia NVARCHAR(50) NOT NULL,  -- Loại giảm giá (phần trăm hoặc giá trị)
	gia_tri_giam_toi_da DECIMAL(19,0), --Giá trị giảm tối đa cho phiếu, đơn vị luôn ở dạng giá trị
    trang_thai BIT NOT NULL,  -- Trạng thái (đang diễn ra, hết hạn) -- Tự động
    thoi_gian_bat_dau DATETIME NOT NULL,  -- Thời gian bắt đầu
    thoi_gian_ket_thuc DATETIME NOT NULL,  -- Thời gian kết thúc
	ngay_tao DATETIME NOT NULL, --Tự động lấy ngày, không cần nhập
	id_nguoitao BIGINT NOT NULL,
	ngay_chinh_sua DATETIME, --Tự động lấy ngày, không cần nhập khi chỉnh sửa 
	id_nguoichinhsua BIGINT
)
GO

--Sản phẩm áp dụng/ được áp dụng của phiếu giảm giá/đợt giảm giá. Đóng vai trò làm bảng nối giữa phiếu hoặc đợt với sản phẩm
CREATE TABLE sanpham_apdung (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_phieu BIGINT,
	id_dot BIGINT,
	id_sp_apdung BIGINT NOT NULL,
	id_bienthe_sp BIGINT NOT NULL
)
GO

ALTER TABLE tai_khoan
ADD FOREIGN KEY (id_nguoitao) REFERENCES tai_khoan(id)
GO

ALTER TABLE tai_khoan
ADD FOREIGN KEY (id_nguoichinhsua) REFERENCES tai_khoan(id)
GO

ALTER TABLE don_hang
ADD FOREIGN KEY (id_khachhang) REFERENCES tai_khoan(id)
GO

ALTER TABLE chitiet_donhang
ADD FOREIGN KEY (id_donhang) REFERENCES don_hang(id)
GO

ALTER TABLE chitiet_donhang
ADD FOREIGN KEY (id_sanpham) REFERENCES san_pham(id)
GO

ALTER TABLE chitiet_donhang
ADD FOREIGN KEY (id_bienthe) REFERENCES bienthe_sanpham(id)
GO

ALTER TABLE hoa_don
ADD FOREIGN KEY (id_khachhang) REFERENCES tai_khoan(id)
GO

ALTER TABLE hoa_don
ADD FOREIGN KEY (id_nhanvien) REFERENCES tai_khoan(id)
GO

ALTER TABLE hoa_don
ADD FOREIGN KEY (id_donhang) REFERENCES don_hang(id)
GO

ALTER TABLE hoa_don
ADD FOREIGN KEY (id_chitiet_donhang) REFERENCES chitiet_donhang(id)
GO

ALTER TABLE san_pham
ADD FOREIGN KEY (id_thuonghieu) REFERENCES thuong_hieu(id);
GO

ALTER TABLE san_pham
ADD FOREIGN KEY (id_kieudang) REFERENCES kieu_dang(id);
GO

ALTER TABLE san_pham
ADD FOREIGN KEY (id_chatlieu) REFERENCES chat_lieu(id);
GO

ALTER TABLE san_pham
ADD FOREIGN KEY (id_nguoitao) REFERENCES tai_khoan(id);
GO

ALTER TABLE san_pham
ADD FOREIGN KEY (id_nguoichinhsua) REFERENCES tai_khoan(id);
GO

ALTER TABLE bienthe_sanpham
ADD FOREIGN KEY (id_sanpham) REFERENCES san_pham(id);
GO

ALTER TABLE bienthe_sanpham
ADD FOREIGN KEY (id_hinhanh) REFERENCES hinh_anh(id);
GO

ALTER TABLE bienthe_sanpham
ADD FOREIGN KEY (id_mausac) REFERENCES mau_sac(id);
GO

ALTER TABLE bienthe_sanpham
ADD FOREIGN KEY (id_kichco) REFERENCES kich_co(id);
GO

ALTER TABLE phieu_giam_gia
ADD FOREIGN KEY (id_nguoitao) REFERENCES tai_khoan(id)
GO

ALTER TABLE phieu_giam_gia
ADD FOREIGN KEY (id_nguoichinhsua) REFERENCES tai_khoan(id)
GO

ALTER TABLE dot_giam_gia
ADD FOREIGN KEY (id_nguoitao) REFERENCES tai_khoan(id)
GO

ALTER TABLE dot_giam_gia
ADD FOREIGN KEY (id_nguoichinhsua) REFERENCES tai_khoan(id)
GO

ALTER TABLE sanpham_apdung
ADD FOREIGN KEY (id_phieu) REFERENCES phieu_giam_gia(id)
GO

ALTER TABLE sanpham_apdung
ADD FOREIGN KEY (id_dot) REFERENCES dot_giam_gia(id)
GO

ALTER TABLE sanpham_apdung
ADD FOREIGN KEY (id_sp_apdung) REFERENCES san_pham(id)
GO

ALTER TABLE sanpham_apdung
ADD FOREIGN KEY (id_bienthe_sp) REFERENCES bienthe_sanpham(id)
GO

/* --Bảng thống kê được tạo dựa trên bảng này, hiện tại vẫn chưa biết cách hoạt động
   CREATE TABLE `statistic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `profit` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sales` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKok7jp7mh6y9tghumc2do51ieq` (`order_id`),
  CONSTRAINT `FKok7jp7mh6y9tghumc2do51ieq` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE thong_ke (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	loi_nhuan BIGINT,
	so_luong INT,
	luong_donhang_daban BIGINT,
	id_donhang BIGINT NOT NULL
)
GO
*/

/*
ALTER TABLE tai_khoan
ADD CONSTRAINT add_tu_dong DEFAULT (GETDATE()) FOR ngay_tao
GO

ALTER TABLE don_hang
ADD CONSTRAINT add_tu_dong DEFAULT (GETDATE()) FOR ngay_tao
GO

ALTER TABLE san_pham
ADD CONSTRAINT add_tu_dong DEFAULT (GETDATE()) FOR ngay_tao
GO

ALTER TABLE phieu_giam_gia
ADD CONSTRAINT add_tu_dong DEFAULT (GETDATE()) FOR ngay_tao
GO

ALTER TABLE phieu_giam_gia
ADD CONSTRAINT add_tu_dong DEFAULT (GETDATE()) FOR ngay_tao
GO

Dự kiến sẽ xử lý ngày tạo tự động lấy ngày bằng câu lệnh insert sql
*/

INSERT INTO tai_khoan (ten_tk, mat_khau, ho_ten, email, sdt, dia_chi, vai_tro, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua, trang_thai)
VALUES 
('nghia', '123456', N'Nguyễn Hữu Nghĩa', 'nghia@gmail.com', '0901234567', N'Hà Nội', 'admin', GETDATE(), NULL, NULL, NULL, 1),
('thai', '123456', N'Chu Trần Thái', 'thai@gmail.com', '0907654321', N'Hà Nội', 'manager', GETDATE(), 1, GETDATE(), 1, 1);
Go
INSERT INTO thuong_hieu (ten_thuonghieu)
VALUES 
(N'Nike'),
(N'Adidas'),
(N'Puma');
Go
INSERT INTO kieu_dang (ten_kieudang)
VALUES 
(N'Thể thao'),
(N'Teddy'),
(N'Trẻ trung');
Go
INSERT INTO chat_lieu (ten_chatlieu)
VALUES 
(N'Da'),
(N'Vải Da'),
(N'Da');
Go
INSERT INTO mau_sac (ten_mausac)
VALUES 
(N'Đen'),
(N'Trắng'),
(N'Xanh');
Go
INSERT INTO kich_co (kichco)
VALUES 
(38),
(40),
(42);
Go
Select * from san_pham
INSERT INTO san_pham (ma_sanpham, ten_sanpham, id_thuonghieu, id_kieudang, id_chatlieu, gia_goc, gia_khuyen_mai, so_luong, mo_ta, trang_thai, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua)
VALUES 
('SP001', N'Giày Thể Thao Nam', 1, 1, 1, 1200000, 1000000, 100, N'Giày thể thao nam Nike chính hãng.', 1, GETDATE(), 1, GETDATE(), 1),
('SP002', N'Giày Thể Thao Sneaker', 2, 2, 2, 800000, 700000, 50, N'Giày Thể Thao Sneaker.', 1, GETDATE(), 1, GETDATE(), 1);
Go
INSERT INTO bienthe_sanpham (id_sanpham, id_hinhanh, id_mausac, id_kichco, so_luong)
VALUES 
(1, 1, 1, 1, 100),
(1, 2, 2, 1, 50),  
(2, 3, 1, 2, 30);  
Go
INSERT INTO don_hang (ma_don_hang, id_khachhang, hoten_nguoinhanhang, sdt_nguoinhanhang, diachi_nhanhang, ngay_tao_don, tong_tien, trang_thai, ghi_chu, phuong_thuc_thanh_toan)
VALUES 
('DH001', 1, N'Nguyễn Văn A', '0901234567', N'123 Nguyễn Trãi, Hà Nội', GETDATE(), 2000000, N'Hoàn tất', N'Giao hàng nhanh', N'Thẻ tín dụng'),
('DH002', 1, N'Trần Thị B', '0912345678', N'456 Lê Lợi, Hồ Chí Minh', GETDATE(), 1200000, N'Chưa giao', NULL, N'Tiền mặt');
Go
INSERT INTO chitiet_donhang (id_donhang, id_sanpham, id_bienthe, so_luong, tong_tien)
VALUES 
(1, 1, 1, 2, 3000000),
(1, 2, 2, 1, 1600000);  
Go
INSERT INTO hoa_don (ma_hoa_don, id_khachhang, id_nhanvien, ngay_tao, id_donhang, id_chitiet_donhang, giam_gia, phi_giao_hang, tong_tien, ghi_chu)
VALUES 
('HD001', 1, 1, GETDATE(), 1, 1, 0, 0, 3000000, N'Thanh toán đầy đủ'),
('HD002', 1, 1, GETDATE(), 1, 2, 0, 0, 1600000, N'Giao hàng nhanh');
Go
INSERT INTO phieu_giam_gia (ma_phieu, ten_phieu, gia_tri, loai_giam_gia, gia_tri_giam_toi_da, so_luong, thoi_gian_bat_dau, thoi_gian_ket_thuc, trang_thai, kieu, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua)
VALUES 
('PGG001', N'Giảm 10% đơn hàng', 10, N'Phần trăm', NULL, 100, GETDATE(), DATEADD(MONTH, 1, GETDATE()), 1, N'Công khai', GETDATE(), 1, GETDATE(), 1),
('PGG002', N'Giảm 50k cho đơn từ 500k', 50000, N'Tiền',50000, 50, GETDATE(), DATEADD(MONTH, 1, GETDATE()), 1, N'Cá nhân', GETDATE(), 1, GETDATE(), 1);
Go
INSERT INTO dot_giam_gia (ten_dot, gia_tri, loai_giam_gia, gia_tri_giam_toi_da, trang_thai, thoi_gian_bat_dau, thoi_gian_ket_thuc, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua)
VALUES 
(N'Khuyến mãi mùa hè', 20, N'Phần trăm', NULL, 1, GETDATE(), DATEADD(MONTH, 1, GETDATE()), GETDATE(), 1, GETDATE(), 1);
INSERT INTO hinh_anh (hinh_anh)
VALUES 
('https://example.com/images/adidas_shoes_1.jpg'),
('https://example.com/images/adidas_shoes_2.jpg'),
('https://example.com/images/nike_shirt_1.jpg'),
('https://example.com/images/nike_shirt_2.jpg'),
('https://example.com/images/puma_shoes_1.jpg');
Go
