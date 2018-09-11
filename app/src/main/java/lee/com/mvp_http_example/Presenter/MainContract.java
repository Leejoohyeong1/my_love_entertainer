package lee.com.mvp_http_example.Presenter;



import lee.com.mvp_http_example.adapter.adapterConteact.ImageAdapterConteact;

public interface MainContract {

    interface View {
        void ToastShow(String tittle);
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
