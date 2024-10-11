/* Note:
- Dự kiến sẽ xử lý ngày tạo tự động lấy ngày bằng câu lệnh insert sql.
- Chưa rõ có cần bảng thống kê không và nếu có thì nó sẽ chứa những trường gì (bảng thống kê doanh thu)
- Nếu có bảng thống kê thì chỉ cần 1 hay nên có nhiều bảng cho nhiều loại thống kê? (doanh thu, bán chạy, mã?)
- Một số bảng đang cân nhắc thêm vào như Bình luận, Bài đăng, v.v
*/

CREATE DATABASE TeddySneaker
GO
USE TeddySneaker
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
	gia_tri_toi_thieu DECIMAL(19,0) NOT NULL, --Giá trị tối thiểu đơn hàng cần đạt để sử dụng mã giảm
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
	id_sp_apdung BIGINT,
	id_bienthe_sp BIGINT
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
('Nghia', '123456', N'Nguyễn Hữu Nghĩa', 'nghia@gmail.com', '0901234567', N'Hà Nội', 'admin', GETDATE(), NULL, NULL, NULL, 1),
('Thai', '123456', N'Chu Trần Thái', 'thai@gmail.com', '0907654321', N'Hà Nội', 'manager', GETDATE(), NULL, NULL, NULL, 1),
('VDuc', '123456', N'Vương Minh Đức', 'vduc@gmail.com', '0123456789', N'Hà Nội', 'employee', GETDATE(), NULL, NULL, NULL, 1),
('PDuc', '123456', N'Phạm Minh Đức', 'pduc@gmail.com', '0345239051', N'Hà Nội', 'admin', GETDATE(), NULL, NULL, NULL, 1),
('Phuong', '123456', N'Hoàng Văn Phương', 'phuong@gmail.com', '0987654321', N'Hà Nội', 'employee', GETDATE(), NULL, NULL, NULL, 1)
GO

INSERT INTO thuong_hieu (ten_thuonghieu)
VALUES 
('Nike'),
('Adidas'),
('Puma'),
('Converse'),
('Vans');
GO

INSERT INTO kieu_dang (ten_kieudang)
VALUES 
('Giày bóng đá'),
('Giày chạy'),
('Giày chơi golf'),
('Giày trượt ván'),
('Giày tập');
GO

INSERT INTO chat_lieu (ten_chatlieu)
VALUES 
('Da'),
('Da lộn'),
('Vải bố'),
('Chất liệu tổng hợp'),
('Lưới')
GO

INSERT INTO hinh_anh (hinh_anh)
VALUES 
('anh1'),
('anh2'),
('anh3'),
('anh4'),
('anh5'),
('anh6'),
('anh7'),
('anh8'),
('anh9'),
('anh10'),
('anh11'),
('anh12'),
('anh13'),
('anh14'),
('anh15')
GO

INSERT INTO mau_sac (ten_mausac)
VALUES 
('Đỏ'),
('Đen'),
('Trắng'),
('Xanh'),
('Xám'),
('Xanh lá cây'),
('Nâu'),
('Vàng'),
('Cam')
GO

INSERT INTO kich_co (kichco)
VALUES 
(35),
(36),
(37),
(38),
(39),
(40),
(41),
(42),
(43), 
(44)
Go

INSERT INTO san_pham (ma_sanpham, ten_sanpham, id_thuonghieu, id_kieudang, id_chatlieu, gia_goc, gia_khuyen_mai, so_luong, mo_ta, trang_thai, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua)
VALUES 
('SP001', N'Nike Air Max 97', 1, 1, 1, 3500000, NULL, 50, 'Nike Air Max 97 với thiết kế hiện đại và thoải mái.', 1, GETDATE(), 1, NULL, NULL),
('SP002', N'Adidas Ultraboost 21', 2, 2, 2, 4200000, NULL, 30, 'Adidas Ultraboost 21, đôi giày chạy bộ tốt nhất.', 1, GETDATE(), 1, NULL, NULL),
('SP003', N'Puma Classic Suede', 3, 3, 3, 2700000, NULL, 20, 'Puma Classic Suede, phong cách cổ điển.', 1, GETDATE(), 1, NULL, NULL),
('SP004', N'Converse Chuck Taylor All Star', 4, 4, 4, 1500000, NULL, 40, 'Converse Chuck Taylor All Star, biểu tượng của sự đơn giản và thời trang.', 1, GETDATE(), 1, NULL, NULL),
('SP005', N'Nike Air Force 1', 5, 5, 5, 3300000, NULL, 40, 'Nike Air Force 1, biểu tượng của sự thanh lịch và cổ điển.', 1, GETDATE(), 1, NULL, NULL)
GO

