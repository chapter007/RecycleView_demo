package com.zhangjie.adm.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adm on 2015/10/30.
 */
class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    private Context context;
    private List<String> mDatas;
    private List<Integer> mHeight;
    private OnItemClickListener OnItemClickLitener;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view , int position);
    }


    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener){
        OnItemClickLitener=mOnItemClickListener;
    }

    public MyAdapter(Context mContext,List<String> Datas,List<Integer> heights){
        context=mContext;
        mDatas=Datas;
        mHeight=heights;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder=new MyHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_home, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.mText.setText(mDatas.get(position));
        ViewGroup.LayoutParams lp = holder.mText.getLayoutParams();
        lp.height = mHeight.get(position);

        holder.mText.setLayoutParams(lp);

        // 如果设置了回调，则设置点击事件
        if (OnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    OnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    OnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    public void addData(int position){
        mDatas.add(position, "Insert One");
        mHeight.add( (int) (100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView mText;

        public MyHolder(View itemView) {
            super(itemView);
            mText= (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
