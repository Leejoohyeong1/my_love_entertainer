package lee.com.mvp_http_example.Presenter;

public class DetailPresenter implements DetailContract.Presenter {

    DetailContract.View view;

    @Override
    public void attachView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void viewinit() {
        view.Viewinit();
        view.RuntimePermssionChek();
    }

    @Override
    public void ImageDownLoad() {
        view.UrlImageSaveLocalStorag();
    }


}
