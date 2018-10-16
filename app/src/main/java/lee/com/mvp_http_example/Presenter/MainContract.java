package lee.com.mvp_http_example.Presenter;



import android.widget.ImageView;

import lee.com.mvp_http_example.adapter.adapterConteact.ImageAdapterConteact;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public interface MainContract {

    interface View {
        void ToastShow(String tittle);
        void ActivityChange(ImageItem item,ImageView imageView);
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void ImageSearch(String massage);
        void ImageMoreSearch(int itemConut);
        void setImageAdapterViewModel(ImageAdapterConteact.Model adapterViewModel);
        void setImageAdapterViewView(ImageAdapterConteact.view adapterViewView);
    }
}
