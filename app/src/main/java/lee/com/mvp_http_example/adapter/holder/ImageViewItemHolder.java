package lee.com.mvp_http_example.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import lee.com.mvp_http_example.Listener.OnItemClickListener;
import lee.com.mvp_http_example.R;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public class ImageViewItemHolder extends RecyclerView.ViewHolder{
    ImageView image;
    Context context;

    private OnItemClickListener onItemClickListener;


    public ImageViewItemHolder(@NonNull View itemView,Context context,OnItemClickListener onItemClickListener) {
        super(itemView);
        this.context = context;
        image = (ImageView) itemView.findViewById(R.id.recyclerviewImage);
        this.onItemClickListener = onItemClickListener;
    }


    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void onBind(ImageItem item, final int position) {

        RequestOptions options = new RequestOptions()
                .override(item.getSizewidth(),item.getSizeheight())
                .centerCrop()
                .placeholder(R.drawable.my_love_entertainer);

        Glide.with(this.context)
                .load(item.getLink())
                .apply(options)
                .into(this.image);

        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(position,image);
                }
            }
        });
    }

}
