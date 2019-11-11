package com.example.pc.luugiakhanh16019591;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    private Context context;
    private int resource;
    private ArrayList<SinhVien> listSV;

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SinhVien> listSV) {
        super(context, resource, listSV);
        this.context = context;
        this.resource = resource;
        this.listSV = listSV;
    }

    class ViewHolder {
        TextView tvMSSV, tvTenSV, tvLop;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sv_item_lv, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMSSV = convertView.findViewById(R.id.tvMSSV);
            viewHolder.tvTenSV = convertView.findViewById(R.id.tvTenSV);
            viewHolder.tvLop = convertView.findViewById(R.id.tvLop);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SinhVien sinhVien = listSV.get(position);
        viewHolder.tvMSSV.setText(String.valueOf(sinhVien.getMssv()));
        viewHolder.tvTenSV.setText(sinhVien.getTenSV());
        viewHolder.tvLop.setText(sinhVien.getLop());
        return convertView;
    }
}
