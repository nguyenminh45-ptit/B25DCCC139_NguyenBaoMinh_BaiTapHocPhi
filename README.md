# Bài tập: Quản lý học phí sinh viên

## Thông tin sinh viên
- **Họ và tên:** [Điền họ và tên]
- **Mã sinh viên:** [Điền mã sinh viên]
- **Lớp:** [Điền lớp]

## Cấu trúc project

| Thư mục | Nội dung |
|---------|----------|
| `cau1/` | Chuẩn hóa thông tin sinh viên |
| `cau2/` | Tính học phí theo hệ đào tạo (abstract class) |
| `cau3/` | Quản lý danh sách học phí (exception tự định nghĩa, ArrayList) |
| `cau4/` | Miễn giảm học phí và thống kê theo hệ (interface) |

Mỗi thư mục chứa một file `Main.java` độc lập, không khai báo package.

## Yêu cầu
- JDK 8 trở lên (kiểm tra bằng lệnh `java -version`)

## Hướng dẫn chạy chương trình

Mỗi câu là một chương trình độc lập, dữ liệu được nhập từ bàn phím (`System.in`).

Ví dụ chạy câu 1:

```bash
cd cau1
javac Main.java
java Main
```

Sau đó nhập dữ liệu theo đúng định dạng đề bài. Có thể chạy bằng file input:

```bash
java Main < input.txt
```

Các câu 2, 3, 4 chạy tương tự, chỉ cần đổi thư mục:

```bash
cd cau2   # hoặc cau3, cau4
javac Main.java
java Main
```

## Ví dụ nhập/xuất (Câu 2)

Input:
```
4
CQ B23DCCN001 18
CLC B23DCCN002 20
LT B23DCCN003 15
CQ B23DCCN004 21
```

Output:
```
B23DCCN001 CQ 8100000
B23DCCN002 CLC 13500000
B23DCCN003 LT 8750000
B23DCCN004 CQ 9450000
```
