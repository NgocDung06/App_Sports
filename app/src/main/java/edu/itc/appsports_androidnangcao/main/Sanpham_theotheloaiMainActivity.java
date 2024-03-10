package edu.itc.appsports_androidnangcao.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.itc.appsports_androidnangcao.ADAPTER.SANPHAMADAPTER;
import edu.itc.appsports_androidnangcao.MODEL.SANPHAM;
import edu.itc.appsports_androidnangcao.MODEL.THELOAISANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class Sanpham_theotheloaiMainActivity extends AppCompatActivity {

    MaterialToolbar toolbarsachtheotheloai;
    RecyclerView rvsanphamtheotheloai ;

    ArrayList<SANPHAM> sanpham_data = new ArrayList<>();
    SANPHAMADAPTER sanphamadapter;
    THELOAISANPHAM theloai;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_theotheloai_main);

        toolbarsachtheotheloai = findViewById(R.id.toolbarSanphamtheotheloai);
        rvsanphamtheotheloai = findViewById(R.id.rvSanphamtheotheloai);

        //chuyển từ thể loại sang sản phẩm
        Intent intent = getIntent();
        theloai = (THELOAISANPHAM) intent.getSerializableExtra("theloaiobject");



        sanphamadapter = new SANPHAMADAPTER(this,sanpham_data);
        rvsanphamtheotheloai.setAdapter(sanphamadapter);
        rvsanphamtheotheloai.setLayoutManager(new GridLayoutManager(this,2));

        setSupportActionBar(toolbarsachtheotheloai);
        getSupportActionBar().setTitle("");
        toolbarsachtheotheloai.setNavigationIcon(R.drawable.baseline_arrow_back_2444);
        toolbarsachtheotheloai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadsanpham();
    }

    private void loadsanpham() {
        Response.Listener<String> thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Chuyển đổi dữ liệu JSON từ máy chủ sang chuỗi UTF-8
                    String utfResponse = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                    JSONArray jsonArray = new JSONArray(utfResponse);

                    // Xóa dữ liệu cũ trước khi thêm dữ liệu mới
                    sanpham_data.clear();

                    // Duyệt qua mỗi phần tử trong mảng JSON
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sanphamobject = jsonArray.getJSONObject(i);
                        // Tạo đối tượng SANPHAM từ dữ liệu JSON
                        SANPHAM sanpham = new SANPHAM(
                                sanphamobject.getString("idsanpham"),
                                sanphamobject.getString("idtheloai"),
                                sanphamobject.getString("tensanpham"),
                                sanphamobject.getString("hinhsanpham"),
                                sanphamobject.getString("mota"),
                                sanphamobject.getString("dongia"),
                                sanphamobject.getString("ngaycapnhat"),
                                sanphamobject.getString("luotmua")
                        );
                        // Thêm SANPHAM vào ArrayList
                        sanpham_data.add(sanpham);
                    }

                    // Cập nhật RecyclerView sau khi có dữ liệu mới
                    sanphamadapter.notifyDataSetChanged();
                } catch (JSONException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Toast.makeText(Sanpham_theotheloaiMainActivity.this, "Lỗi khi xử lý dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        // Xử lý lỗi nếu có
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sanpham_theotheloaiMainActivity.this, "Lỗi khi kết nối máy chủ: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // Tạo và thực hiện StringRequest để lấy dữ liệu từ máy chủ
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.urllaysanphamtheotheloai, thanhcong, thatbai) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Truyền các tham số yêu cầu đến máy chủ (nếu có)
                HashMap<String, String> params = new HashMap<>();
                params.put("idtheloai", theloai.idtheloai);
                return params;
            }
        };

        // Thêm StringRequest vào RequestQueue để thực hiện yêu cầu
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