INSERT INTO bienthe_sanpham (id_sanpham, id_hinhanh, id_mausac, id_kichco, so_luong)
VALUES 
(1, 1, 3, 1, 10),
(1, 2, 1, 2, 15),
(1, 3, 2, 3, 25),
(2, 4, 6, 4, 8),
(2, 5, 4, 5, 12),
(2, 6, 5, 6, 10),
(3, 7, 9, 7, 7),
(3, 8, 7, 8, 13),
(3, 9, 8, 9, 20),
(4, 10, 3, 1, 9),
(4, 11, 2, 2, 14),
(4, 12, 1, 3, 17),
(5, 13, 6, 4, 11),
(5, 14, 5, 5, 18),
(5, 15, 4, 6, 19)
GO

INSERT INTO don_hang (ma_don_hang, id_khachhang, hoten_nguoinhanhang, sdt_nguoinhanhang, diachi_nhanhang, ngay_tao_don, tong_tien, trang_thai, ghi_chu, phuong_thuc_thanh_toan)
VALUES 
('DH001', NULL, N'Nguyễn Văn A', '0901234567', N'Đ. Thanh Niên, Quán Thánh, Ba Đình, Hà Nội 118810, Việt Nam', GETDATE(), 11200000, N'Đã giao', NULL, N'Tiền mặt'),
('DH002', NULL, N'Trần Thị B', '0912345678', N'Giảng Võ, Ba Đình, Hà Nội, Việt Nam', GETDATE(), 10900000, N'Chưa giao', NULL, N'Tiền mặt'),
('DH003', NULL, N'Trần Thị B', '0912345678', N'7 P. Hàng Chai, Hàng Mã, Hoàn Kiếm, Hà Nội', GETDATE(), 3500000, N'Chưa giao', NULL, N'Tiền mặt'),
('DH004', 3, N'Vương Minh Đức', '0123456789', N'251 Phố Xã Đàn P. Nam Đồng Q. Đống Đa, Nam Đồng, Đống Đa, Hà Nội, Việt Nam', GETDATE(), 8400000, N'Hoàn thành', NULL, N'Tiền mặt'),
('DH005', 5, N'Hoàng Văn Phương', '0987654321', N'614 Đ. Lạc Long Quân, Nhật Tân, Tây Hồ, Hà Nội, Việt Nam', GETDATE(), 8100000, N'Chưa giao', NULL, N'Chuyển khoản');
GO

INSERT INTO chitiet_donhang (id_donhang, id_sanpham, id_bienthe, so_luong, tong_tien)
VALUES 
(1, 1, 1, 2, 2 * 3500000),
(1, 2, 4, 1, 1 * 4200000),
(2, 3, 7, 1, 1 * 2700000),
(2, 4, 10, 2, 2 * 1500000),
(2, 5, 13, 3, 3 * 3300000),
(3, 1, 1, 2, 2 * 3500000),
(4, 2, 6, 1, 1 * 4200000),
(4, 3, 9, 2, 2 * 2700000),
(4, 4, 11, 1, 1 * 1500000),
(5, 1, 3, 1, 1 * 3500000),
(5, 5, 15, 2, 2 * 3300000)
GO

INSERT INTO hoa_don (ma_hoa_don, id_khachhang, id_nhanvien, ngay_tao, id_donhang, id_chitiet_donhang, giam_gia, phi_giao_hang, tong_tien, ghi_chu)
VALUES
('HD001', NULL, 1, GETDATE(), 1, 1, 0, 0, 11200000, NULL),
('HD002', NULL, 1, GETDATE(), 2, 2, 0, 0, 10900000, NULL),
('HD003', NULL, 1, GETDATE(), 3, 3, 0, 0, 3500000, NULL),
('HD004', 3, 1, GETDATE(), 4, 4, 0, 0, 8400000, NULL),
('HD005', 5, 1, GETDATE(), 5, 5, 0, 0, 8100000, NULL)
GO

