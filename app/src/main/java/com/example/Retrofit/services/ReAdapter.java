package com.example.Retrofit.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Retrofit.R;
import com.example.Retrofit.databinding.ItemBinding;
import com.example.Retrofit.model.HomeDeliverReq;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.RecHolder> {
    ArrayList<HomeDeliverReq> datumArrayList;

    public ReAdapter(ArrayList<HomeDeliverReq> datumArrayList) {
        this.datumArrayList = datumArrayList;
    }


    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false); // اضافة التصميم
        return new RecHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        HomeDeliverReq datum = datumArrayList.get(position);
        //
        holder.bind(datum);
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    //حجم التكرا عدد العناصر
    @Override
    public int getItemCount() {
        return datumArrayList.size();
    }

    static public class RecHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;
        Context context;

        public RecHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemBinding.bind(itemView);
            // احضار الكونتكس
            context = itemView.getContext();
        }


        //تعبئة بيانات اليوزر بالتصميم
        void bind(HomeDeliverReq datum) {
            Glide.with(context).load(datum.getPhotoOrderHome().getPhoto()).into(binding.image);
            binding.id.setText(datum.getId() + "");
            binding.workname.setText("Servese tybe :" + datum.getWork().getName());
            binding.date.setText(datum.getCreatedAt());
        }
    }
}
