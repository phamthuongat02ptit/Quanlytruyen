package com.example.quanlytruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlytruyen.adapter.TheLoaiAdapter;
import com.example.quanlytruyen.database.DataBase;
import com.example.quanlytruyen.model.TheLoaiTruyen;

import java.util.ArrayList;

public class TheLoaiActivity extends AppCompatActivity {
    ListView listViewTheLoai;
    ArrayList<TheLoaiTruyen> theLoaiTruyenArrayList;
    TheLoaiAdapter adapter;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

        listViewTheLoai = (ListView) findViewById(R.id.listViewTheLoai);
        theLoaiTruyenArrayList = new ArrayList<>();
        adapter = new TheLoaiAdapter(this, R.layout.dongtheloai, theLoaiTruyenArrayList);
        listViewTheLoai.setAdapter(adapter);

        dataBase = new DataBase(this, "quanlytheloai.sqlite", null, 1);
        //tao bang
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS TheLoai(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenTheLoai VARCHAR(200), MoTaTheLoai VARCHAR(1000))");

//        dataBase.QueryData("INSERT INTO TheLoai VALUES(null, 'Co Tich', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac')");
//        dataBase.QueryData("INSERT INTO TheLoai VALUES(null, 'Hai Hươc', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac')");
//        dataBase.QueryData("INSERT INTO TheLoai VALUES(null, 'Truyen Thuyet', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac')");
//        dataBase.QueryData("INSERT INTO TheLoai VALUES(null, 'Tam Ly', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac')");

        GetData();

    }
    private void GetData() {
        Cursor dataTheLoaiTruyen = dataBase.GetData("SELECT * FROM TheLoai");
        theLoaiTruyenArrayList.clear();
        while (dataTheLoaiTruyen.moveToNext()){
            int id = dataTheLoaiTruyen.getInt(0);
            String tenTheloai = dataTheLoaiTruyen.getString(1);
            String moTaTheloai = dataTheLoaiTruyen.getString(2);
            theLoaiTruyenArrayList.add(new TheLoaiTruyen(id, tenTheloai, moTaTheloai));

        }
        adapter.notifyDataSetChanged();
    }
    public void DialogXoa(final int id){
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Ban co muon xoa nay khong?");
        dialogxoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase.QueryData("DELETE FROM TheLoai WHERE Id = '"+id+"'");
                Toast.makeText(TheLoaiActivity.this, "Da xoa ", Toast.LENGTH_SHORT).show();
                GetData();
            }
        });
        dialogxoa.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogxoa.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_theloai, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.idMenuAddTheLoai){
            DialogThem1();
        }
        return super.onOptionsItemSelected(item);
    }
    private void DialogThem1(){

        final Dialog dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog_themtheloai);

        final EditText edtTenTheLoai = (EditText) dialog1.findViewById(R.id.tenTheLoaiThem);
        final EditText edtMotaTheLoai = (EditText) dialog1.findViewById(R.id.moTaTheLoaiThem);
        final Button btnTheLoaiThem = (Button) dialog1.findViewById(R.id.btnThemTheLoai);
        Button btnTheLoaiHuy = (Button) dialog1.findViewById(R.id.btnHuyThemTheLoai);

        btnTheLoaiThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTheloai = edtTenTheLoai.getText().toString();
                String moTaTheloai = edtMotaTheLoai.getText().toString();
                dataBase.QueryData("INSERT INTO TheLoai VALUES(null, '"+tenTheloai+"', '"+moTaTheloai+"')");
                Toast.makeText(TheLoaiActivity.this, "da them", Toast.LENGTH_SHORT).show();
                dialog1.dismiss();
                GetData();

            }
        });
        btnTheLoaiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }

    public void DialogSua1(final int id, String tenTheloaiSua, String moTaTheloaiSua){

        final Dialog dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.dialog_suatheloai);

        final EditText edtTenTheLoai = (EditText) dialog1.findViewById(R.id.tenTheLoaiSua);
        final EditText edtMotaTheLoai = (EditText) dialog1.findViewById(R.id.moTaTheLoaiSua);
        final Button btnTheLoaiThem = (Button) dialog1.findViewById(R.id.btnSuaTheLoai);
        Button btnTheLoaiHuy = (Button) dialog1.findViewById(R.id.btnHuySuaTheLoai);

        edtTenTheLoai.setText(tenTheloaiSua);
        edtMotaTheLoai.setText(moTaTheloaiSua);

        btnTheLoaiThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTheloai = edtTenTheLoai.getText().toString();
                String moTaTheloai = edtMotaTheLoai.getText().toString();
                dataBase.QueryData("UPDATE TheLoai SET TenTheLoai = '"+tenTheloai+"', MoTaTheLoai = '"+moTaTheloai+"' WHERE Id = '"+id+"'");
                Toast.makeText(TheLoaiActivity.this, "da sua", Toast.LENGTH_SHORT).show();
                dialog1.dismiss();
                GetData();

            }
        });
        btnTheLoaiHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }
}