INSERT INTO phieu_giam_gia (ma_phieu, ten_phieu, gia_tri, loai_giam_gia, gia_tri_toi_thieu, gia_tri_giam_toi_da, so_luong, thoi_gian_bat_dau, thoi_gian_ket_thuc, trang_thai, kieu, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua)
VALUES 
('PGG001', N'Giảm 15% cho đơn hàng', 15, N'Phần trăm', 200000, NULL, 100, '2024-10-01', '2024-11-08', 1, N'Công khai', GETDATE(), 1, NULL, NULL),
('PGG002', N'Giảm 30k cho đơn hàng trên 300k', 30000, N'Tiền', 300000, 50000, 50, '2024-10-01', '2024-11-06', 1, N'Cá nhân', GETDATE(), 1, NULL, NULL),
('PGG003', N'Miễn phí giao hàng cho đơn hàng trên 500k', 0, N'Tiền', 500000, NULL, 200, '2024-10-01', '2024-11-11', 1, N'Công khai', GETDATE(), 1, NULL, NULL),
('PGG004', N'Giảm 20% cho đơn hàng đầu tiên', 20, N'Phần trăm', 100000, NULL, 150, '2024-10-01', '2024-11-04', 1, N'Cá nhân', GETDATE(), 1, NULL, NULL),
('PGG005', N'Giảm 40k cho đơn từ 400k', 40000, N'Tiền', 400000, 60000, 75, '2024-10-01', '2024-11-03', 1, N'Công khai', GETDATE(), 1, NULL, NULL);
GO

INSERT INTO dot_giam_gia (ten_dot, gia_tri, loai_giam_gia, gia_tri_giam_toi_da, trang_thai, thoi_gian_bat_dau, thoi_gian_ket_thuc, ngay_tao, id_nguoitao, ngay_chinh_sua, id_nguoichinhsua)
VALUES 
(N'Khuyến mãi mùa hè', 10, N'Phần trăm', 50000, 1, '2024-10-01', '2024-12-10', GETDATE(), 1, NULL, NULL),
(N'Mừng sinh nhật khách hàng', 20, N'Phần trăm', 100000, 1, '2024-10-02', '2024-12-12', GETDATE(), 1, NULL, NULL),
(N'Giảm giá cuối tuần', 30, N'Tiền', 30000, 1, '2024-10-03', '2024-12-07', GETDATE(), 1, NULL, NULL),
(N'Giảm giá mùa đông', 15, N'Phần trăm', NULL, 1, '2024-10-04', '2024-12-14', GETDATE(), 1, NULL, NULL),
(N'Giảm giá cho khách hàng thân thiết', 25, N'Phần trăm', 70000, 1, '2024-10-05', '2024-12-15', GETDATE(), 1, NULL, NULL);
GO

INSERT INTO sanpham_apdung (id_phieu, id_dot, id_sp_apdung, id_bienthe_sp)
VALUES 
(1, NULL, 1, 1),
(1, NULL, 2, 4),
(1, NULL, 3, 7),
(1, NULL, 4, 10),
(1, NULL, 5, 13),
(2, NULL, 1, 2),
(2, NULL, 2, 5),
(2, NULL, 3, 8),
(2, NULL, 4, 11),
(2, NULL, 5, 14),
(3, NULL, 1, 3),
(3, NULL, 2, 6),
(3, NULL, 3, 9),
(3, NULL, 4, 12),
(3, NULL, 5, 15),
(4, NULL, 1, 1),
(4, NULL, 2, 4),
(4, NULL, 3, 7),
(4, NULL, 4, 10),
(4, NULL, 5, 13),
(5, NULL, 1, 2),
(5, NULL, 2, 5),
(5, NULL, 3, 8),
(5, NULL, 4, 11),
(5, NULL, 5, 14),
(NULL, 1, 1, 1),
(NULL, 1, 2, 4),
(NULL, 1, 3, 7),
(NULL, 1, 4, 10),
(NULL, 1, 5, 13),
(NULL, 2, 1, 2),
(NULL, 2, 2, 5),
(NULL, 2, 3, 8),
(NULL, 2, 4, 11),
(NULL, 2, 5, 14),
(NULL, 3, 1, 3),
(NULL, 3, 2, 6),
(NULL, 3, 3, 9),
(NULL, 3, 4, 12),
(NULL, 3, 5, 15),
(NULL, 4, 1, 1),
(NULL, 4, 2, 4),
(NULL, 4, 3, 7),
(NULL, 4, 4, 10),
(NULL, 4, 5, 13),
(NULL, 5, 1, 2),
(NULL, 5, 2, 5),
(NULL, 5, 3, 8),
(NULL, 5, 4, 11),
(NULL, 5, 5, 14)
GO
