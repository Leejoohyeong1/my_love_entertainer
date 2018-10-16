package lee.com.mvp_http_example;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lee.com.mvp_http_example.Presenter.MainContract;
import lee.com.mvp_http_example.Presenter.MainPresenter;
import lee.com.mvp_http_example.adapter.ImageAdapter;
import lee.com.mvp_http_example.adapter.ScrollListener.EndlessRecyclerViewScrollListener;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public class MainActivity extends AppCompatActivity  implements MainContract.View {

    EditText editfield;
    Button actionBtn;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    StaggeredGridLayoutManager mLayoutManager;
    private MainContract.Presenter MainPersenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editfield = (EditText) findViewById(R.id.editField);
        actionBtn = (Button) findViewById(R.id.actionBtn);
        actionBtn.setOnClickListener(listener);

        mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mLayoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        imageAdapter = new ImageAdapter(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(imageAdapter);

        recyclerView.setLayoutManager(mLayoutManager);

        MainPersenter = new MainPresenter();
        MainPersenter.attachView(this);
        MainPersenter.setImageAdapterViewModel(imageAdapter);
        MainPersenter.setImageAdapterViewView(imageAdapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager, EndlessRecyclerViewScrollListener.LoadOnScrollDirection.BOTTOM) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                MainPersenter.ImageMoreSearch(totalItemsCount);

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainPersenter.detachView();
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainPersenter.ImageSearch(editfield.getText().toString());
        }
    };


    @Override
    public void ToastShow(String tittle) {
        Toast.makeText(this, tittle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ActivityChange(ImageItem item,ImageView imageView) {
        Intent intent = new Intent(this, ImageDetailActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, imageView, "imgAlbumArt");
            intent.putExtra("ImageItem",item);
            intent.getSerializableExtra("ImageItem");
            startActivity(intent, options.toBundle());
        }
    }
}

