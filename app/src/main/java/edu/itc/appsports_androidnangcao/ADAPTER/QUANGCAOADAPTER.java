package edu.itc.appsports_androidnangcao.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.itc.appsports_androidnangcao.MODEL.QUANGCAO;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class QUANGCAOADAPTER extends RecyclerView.Adapter<QUANGCAOADAPTER.QUANGCAOVIEWHODER> {
    Context context;
    ArrayList<QUANGCAO> data;

    public QUANGCAOADAPTER(Context context, ArrayList<QUANGCAO> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public QUANGCAOVIEWHODER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_quangcao, parent, false);
        return new QUANGCAOVIEWHODER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QUANGCAOVIEWHODER holder, int position) {
        QUANGCAO quangcao = data.get(position);
        Picasso.get().load(SERVER.pathImages + quangcao.hinhquangcao).into(holder.imgQuangcao);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class QUANGCAOVIEWHODER extends RecyclerView.ViewHolder {
        ImageView imgQuangcao;

        public QUANGCAOVIEWHODER(@NonNull View itemView) {
            super(itemView);
            imgQuangcao = itemView.findViewById(R.id.imgQuangcao);
        }
    }
}
