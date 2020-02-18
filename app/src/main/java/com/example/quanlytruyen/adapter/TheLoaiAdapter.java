package com.example.quanlytruyen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlytruyen.R;
import com.example.quanlytruyen.TheLoaiActivity;
import com.example.quanlytruyen.model.TheLoaiTruyen;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private TheLoaiActivity contex1;
    private int layout1;
    private List<TheLoaiTruyen> theLoaiTruyenList;

    public TheLoaiAdapter(TheLoaiActivity contex1, int layout1, List<TheLoaiTruyen> theLoaiTruyenList) {
        this.contex1 = contex1;
        this.layout1 = layout1;
        this.theLoaiTruyenList = theLoaiTruyenList;
    }

    @Override
    public int getCount() {
        return theLoaiTruyenList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder1{
        TextView txtTheLoai;
        TextView txtMoTaTheLoai;
        TextView txtXoaTL;
        TextView txtSuaTL;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        if(convertView == null){
            viewHolder1 = new ViewHolder1();
            LayoutInflater inflater = (LayoutInflater) contex1.getSystemService(TheLoaiActivity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout1, null);
            viewHolder1.txtTheLoai = (TextView) convertView.findViewById(R.id.tenTheloai);
            viewHolder1.txtMoTaTheLoai = (TextView) convertView.findViewById(R.id.moTaTheLoai);
            viewHolder1.txtXoaTL = (TextView) convertView.findViewById(R.id.xoaTheLoai);
            viewHolder1.txtSuaTL = (TextView) convertView.findViewById(R.id.suaTheLoai);
            convertView.setTag(viewHolder1);
        }
        else {
            viewHolder1 = (ViewHolder1) convertView.getTag();
        }
        final TheLoaiTruyen theLoaiTruyen = theLoaiTruyenList.get(position);
        viewHolder1.txtTheLoai.setText(theLoaiTruyen.getTenTheLoai());
        viewHolder1.txtMoTaTheLoai.setText(theLoaiTruyen.getMoTaTheLoai());
        viewHolder1.txtXoaTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contex1.DialogXoa(theLoaiTruyen.getIdTruyen());
            }
        });
        viewHolder1.txtSuaTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contex1.DialogSua1(theLoaiTruyen.getIdTruyen(), theLoaiTruyen.getTenTheLoai(), theLoaiTruyen.getMoTaTheLoai());
            }
        });

        return convertView;
    }
}
