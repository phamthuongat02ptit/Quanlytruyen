package com.example.quanlytruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlytruyen.MainActivity;
import com.example.quanlytruyen.R;
import com.example.quanlytruyen.model.Truyen;

import java.util.List;

public class TruyenAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Truyen> truyenList;
    int soSao;

    public TruyenAdapter(MainActivity context, int layout, List<Truyen> truyenList) {
        this.context = context;
        this.layout = layout;
        this.truyenList = truyenList;
    }

    @Override
    public int getCount() {
        return truyenList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTenTruyen;
        TextView txtTheLoaiTruyen;
        TextView txtDanhGiaTruyen;
        ImageView imgXoa;
        ImageView imgSua;
        ImageView imgHinhTruyen;
        TextView txtMotaDongTruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            //anh xa
            viewHolder.txtTenTruyen = (TextView) convertView.findViewById(R.id.idTenTruyen);
            viewHolder.txtTheLoaiTruyen = (TextView) convertView.findViewById(R.id.idTheLoaiTruyen);
            viewHolder.txtDanhGiaTruyen = (TextView) convertView.findViewById(R.id.idDanhGiaTruyen);
            viewHolder.imgXoa = (ImageView) convertView.findViewById(R.id.idXoa);
            viewHolder.imgSua = (ImageView) convertView.findViewById(R.id.idSua);
            viewHolder.imgHinhTruyen = (ImageView) convertView.findViewById(R.id.idHinhTruyen);
            viewHolder.txtMotaDongTruyen = (TextView) convertView.findViewById(R.id.idMotaDongTruyen);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Truyen truyen = truyenList.get(position);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());
        viewHolder.txtTheLoaiTruyen.setText(truyen.getTheLoaiTruyen());
        soSao = Integer.parseInt(truyen.getDanhGiaTruyen()+"");
        viewHolder.txtDanhGiaTruyen.setText(Trave(soSao));

        viewHolder.txtMotaDongTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Manhinhkach(truyen.getIdTruyen(), truyen.getTenTruyen(), truyen.getTheLoaiTruyen(), truyen.getMoTaTruyen(), truyen.getLinkTruyen(), truyen.getDanhGiaTruyen());

            }
        });

        viewHolder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSua(truyen.getIdTruyen(), truyen.getTenTruyen(), truyen.getTheLoaiTruyen(), truyen.getMoTaTruyen(), truyen.getLinkTruyen(), truyen.getDanhGiaTruyen());
            }
        });

        viewHolder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoa(truyen.getTenTruyen(), truyen.getIdTruyen());
            }
        });

        return convertView;
    }
    public String Trave(int n){
        String a = "";
        for(int i=0; i<n; i++){
            a = a+"*";
        }
        return a;
    }



}
