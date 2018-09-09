package lee.com.mvp_http_example.adapter.adapterConteact;


import android.view.View;

import java.util.ArrayList;

import lee.com.mvp_http_example.Listener.OnItemClickListener;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public interface ImageAdapterConteact {
    interface view{
        void notfyAdaoter();

        void setOnClickLisetner(OnItemClickListener clickLisetner);
    }
    interface Model{
        void addItems(ArrayList<ImageItem> imageItem);
        ImageItem getItem(int position);
        int getSize();
        void clearItems();
    }
}
