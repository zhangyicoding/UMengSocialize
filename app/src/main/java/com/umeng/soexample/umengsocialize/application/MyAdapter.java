package com.umeng.soexample.umengsocialize.application;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context context;
    private List<String> datas;

    private OnChildClickListener onChildClickListener;
    private OnChildLongClickListener onChildLongClickListener;

    public MyAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<>();
    }

    private int getLayoutId() {
        return 0;
    }

    public void addDatas(List<String> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        itemView.setTag(holder);
        initItemViewListener(itemView);
        return holder;
    }

    private void initItemViewListener(View itemView) {
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View view) {
        int position;
        switch (view.getId()) {
            default:
                if (onChildClickListener != null) {
                    position = ((RecyclerView) view.getParent()).getChildLayoutPosition(view);
                    onChildClickListener.onChildClick(position);
                }
                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int position;
        switch (view.getId()) {
            default:
                if (onChildLongClickListener != null) {
                    position = ((RecyclerView) view.getParent()).getChildLayoutPosition(view);
                    onChildLongClickListener.onChildLongClick(position);
                }
                break;
        }
        return true;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

    public interface OnChildClickListener {
        public void onChildClick(int position);
    }

    public interface OnChildLongClickListener {
        public void onChildLongClick(int position);
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public void setOnChildLongClickListener(OnChildLongClickListener onChildLongClickListener) {
        this.onChildLongClickListener = onChildLongClickListener;
    }

}