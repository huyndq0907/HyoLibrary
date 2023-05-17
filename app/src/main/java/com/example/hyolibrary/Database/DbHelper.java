package com.example.hyolibrary.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HyoLibrary";
    private static final int DB_VERSION = 1;

    static final String CREATE_TABLE_THU_THU = "CREATE TABLE ThuThu (\n" +
            "    maTT    TEXT PRIMARY KEY,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    matKhau TEXT NOT NULL\n" +
            ");\n";
    static final String CREATE_TABLE_THANH_VIEN ="CREATE TABLE ThanhVien (\n" +
            "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    hoTen   TEXT NOT NULL,\n" +
            "    namSinh TEXT NOT NULL\n" +
            ");\n";

    static final String CREATE_TABLE_LOAI_SACH ="CREATE TABLE LoaiSach (\n" +
            "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenLoai TEXT    NOT NULL\n" +
            ");\n";

    static final String CREATE_TABLE_SACH ="CREATE TABLE Sach (\n" +
            "    maSach  INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
            "    tenSach TEXT     NOT NULL,\n" +
            "    giaThue INTEGER  NOT NULL,\n" +
            "    maLoai  INTEGER  REFERENCES LoaiSach (maLoai) \n" +
            ");\n";

    static final String CREATE_TABLE_PHIEU_MUON ="CREATE TABLE PhieuMuon (\n" +
            "    maPM     INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
            "    maTT     TEXT     REFERENCES ThuThu (maTT),\n" +
            "    maTV     INTEGER  REFERENCES ThanhVien (maTV),\n" +
            "    maSach   INTEGER  REFERENCES Sach (maSach),\n" +
            "    tienThue INTEGER  NOT NULL,\n" +
            "    ngay     DATE     NOT NULL,\n" +
            "    traSach  INTEGER  NOT NULL\n" +
            ");\n";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tao bang Thu Thu
        db.execSQL(CREATE_TABLE_THU_THU);
        // Tao bang Thanh Vien
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        // Tao bang Loai Sach
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        // Tao bang Sach
        db.execSQL(CREATE_TABLE_SACH);
        // Tao bang Phieu Muon
        db.execSQL(CREATE_TABLE_PHIEU_MUON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);

        String dropTableThanhVien = "drop table if exists ThanhVIen";
        db.execSQL(dropTableThanhVien);

        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);

        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);

        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
