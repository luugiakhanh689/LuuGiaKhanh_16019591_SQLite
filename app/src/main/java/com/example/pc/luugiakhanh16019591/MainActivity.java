package com.example.pc.luugiakhanh16019591;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText edtMSSV, edtTenSV, edtLop;
    private Button btnThem, btnSua, btnXoa;
    private ListView lvSV;
    private SqliteSV sqliteSV;
    private SinhVienAdapter adapter;
    private ArrayList<SinhVien> listSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        createEvent();
    }

    public void init() {
        edtMSSV = findViewById(R.id.edtMSSV);
        edtTenSV = findViewById(R.id.edtTenSV);
        edtLop = findViewById(R.id.edtLop);
        btnSua = findViewById(R.id.btnSua);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        lvSV = findViewById(R.id.lvSV);
        sqliteSV = new SqliteSV(this);
        listSV = sqliteSV.getAllSV();
        adapter = new SinhVienAdapter(this, R.layout.sv_item_lv, listSV);
        lvSV.setAdapter(adapter);
    }

    public void createEvent() {
        btnThem.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnSua.setOnClickListener(this);
        lvSV.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThem:
                if (btnThem.getText().toString().equalsIgnoreCase("Thêm")) {
                    themSV();
                } else {
                    resetForm();
                }
                break;
            case R.id.btnSua:
                suaSV();
                break;
            case R.id.btnXoa:
                xoaSV();
                break;
        }
    }

    private void xoaSV() {
        if (edtMSSV.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy chọn 1 sinh viên", Toast.LENGTH_SHORT).show();
        } else {
            if (sqliteSV.deleteSinhVien(edtMSSV.getText().toString()) > 0) {
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listSV.clear();
                listSV.addAll(sqliteSV.getAllSV());
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void suaSV() {
        if (edtTenSV.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập tên sv", Toast.LENGTH_SHORT).show();
        } else if (edtLop.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập lớp", Toast.LENGTH_SHORT).show();
        } else {
            if (sqliteSV.updateSinhVien(edtMSSV.getText().toString(), edtTenSV.getText().toString(), edtLop.getText().toString()) > 0) {
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listSV.clear();
                listSV.addAll(sqliteSV.getAllSV());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void themSV() {
        if (edtMSSV.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập MSSV", Toast.LENGTH_SHORT).show();
        } else if (edtTenSV.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập tên sinh viên", Toast.LENGTH_SHORT).show();
        } else if (edtLop.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập lớp", Toast.LENGTH_SHORT).show();
        } else if (sqliteSV.getSVById(edtMSSV.getText().toString())!=null) {
            Toast.makeText(this, "Trùng mã sv", Toast.LENGTH_SHORT).show();
        } else {
            if (sqliteSV.createSV(getSVInfo()) > 0) {
                Toast.makeText(this, "Thêm sv thành công", Toast.LENGTH_SHORT).show();
                listSV.clear();
                listSV.addAll(sqliteSV.getAllSV());
                adapter.notifyDataSetChanged();
                lvSV.setSelection(adapter.getCount() - 1);
                resetForm();
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public SinhVien getSVInfo() {
        SinhVien sinhVien = new SinhVien();
        sinhVien.setMssv(Integer.parseInt(edtMSSV.getText().toString()));
        sinhVien.setTenSV(edtTenSV.getText().toString());
        sinhVien.setLop(edtLop.getText().toString());
        return sinhVien;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SinhVien sinhVien = listSV.get(position);
        edtMSSV.setText(String.valueOf(sinhVien.getMssv()));
        edtTenSV.setText(sinhVien.getTenSV());
        edtLop.setText(sinhVien.getLop());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        edtMSSV.setFocusable(false);
        edtMSSV.setEnabled(false);
        btnThem.setText("Hủy");
    }

    public void resetForm() {
        edtLop.setText("");
        edtTenSV.setText("");
        edtMSSV.setText("");
        btnThem.setText("Thêm");
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        edtMSSV.setEnabled(true);
        edtMSSV.setFocusable(true);
        edtMSSV.setFocusableInTouchMode(true);
        edtMSSV.requestFocus();
    }
}
