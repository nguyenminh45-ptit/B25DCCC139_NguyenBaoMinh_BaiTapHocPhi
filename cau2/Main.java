import java.util.*;

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
    public String getMaHe() { return maHe; }

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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<SinhVien> ds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tc = sc.nextInt();
            if (he.equals("CQ")) ds.add(new SinhVienChinhQuy(ma, tc));
            else if (he.equals("CLC")) ds.add(new SinhVienCLC(ma, tc));
            else ds.add(new SinhVienLienThong(ma, tc));
        }
        for (SinhVien sv : ds) {
            System.out.println(sv);
        }
    }
}
