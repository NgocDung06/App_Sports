package edu.itc.appsports_androidnangcao.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import edu.itc.appsports_androidnangcao.MODEL.SANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.main.Chitiet_SanphamMainActivity;
import edu.itc.appsports_androidnangcao.main.Sanpham_theotheloaiMainActivity;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class SANPHAMADAPTER extends RecyclerView.Adapter<SANPHAMADAPTER.SANPHAMVIEWHODER> {
    Context context;
    ArrayList<SANPHAM> data;

    public SANPHAMADAPTER(Context context, ArrayList<SANPHAM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SANPHAMVIEWHODER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_sanpham,null);
        return new SANPHAMVIEWHODER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SANPHAMVIEWHODER holder, int position) {
        SANPHAM sanpham = data.get(position);
        Picasso.get().load(SERVER.pathImages+sanpham.hinhsanpham).into(holder.imghinhSanpham);
        String tensanpham = sanpham.tensanpham;
        if (tensanpham.length()>11)
            tensanpham = tensanpham.substring(0,11)+"...";
        holder.tvtenSanpham.setText(tensanpham);

        String dongia = NumberFormat.getNumberInstance(Locale.getDefault()).format(Double.parseDouble(sanpham.dongia));
        holder.tvgiaSanpham.setText(dongia);

        holder.imghinhSanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chitiet_SanphamMainActivity.class);
                intent.putExtra("sanpham",sanpham);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class SANPHAMVIEWHODER extends RecyclerView.ViewHolder{
        ImageView imghinhSanpham;
        TextView tvtenSanpham, tvgiaSanpham;

        public SANPHAMVIEWHODER(@NonNull View itemView) {
            super(itemView);
            imghinhSanpham = itemView.findViewById(R.id.imghinhSanpham);
            tvtenSanpham = itemView.findViewById(R.id.tvtenSanphamm);
            tvgiaSanpham = itemView.findViewById(R.id.tvgiaSanpham);
        }
    }
}
