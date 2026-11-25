import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

abstract class SinhVien {
    protected static final long DON_GIA = 450000;
    protected String maSV;
    protected String maHe;
    protected int soTinChi;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public String getMaSV() { return maSV; }

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi();
    }
}

class SinhVienChinhQuy extends SinhVien {
    public SinhVienChinhQuy(String maSV, int tc) { super(maSV, "CQ", tc); }

    @Override
    public long hocPhi() { return soTinChi * DON_GIA; }
}

class SinhVienCLC extends SinhVien {
    public SinhVienCLC(String maSV, int tc) { super(maSV, "CLC", tc); }

    @Override
    public long hocPhi() { return soTinChi * DON_GIA * 3 / 2; }
}

class SinhVienLienThong extends SinhVien {
    public SinhVienLienThong(String maSV, int tc) { super(maSV, "LT", tc); }

    @Override
    public long hocPhi() { return soTinChi * DON_GIA + 2000000; }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> ds = new ArrayList<>();

    public void them(String maHe, String maSV, String tcStr) throws DuLieuKhongHopLeException {
        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT"))
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        int tc;
        try {
            tc = Integer.parseInt(tcStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong phai so nguyen");
        }
        if (tc < 1 || tc > 30)
            throw new DuLieuKhongHopLeException("So tin chi ngoai doan [1, 30]");
        for (SinhVien sv : ds) {
            if (sv.getMaSV().equals(maSV))
                throw new DuLieuKhongHopLeException("Trung ma sinh vien");
        }
        if (maHe.equals("CQ")) ds.add(new SinhVienChinhQuy(maSV, tc));
        else if (maHe.equals("CLC")) ds.add(new SinhVienCLC(maSV, tc));
        else ds.add(new SinhVienLienThong(maSV, tc));
    }

    public void sapXep() {
        ds.sort((a, b) -> {
            if (a.hocPhi() != b.hocPhi()) return Long.compare(b.hocPhi(), a.hocPhi());
            return a.getMaSV().compareTo(b.getMaSV());
        });
    }

    public long tongHocPhi() {
        long tong = 0;
        for (SinhVien sv : ds) tong += sv.hocPhi();
        return tong;
    }

    public void inDanhSach() {
        for (SinhVien sv : ds) System.out.println(sv);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        QuanLyHocPhi ql = new QuanLyHocPhi();
        List<String> invalid = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] p = sc.nextLine().trim().split("\\s+");
            try {
                ql.them(p[0], p[1], p[2]);
            } catch (DuLieuKhongHopLeException e) {
                invalid.add(p[1]);
            }
        }
        for (String ma : invalid) System.out.println("INVALID " + ma);
        ql.sapXep();
        ql.inDanhSach();
        System.out.println("TONG " + ql.tongHocPhi());
    }
}
