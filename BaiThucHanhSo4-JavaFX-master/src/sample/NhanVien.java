package sample;

import javafx.scene.control.Button;

import java.time.LocalDate;
import java.util.Date;

public class NhanVien extends Button{
    private int Ma;
    private String Ten;
    private String NgSinh;
    private String GioiTinh;
    private Double trinhDo;



    @Override
    public String toString() {
        return "NhanVien [Ma=" + Ma + ", Ten=" + Ten + ", NgSinh=" + NgSinh + ", GioiTinh=" + GioiTinh + ", trinhDo="
                + trinhDo + "]";
    }
    public NhanVien() {
        super();
    }
    public NhanVien(int ma, String ten, String ngSinh, String gioiTinh, Double trinhDo) {
        super();
        Ma = ma;
        Ten = ten;
        NgSinh = ngSinh;
        GioiTinh = gioiTinh;
        this.trinhDo = trinhDo;
    }
    public int getMa() {
        Ma = Ma++;
        return Ma;
    }
    public void setMa(int ma) {
        Ma = ma;
    }
    public String getTen() {
        return Ten;
    }
    public void setTen(String ten) {
        Ten = ten;
    }
    public String getNgSinh() {
        return NgSinh;
    }
    public void setNgSinh(String ngSinh) {
        NgSinh = ngSinh;
    }
    public String getGioiTinh() {
        return GioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }
    public Double getTrinhDo() {
        return trinhDo;
    }
    public void setTrinhDo(Double trinhDo) {
        this.trinhDo = trinhDo;
    }


}
