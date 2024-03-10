package edu.itc.appsports_androidnangcao.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.itc.appsports_androidnangcao.ADAPTER.GIOHANGADAPTER;
import edu.itc.appsports_androidnangcao.MODEL.SANPHAM;
import edu.itc.appsports_androidnangcao.R;

public class Fragment_giohang extends Fragment {
    public static ArrayList<SANPHAM> listGioHang = new ArrayList<>();
    private RecyclerView recyclerView;
    private GIOHANGADAPTER giohangadapter;
    private TextView tvGiohang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giohang, container, false);
        tvGiohang = view.findViewById(R.id.textView2);
        recyclerView = view.findViewById(R.id.rvframegiohang);

        setupRecycleview(listGioHang);
        return view;
    }

    private void setupRecycleview(List<SANPHAM> list) {
        giohangadapter = new GIOHANGADAPTER(list, getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(giohangadapter);
    }
}
