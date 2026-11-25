import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return hocPhi * 50 / 100; }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return hocPhi * 70 / 100; }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) { return Math.min(hocPhi, 3000000L); }
}

abstract class SinhVien {
    protected static final long DON_GIA = 450000;
    protected String maSV;
    protected String maHe;
    protected int soTinChi;
    protected MienGiam mienGiam; // co the null

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public long soTienGiam() {
        return mienGiam == null ? 0 : mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() { return hocPhi() - soTienGiam(); }

    public String getMaSV() { return maSV; }
    public String getMaHe() { return maHe; }
    public void setMienGiam(MienGiam mg) { this.mienGiam = mg; }

    @Override
    public String toString() {
        return maSV + " " + maHe + " " + hocPhi() + " " + soTienGiam() + " " + phaiNop();
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
        Map<String, SinhVien> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String he = sc.next();
            String ma = sc.next();
            int tc = sc.nextInt();
            SinhVien sv;
            if (he.equals("CQ")) sv = new SinhVienChinhQuy(ma, tc);
            else if (he.equals("CLC")) sv = new SinhVienCLC(ma, tc);
            else sv = new SinhVienLienThong(ma, tc);
            ds.add(sv);
            map.put(ma, sv);
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            String ma = sc.next();
            String cs = sc.next();
            SinhVien sv = map.get(ma);
            if (sv == null) {
                System.out.println("NOT FOUND " + ma);
                continue;
            }
            if (cs.equals("CS")) sv.setMienGiam(new ConChinhSach());
            else if (cs.equals("HN")) sv.setMienGiam(new HoNgheo());
            else if (cs.equals("HB")) sv.setMienGiam(new HocBong());
        }
        for (SinhVien sv : ds) System.out.println(sv);

        String[] heList = {"CQ", "CLC", "LT"};
        for (String he : heList) {
            int dem = 0;
            long tong = 0;
            for (SinhVien sv : ds) {
                if (sv.getMaHe().equals(he)) {
                    dem++;
                    tong += sv.phaiNop();
                }
            }
            if (dem > 0) System.out.println(he + " " + dem + " " + tong);
        }
    }
}
