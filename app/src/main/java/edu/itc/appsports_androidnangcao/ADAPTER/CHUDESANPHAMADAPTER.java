package edu.itc.appsports_androidnangcao.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.itc.appsports_androidnangcao.MODEL.CHUDESANPHAM;
import edu.itc.appsports_androidnangcao.R;
import edu.itc.appsports_androidnangcao.server.SERVER;

public class CHUDESANPHAMADAPTER extends RecyclerView.Adapter<CHUDESANPHAMADAPTER.CHUDESANPHAMVIEWHODER> {
    Context context;
    ArrayList<CHUDESANPHAM> data;

    public CHUDESANPHAMADAPTER(Context context, ArrayList<CHUDESANPHAM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CHUDESANPHAMVIEWHODER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dong_chude,null);
        return new CHUDESANPHAMVIEWHODER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHUDESANPHAMVIEWHODER holder, int position) {
        CHUDESANPHAM chudesanpham = data.get(position);
        Picasso.get().load(SERVER.pathImages+chudesanpham.hinhsanpham).into(holder.imghinhchude);
        holder.tvtenchude.setText(chudesanpham.tensanpham);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CHUDESANPHAMVIEWHODER extends RecyclerView.ViewHolder{

        ImageView imghinhchude;
        TextView tvtenchude;

        public CHUDESANPHAMVIEWHODER(@NonNull View itemView) {
            super(itemView);
            imghinhchude = itemView.findViewById(R.id.imghinhchude);
            tvtenchude = itemView.findViewById(R.id.tvtenchude);
        }
    }
}
