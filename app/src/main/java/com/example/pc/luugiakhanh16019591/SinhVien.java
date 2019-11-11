package com.example.pc.luugiakhanh16019591;

import java.util.Objects;

class SinhVien {
    private int mssv;
    private String tenSV;
    private String lop;

    public SinhVien(int mssv, String tenSV, String lop) {
        this.mssv = mssv;
        this.tenSV = tenSV;
        this.lop = lop;
    }

    public SinhVien() {
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "mssv=" + mssv +
                ", tenSV='" + tenSV + '\'' +
                ", lop='" + lop + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinhVien sinhVien = (SinhVien) o;
        return mssv == sinhVien.mssv;
    }

    @Override
    public int hashCode() {

        return Objects.hash(mssv);
    }
}
