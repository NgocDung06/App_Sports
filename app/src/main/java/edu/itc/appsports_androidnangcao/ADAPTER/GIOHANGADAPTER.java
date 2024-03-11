package edu.itc.appsports_androidnangcao.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import edu.itc.appsports_androidnangcao.Fragment.Fragment_giohang;
import edu.itc.appsports_androidnangcao.MODEL.SANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class GIOHANGADAPTER extends RecyclerView.Adapter<GIOHANGADAPTER.GIOHANGVIEWHODER> {

    private List<SANPHAM> data;
    private Context context;

    public GIOHANGADAPTER(List<SANPHAM> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GIOHANGVIEWHODER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chitiet_giohang, parent, false);
        return new GIOHANGVIEWHODER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GIOHANGVIEWHODER holder, int position) {
        SANPHAM sanpham = data.get(position);

        Picasso.get().load(SERVER.pathImages + sanpham.hinhsanpham).into(holder.imghinhgiohangg);
        holder.tvtengiohangg.setText(sanpham.tensanpham);

        String dongia = NumberFormat.getNumberInstance(Locale.getDefault()).format(Double.parseDouble(sanpham.dongia));
        holder.tvgiagiohangg.setText(dongia);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class GIOHANGVIEWHODER extends RecyclerView.ViewHolder {
        ImageView imghinhgiohangg;
        TextView tvtengiohangg, tvgiagiohangg;


        public GIOHANGVIEWHODER(@NonNull View itemView) {
            super(itemView);
            imghinhgiohangg = itemView.findViewById(R.id.imghinhgiohangg);
            tvtengiohangg = itemView.findViewById(R.id.tvtengiohangg);
            tvgiagiohangg = itemView.findViewById(R.id.tvgiagiohangg);
        }
    }
}
