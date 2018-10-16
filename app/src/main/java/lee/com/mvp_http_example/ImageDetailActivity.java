package lee.com.mvp_http_example;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import lee.com.mvp_http_example.LocalStorageSave.LocalStorage;
import lee.com.mvp_http_example.Presenter.DetailContract;
import lee.com.mvp_http_example.Presenter.DetailPresenter;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public class ImageDetailActivity extends AppCompatActivity implements View.OnClickListener,DetailContract.View{

    final int EXTERNAL_STORAGE = 1000;

    ImageView image;
    Button FileDiskSaveBtn;
    ImageItem item;

    private DetailContract.Presenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        this.image = (ImageView) findViewById(R.id.imageDetail);
        this.FileDiskSaveBtn  = (Button) findViewById(R.id.FileSave);
        this.FileDiskSaveBtn.setOnClickListener(this);

        Intent intent = getIntent();
        item = (ImageItem) intent.getSerializableExtra("ImageItem");

        detailPresenter = new DetailPresenter();
        detailPresenter.attachView(this);
        detailPresenter.viewinit();

    }

    @Override
    public void Viewinit() {
        RequestOptions options = new RequestOptions()
                .priority(Priority.IMMEDIATE)
                .centerCrop()
                .override(item.getSizewidth(),item.getSizeheight())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.my_love_entertainer);

        Glide.with(this)
                .load(item.getLink())
                .apply(options)
                .into(this.image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.detachView();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case EXTERNAL_STORAGE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                           //성공
                            Log.d("Permissions","yes");
                        } else {
                           //실패
                            Log.d("Permissions","no");
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        detailPresenter.ImageDownLoad();
    }

    @Override
    public void UrlImageSaveLocalStorag() {

        String wallpaper_url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + R.string.folder_name + File.separator;

        new LocalStorage(this, wallpaper_url, item.getLink(), item.getTitle()) {

            @Override
            public void onLoadSuccess() {
                Toast.makeText(ImageDetailActivity.this, "성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadError() {
                Toast.makeText(ImageDetailActivity.this, "IO 실패", Toast.LENGTH_SHORT).show();
           }

            @Override
            public void onLoadURLError() {
                Toast.makeText(ImageDetailActivity.this, "정상적인 URL 아닙니다", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void RuntimePermssionChek() {
        int permissionWriteStorage = 0;

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            permissionWriteStorage = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(permissionWriteStorage != PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                //사용자가 다시 보지 않기에 체크를 하지 않고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            } else{
                //사용자가 다시 보지 않기에 체크하고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE);

        }
    }


}
