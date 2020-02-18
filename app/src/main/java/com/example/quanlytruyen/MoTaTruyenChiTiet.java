package com.example.quanlytruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlytruyen.model.Truyen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MoTaTruyenChiTiet extends AppCompatActivity {
    ImageView imganhNhan;
    TextView txtTenNhan;
    TextView txtTheLoaiNhan;
    TextView txtDanhGiaNhan;
    TextView txtMoTaNhan;
    Button btnLinkNhan;
    int soSao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_ta_truyen_chi_tiet);

        imganhNhan = (ImageView) findViewById(R.id.anhNhan);
        txtTenNhan = (TextView) findViewById(R.id.tenNhan);
        txtTheLoaiNhan = (TextView) findViewById(R.id.theLoaiNhan);
        txtDanhGiaNhan = (TextView) findViewById(R.id.danhGiaNhan);
        txtMoTaNhan = (TextView) findViewById(R.id.moTaNhan);
        btnLinkNhan = (Button) findViewById(R.id.linkNhan);


        Intent intent = getIntent();
        final Truyen truyennhan = (Truyen) intent.getSerializableExtra("dulieu");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoadImage().execute(truyennhan.getLinkTruyen());
            }
        });
        btnLinkNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Browser1(truyennhan.getLinkTruyen());
            }
        });



        soSao = truyennhan.getDanhGiaTruyen();
        txtTenNhan.setText(truyennhan.getTenTruyen());
        txtTheLoaiNhan.setText(truyennhan.getTheLoaiTruyen());
        txtDanhGiaNhan.setText(TraveSao(soSao));
        txtMoTaNhan.setText(truyennhan.getMoTaTruyen());
    }

    class LoadImage extends AsyncTask<String, Integer, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap b = null;
            try {
                URL u = new URL(strings[0]);
                b = BitmapFactory.decodeStream(u.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return b;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imganhNhan.setImageBitmap(bitmap);
        }
    }

    public void Browser1(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }
    public String TraveSao(int n){
        String a = "";
        for(int i=0; i<n; i++){
            a = a+"*";
        }
        return a;
    }


}
