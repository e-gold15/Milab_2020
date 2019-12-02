package com.example.Game_of_throne;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.ex02.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Data> mData;
    Context context;

    public RecyclerViewAdapter(Context context, List<Data>data) {
        this.mData = data;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public MyViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image);
            mTextView = view.findViewById(R.id.image_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder)holder).mImageView.setImageResource(mData.get(position).image);
        ((MyViewHolder)holder).mTextView.setText(mData.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
