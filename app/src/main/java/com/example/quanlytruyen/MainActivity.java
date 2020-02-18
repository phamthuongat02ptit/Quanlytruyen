package com.example.quanlytruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlytruyen.adapter.TruyenAdapter;
import com.example.quanlytruyen.database.DataBase;
import com.example.quanlytruyen.model.Truyen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    ListView listViewTruyen;
    ArrayList<Truyen> truyenArrayList;
    TruyenAdapter adapterTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anhxa listViewTruyen
        listViewTruyen = (ListView) findViewById(R.id.idListViewTruyen);
        truyenArrayList = new ArrayList<>();
        adapterTruyen = new TruyenAdapter(this, R.layout.dongtruyen, truyenArrayList);
        listViewTruyen.setAdapter(adapterTruyen);

        //tao database
        dataBase = new DataBase(this, "quanlytruyen.sqlite", null, 1);
        //tao bang
        dataBase.QueryData("CREATE TABLE IF NOT EXISTS Truyen(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenTruyen VARCHAR(200), TheLoai VARCHAR(200), MoTa VARCHAR(1000), Link VARCHAR(1000), DanhGia INTEGER)");
        //them data
//        dataBase.QueryData("INSERT INTO Truyen VALUES(null, 'Co Be Quang Khan Do', 'Co Tich', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac', 'link hien thoi chua co', 3)");
//        dataBase.QueryData("INSERT INTO Truyen VALUES(null, 'Doremon', 'Co Tich', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac', 'link hien thoi chua co', 2)");
//        dataBase.QueryData("INSERT INTO Truyen VALUES(null, 'Conan', 'Co Tich', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac', 'link hien thoi chua co', 4)");
//        dataBase.QueryData("INSERT INTO Truyen VALUES(null, 'Chien Binh Rong', 'Than Thoai', 'Ke ve mot chien binh qua cam danh bai ke ac', 'link hien thoi chua co', 5)");
//        dataBase.QueryData("INSERT INTO Truyen VALUES(null, 'Conan 2', 'Co Tich', 'Ke ve mt co be mang com cho ba ga gap con soi gian ac', 'link hien thoi chua co', 1)");
        //lay du lieu ra
        GetData();
    }

    private void GetData() {
        Cursor dataTruyen = dataBase.GetData("SELECT * FROM Truyen");
        truyenArrayList.clear();
        while (dataTruyen.moveToNext()){
            int id = dataTruyen.getInt(0);
            String ten = dataTruyen.getString(1);
            String theloai = dataTruyen.getString(2);
            String mota = dataTruyen.getString(3);
            String link = dataTruyen.getString(4);
            int danhgia = dataTruyen.getInt(5);
            truyenArrayList.add(new Truyen(id, ten, theloai, mota, link, danhgia));

        }
        adapterTruyen.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_truyen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.idMenuAdd){
            DialogThem();
        }
        if(item.getItemId() == R.id.idDanhSachTheLoai){
            Intent intent = new Intent(MainActivity.this, TheLoaiActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void Manhinhkach(int id, String tenTruyen, String theLoaiTruyen, String moTaTruyen, String linkTruyen, int danhGiaTruyen){
        Intent intent = new Intent(MainActivity.this, MoTaTruyenChiTiet.class);
        Truyen truyenget = new Truyen(id, tenTruyen, theLoaiTruyen, moTaTruyen, linkTruyen, danhGiaTruyen);
        intent.putExtra("dulieu", truyenget);
        startActivity(intent);
    }
    public void DialogXoa(final String tenTruyen, final int id){
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Ban co muon xoa Truyen " + tenTruyen +" nay khong?");
        dialogxoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase.QueryData("DELETE FROM Truyen WHERE Id = '"+id+"'");
                Toast.makeText(MainActivity.this, "Da xoa " + tenTruyen, Toast.LENGTH_SHORT).show();
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

    public void DialogSua(final int id, String tenTruyenSua, String theLoaiTruyenSua, String moTaTruyenSua, String linkTruyenSua, int danhGiaTruyenSua){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_suatruyen);

        final EditText edtTenTruyenSua = (EditText) dialog.findViewById(R.id.idTenTruyenSua);
        final EditText edtTheLoaiTruyenSua = (EditText) dialog.findViewById(R.id.idTheLoaiTruyenSua);
        final EditText edtMoTaTruyenSua = (EditText) dialog.findViewById(R.id.idMoTaTruyenSua);
        final EditText edtLinkTruyenSua = (EditText) dialog.findViewById(R.id.idLinkTruyenSua);
        final EditText edtDanhGiaTruyenSua = (EditText) dialog.findViewById(R.id.idDanhGiaTruyenSua);
        Button btnSuaTruyen = (Button) dialog.findViewById(R.id.idBtnSuaTruyen);
        Button btnHuySuaTruyen = (Button) dialog.findViewById(R.id.idBtnHuySuaTruyen);

        edtTenTruyenSua.setText(tenTruyenSua);
        edtTheLoaiTruyenSua.setText(theLoaiTruyenSua);
        edtMoTaTruyenSua.setText(moTaTruyenSua);
        edtLinkTruyenSua.setText(linkTruyenSua);
        edtDanhGiaTruyenSua.setText(danhGiaTruyenSua+"");

        btnSuaTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTruyenSua = edtTenTruyenSua.getText().toString();
                String theLoaiTruyenSua = edtTheLoaiTruyenSua.getText().toString();
                String moTaTruyenSua = edtMoTaTruyenSua.getText().toString();
                String linkTruyenSua = edtLinkTruyenSua.getText().toString();
                int danhGiaTruyenSua = Integer.parseInt(edtDanhGiaTruyenSua.getText().toString());
                if(tenTruyenSua.equals("")||theLoaiTruyenSua.equals("")||moTaTruyenSua.equals("")||linkTruyenSua.equals("")){
                    Toast.makeText(MainActivity.this, "Vui long nhap day du", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBase.QueryData("UPDATE Truyen SET TenTruyen = '"+tenTruyenSua+"', TheLoai = '"+theLoaiTruyenSua+"', MoTa = '"+moTaTruyenSua+"', Link = '"+linkTruyenSua+"', DanhGia = '"+danhGiaTruyenSua+"' WHERE Id = '"+id+"'");
                    Toast.makeText(MainActivity.this, "da sua", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetData();
                }
            }
        });

        btnHuySuaTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void DialogThem(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_truyen);

        final EditText edtTenTruyenThem = (EditText) dialog.findViewById(R.id.idTenTruyenThem);
        final EditText edtTheLoaiTruyenThem = (EditText) dialog.findViewById(R.id.idTheLoaiTruyenThem);
        final EditText edtMoTaTruyenThem = (EditText) dialog.findViewById(R.id.idMoTaTruyenThem);
        final EditText edtLinkTruyenThem = (EditText) dialog.findViewById(R.id.idLinkTruyenThem);
        final EditText edtDanhGiaTruyenThem = (EditText) dialog.findViewById(R.id.idDanhGiaTruyenThem);
        Button btnThemTruyen = (Button) dialog.findViewById(R.id.idBtnThemTruyen);
        Button btnHuyTruyen = (Button) dialog.findViewById(R.id.idBtnHuyThemTruyen);

        btnThemTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTruyenThem = edtTenTruyenThem.getText().toString();
                String theLoaiTruyenThem = edtTheLoaiTruyenThem.getText().toString();
                String moTaTruyenThem = edtMoTaTruyenThem.getText().toString();
                String linkTruyenThem = edtLinkTruyenThem.getText().toString();
                int danhGiaTruyenThem = Integer.parseInt(edtDanhGiaTruyenThem.getText().toString());

                if(tenTruyenThem.equals("")||theLoaiTruyenThem.equals("")||moTaTruyenThem.equals("")||linkTruyenThem.equals("")){
                    Toast.makeText(MainActivity.this, "Vui long nhap day du", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataBase.QueryData("INSERT INTO Truyen VALUES(null, '"+tenTruyenThem+"', '"+theLoaiTruyenThem+"', '"+moTaTruyenThem+"', '"+linkTruyenThem+"', '"+danhGiaTruyenThem+"')");
                    Toast.makeText(MainActivity.this, "da them", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetData();
                }
            }
        });

        btnHuyTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }


}
