package edu.itc.appsports_androidnangcao.ADAPTER;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.itc.appsports_androidnangcao.MODEL.THELOAISANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.main.Sanpham_theotheloaiMainActivity;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class THELOAISANPHAMADAPTER extends RecyclerView.Adapter<THELOAISANPHAMADAPTER.THELOAISANPHAMVIEWHODER> {
    Context context;
    ArrayList<THELOAISANPHAM> data;

    public THELOAISANPHAMADAPTER(Context context, ArrayList<THELOAISANPHAM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public THELOAISANPHAMVIEWHODER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_theloai,null);
        return new THELOAISANPHAMVIEWHODER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull THELOAISANPHAMVIEWHODER holder, int position) {
        THELOAISANPHAM theloaisanpham = data.get(position);
        Picasso.get().load(SERVER.pathImages+theloaisanpham.hinhtheloai).into(holder.imgTheloai);
        holder.tvTheloai.setText(theloaisanpham.tenthheloai);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Sanpham_theotheloaiMainActivity.class);
                intent.putExtra("theloaiobject",theloaisanpham);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class THELOAISANPHAMVIEWHODER extends RecyclerView.ViewHolder{
        ImageView imgTheloai;
        TextView tvTheloai;

        public THELOAISANPHAMVIEWHODER(@NonNull View itemView) {
            super(itemView);
            imgTheloai = itemView.findViewById(R.id.imghinhtheloai);
            tvTheloai = itemView.findViewById(R.id.tvtentheloai);
        }
    }
}
