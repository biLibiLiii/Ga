package com.bilibiliii.ga.murmur.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilibiliii.ga.R;
import com.bilibiliii.ga.bean.Murmur;
import com.bilibiliii.ga.utils.Common;
import com.bilibiliii.ga.utils.ImageUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Cherie_No.47 on 2016/7/11 14:32.
 * Email jascal@163.com
 */
public class MurmurListAdapter extends RecyclerView.Adapter<MurmurListAdapter.MurmurListViewHolder> {
    private Context context;
    private List<Murmur> list;

    public MurmurListAdapter(Context context, List<Murmur> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public MurmurListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Fresco.initialize(context.getApplicationContext());
        MurmurListViewHolder murmurListViewHolder = new MurmurListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_murmur, parent, false));
        return murmurListViewHolder;
    }

    @Override
    public void onBindViewHolder(MurmurListViewHolder holder, int position) {
        holder.creater.setText(list.get(position).getCreater());
        if (!list.get(position).getContent().equals("")) {
            holder.content.setVisibility(View.VISIBLE);
            holder.content.setText(list.get(position).getContent());
        } else {
            holder.content.setVisibility(View.GONE);
        }
        if (list.get(position).getImageUri().length() > 3) {
            holder.image.setVisibility(View.VISIBLE);
            ImageUtils.displaySinglePic(holder.image, Uri.parse(list.get(position).getImageUri()), Common.IMAGE_SIZE_ON_EDIT_WIDTH, Common.IMAGE_SIZE_ON_EDIT_HEIGHT);
        } else {
            holder.image.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MurmurListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.list_item_murmur_creater)
        TextView creater;
        @Bind(R.id.list_item_murmur_content)
        TextView content;
        @Bind(R.id.list_item_murmur_image)
        SimpleDraweeView image;

        public MurmurListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
