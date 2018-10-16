package lee.com.mvp_http_example.Presenter;


import android.content.Context;


public interface DetailContract {


    interface View{
        void UrlImageSaveLocalStorag();
        void RuntimePermssionChek();
        void Viewinit();
    }

    interface Presenter{
        void attachView(DetailContract.View view);
        void detachView();
        void viewinit();
        void ImageDownLoad();
    }
}
