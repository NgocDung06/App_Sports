package edu.itc.appsports_androidnangcao.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import edu.itc.appsports_androidnangcao.Fragment.Fragment_giohang;
import edu.itc.appsports_androidnangcao.MODEL.SANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class Chitiet_SanphamMainActivity extends AppCompatActivity {
    ImageView imgchitietHinhsanpham;
    TextView tvchitietTensanpham, tvcvhitietGiasanpham, tvchitietMota;
    AppCompatButton btnchitietGiohang, btnchitietMua;
    private SANPHAM sanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sanpham_main);

        anhxa();

        Intent intent = getIntent();
        this.sanpham = (SANPHAM) intent.getSerializableExtra("sanpham");
        Picasso.get().load(SERVER.pathImages+sanpham.hinhsanpham).into(imgchitietHinhsanpham);
        tvchitietTensanpham.setText(sanpham.tensanpham);
        tvcvhitietGiasanpham.setText(sanpham.dongia);
        tvchitietMota.setText(sanpham.mota);

        String dongia = NumberFormat.getNumberInstance(Locale.getDefault()).format(Double.parseDouble(sanpham.dongia));
        tvcvhitietGiasanpham.setText(dongia);


        btnchitietMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Bundle để truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putSerializable("giohang", sanpham);

                // Tạo Fragment_giohang mới và truyền dữ liệu
                Fragment_giohang fragment_giohang = new Fragment_giohang();
                fragment_giohang.setArguments(bundle);


                Intent intent1 = new Intent(Chitiet_SanphamMainActivity.this, Giohang_Activity.class);

                Fragment_giohang.listGioHang.add(sanpham);
                Toast.makeText(Chitiet_SanphamMainActivity.this, sanpham.tensanpham, Toast.LENGTH_SHORT).show();
                startActivity(intent1);

            }
        });

    }
    private void anhxa() {
        imgchitietHinhsanpham = findViewById(R.id.imgchitietHinhsanpham);
        tvchitietTensanpham = findViewById(R.id.tvchitietTensanpham);
        tvcvhitietGiasanpham = findViewById(R.id.tvchitietGiasanpham);
        tvchitietMota = findViewById(R.id.tvchitietMota);
        btnchitietGiohang = findViewById(R.id.btnchitietGiohang);
        btnchitietMua = findViewById(R.id.btnchitietMua);
    }
}