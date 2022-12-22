package com.example.Retrofit.serr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Retrofit.R;
import com.example.Retrofit.Sett.Datum;
import com.example.Retrofit.databinding.ItemBinding;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.RecHolder> {
    ArrayList<Datum> datumArrayList;

    public ReAdapter(ArrayList<Datum> datumArrayList) {
        this.datumArrayList = datumArrayList;
    }


    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false); // اضافة التصميم
        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        Datum datum = datumArrayList.get(position);
        //
        holder.bind(datum);

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
        void bind(Datum datum) {
            Glide.with(context).load(datum.getPhoto()).into(binding.image);
            binding.id.setText(datum.getId() + "");
            binding.workname.setText("Servese tybe :" + datum.getWork().getName());
            binding.date.setText(datum.getCreatedAt());
         }
    }
}
