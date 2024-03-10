package edu.itc.appsports_androidnangcao.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.itc.appsports_androidnangcao.ADAPTER.CHUDESANPHAMADAPTER;
import edu.itc.appsports_androidnangcao.ADAPTER.SANPHAMADAPTER;
import edu.itc.appsports_androidnangcao.ADAPTER.THELOAISANPHAMADAPTER;
import edu.itc.appsports_androidnangcao.MODEL.CHUDESANPHAM;
import edu.itc.appsports_androidnangcao.MODEL.QUANGCAO;
import edu.itc.appsports_androidnangcao.MODEL.SANPHAM;
import edu.itc.appsports_androidnangcao.MODEL.THELOAISANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class Fragment_Home extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView rvchude, rvtheloai, rvSanphambanchay;
    ArrayList<CHUDESANPHAM> chude_data = new ArrayList<>();
    CHUDESANPHAMADAPTER chudesanphamadapter;
    ArrayList<QUANGCAO> layquangcao = new ArrayList<>();
    ArrayList<THELOAISANPHAM> theloai_data = new ArrayList<>();
    THELOAISANPHAMADAPTER theloaisanphamadapter;
    ArrayList<SANPHAM> sanpham_data = new ArrayList<>();
    SANPHAMADAPTER sanphamadapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_hom_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        chudesanphamadapter = new CHUDESANPHAMADAPTER(getContext(), chude_data);
        rvchude.setAdapter(chudesanphamadapter);
        rvchude.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        theloaisanphamadapter = new THELOAISANPHAMADAPTER(getContext(),theloai_data);
        rvtheloai.setAdapter(theloaisanphamadapter);
        rvtheloai.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.HORIZONTAL,false));

        sanphamadapter = new SANPHAMADAPTER(getContext(),sanpham_data);
        rvSanphambanchay.setAdapter(sanphamadapter);
        rvSanphambanchay.setLayoutManager(new GridLayoutManager(getContext(),2));


        loadchude();
        loadquangcao();
        loadtheloai();
        loadsanpham();
    }

    private void loadsanpham() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length();i++)
                {
                    try {
                        JSONObject sanphamobject = response.getJSONObject(i);
                        SANPHAM sanpham = new SANPHAM(
                                sanphamobject.getString("idsanpham"),
                                sanphamobject.getString("idtheloai"),
                                sanphamobject.getString("tensanpham"),
                                sanphamobject.getString("hinhsanpham"),
                                sanphamobject.getString("mota"),
                                sanphamobject.getString("dongia"),
                                sanphamobject.getString("ngaycapnhat"),
                                sanphamobject.getString("luotmua"));
                        sanpham_data.add(sanpham);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "loi phuong thuc thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
                sanphamadapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi phuong thuc that bai", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urllaysanpham,thanhcong,thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void loadtheloai() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++)
                {
                    try {
                        JSONObject theloaiobject = response.getJSONObject(i);
                        THELOAISANPHAM theloaisanpham = new THELOAISANPHAM(
                                theloaiobject.getString("idtheloai"),
                                theloaiobject.getString("idchude"),
                                theloaiobject.getString("tentheloai"),
                                theloaiobject.getString("hinhtheloai"));
                        theloai_data.add(theloaisanpham);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Loi thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
                theloaisanphamadapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Loi that bai", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urllaytheloai,thanhcong,thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void loadquangcao() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject quangcaoobject = response.getJSONObject(i);
                        QUANGCAO quangcao = new QUANGCAO(quangcaoobject.getString("idquangcao"),
                                quangcaoobject.getString("idsanpham"),
                                quangcaoobject.getString("hinhquuangcao"));
                        layquangcao.add(quangcao);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("loiquuangcao", e.getMessage());
                        Toast.makeText(getContext(), "Loi phuong thuc thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
                // Load quangcao
                loadQuangcaoToViewFlipper();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi phuong thuc that bai", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urllayquangcao, thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void loadQuangcaoToViewFlipper() {
        for (QUANGCAO quangcao : layquangcao) {
            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(SERVER.pathImages + quangcao.hinhquangcao).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
    }

    private void loadchude() {
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject chudeobject = response.getJSONObject(i);
                        CHUDESANPHAM chude = new CHUDESANPHAM(chudeobject.getString("id"),
                                chudeobject.getString("tensanpham"),
                                chudeobject.getString("hinhsanpham"));
                        chude_data.add(chude);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Loichude", e.getMessage());
                        Toast.makeText(getContext(), "loi phuong thuc thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
                chudesanphamadapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi phuong thuc that bai", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urllaychude, thanhcong, thatbai);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void anhxa(View view) {
        rvchude = view.findViewById(R.id.rvChude);
        rvtheloai = view.findViewById(R.id.rvTheloai);
        viewFlipper = view.findViewById(R.id.viewflipper);
        rvSanphambanchay = view.findViewById(R.id.rvSanphambanchay);
    }
}
