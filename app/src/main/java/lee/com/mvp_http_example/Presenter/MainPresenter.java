package lee.com.mvp_http_example.Presenter;

import android.util.Log;

import lee.com.mvp_http_example.Listener.OnItemClickListener;
import lee.com.mvp_http_example.adapter.adapterConteact.ImageAdapterConteact;
import lee.com.mvp_http_example.retrofitintro.APIClient;
import lee.com.mvp_http_example.retrofitintro.APIInterface;
import lee.com.mvp_http_example.retrofitintro.ImageItem;
import lee.com.mvp_http_example.retrofitintro.ImageResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter,OnItemClickListener {

    APIInterface  apiInterface;

    private  MainContract.View view;
    String SearchString = "";
    int page = 1;

    ImageAdapterConteact.Model adapterViewModel;
    ImageAdapterConteact.view adapterViewView;



    public MainPresenter(){
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void ImageSearch(String massage) {
        if(massage.equals("")){
            this.view.ToastShow("키워드를 입력해주세요");
            return;
        }
        this.SearchString = massage;
        this.page = 1;
        this.adapterViewModel.clearItems();
        Call<ImageResponse> call = apiInterface.NaverImageBySearch(massage,30,page,"sim");
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                adapterViewModel.addItems(response.body().getItems());
                adapterViewView.notfyAdaoter();
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                view.ToastShow("데이터 로딩 실패");
            }
        });

    }

    @Override
    public void ImageMoreSearch(int itemConut) {

        if(itemConut == adapterViewModel.getSize()  && page > adapterViewModel.getSize()){ return; }
        page+=30;

        Call<ImageResponse> call = apiInterface.NaverImageBySearch(SearchString,30,page,"sim");
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                Log.d("MainPresenter","onResponse");
                Log.d("adapterViewModel",""+adapterViewModel.getSize());
                adapterViewModel.addItems(response.body().getItems());
                adapterViewView.notfyAdaoter();
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                view.ToastShow("데이터 로딩 실패");
            }
        });
    }

    @Override
    public void setImageAdapterViewModel(ImageAdapterConteact.Model adapterViewModel) {
        this.adapterViewModel = adapterViewModel;
    }

    @Override
    public void setImageAdapterViewView(ImageAdapterConteact.view adapterViewView) {
        this.adapterViewView = adapterViewView;
        this.adapterViewView.setOnClickLisetner(this);
    }


    @Override
    public void onItemClickListener(int position) {
        ImageItem item = this.adapterViewModel.getItem(position);
        view.ToastShow(item.getTitle());
    }

}
