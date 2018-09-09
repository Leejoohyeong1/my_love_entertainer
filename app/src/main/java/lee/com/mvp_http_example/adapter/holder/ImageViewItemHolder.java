package lee.com.mvp_http_example.adapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import lee.com.mvp_http_example.Listener.OnItemClickListener;
import lee.com.mvp_http_example.R;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public class ImageViewItemHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView title;
    Context context;

    private OnItemClickListener onItemClickListener;


    public ImageViewItemHolder(@NonNull View itemView,Context context,OnItemClickListener onItemClickListener) {
        super(itemView);
        this.context = context;
        image = (ImageView) itemView.findViewById(R.id.recyclerviewImage);
        title = (TextView) itemView.findViewById(R.id.imagetitle);
        this.onItemClickListener = onItemClickListener;
    }


    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public void onBind(ImageItem item, final int position) {


        Glide.with(context).load(item.getLink()).into(image);

        title.setText(item.getTitle());


        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(position);
                }
            }
        });
    }

}
