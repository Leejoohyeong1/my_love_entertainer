package lee.com.mvp_http_example;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
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
import java.io.FileOutputStream;
import java.io.OutputStream;

import lee.com.mvp_http_example.LocalStorageSave.LocalStorage;
import lee.com.mvp_http_example.retrofitintro.ImageItem;

public class ImageDetailActivity extends AppCompatActivity implements View.OnClickListener{
    final int EXTERNAL_STORAGE = 1000;

    ImageView image;
    Button FileDiskSaveBtn;
    ImageItem item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        this.image = (ImageView) findViewById(R.id.imageDetail);
        this.FileDiskSaveBtn  = (Button) findViewById(R.id.FileSave);
        this.FileDiskSaveBtn.setOnClickListener(this);

        Intent intent = getIntent();
        item = (ImageItem) intent.getSerializableExtra("ImageItem");

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case EXTERNAL_STORAGE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
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

    public void onCl11ick(View view) {
        runtimepermssion2();

        OutputStream output = null;



        String wallpaper_url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
          + File.separator + "myfolder" + File.separator;


        File dir = new File(wallpaper_url);
        if (!dir.exists()) {
            dir.mkdirs();
        }



        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        File file = new File(dir,item.getTitle()+".jpg");

        try {

            output = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
            output.flush();
            output.close();

        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ContentValues values = new ContentValues();

            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            values.put(MediaStore.MediaColumns.DATA, file.getPath());

            getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//            try {
//                MediaStore.Images.Media.insertImage(getContentResolver(),wallpaper_url , file.getName() , file.getName());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
            Log.d("save",file.getAbsolutePath());
            Log.d("save",file.getPath());

        }


    }
    public void imageSave(){

        String wallpaper_url = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + "myfolder" + File.separator;
//        LocalStorage(Context context,String flie, String url, String name, FileType type){
        new LocalStorage(this, wallpaper_url, item.getLink(), item.getTitle()) {

            @Override
            public void onLoadSuccess() {
                Toast.makeText(ImageDetailActivity.this, "성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadError() {
                Toast.makeText(ImageDetailActivity.this, "IO 에러", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadURLError() {
                Toast.makeText(ImageDetailActivity.this, "이미지 URL  에러", Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }



    public void runtimepermssion2(){
        int permissionReadStorage = 0;
        int permissionWriteStorage = 0;

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            permissionReadStorage = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            permissionWriteStorage = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(permissionReadStorage != PackageManager.PERMISSION_DENIED || permissionWriteStorage != PackageManager.PERMISSION_DENIED) {
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



    public void runtimepermssion(){
//        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
//        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        Log.d("ImageDetailActivity","runtimepermssion1");
        String WRITE_EXTERNAL_STORAGE_Permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};




        int hasPermission = ActivityCompat.checkSelfPermission(this,WRITE_EXTERNAL_STORAGE_Permission);
        if ( hasPermission != PackageManager.PERMISSION_GRANTED ){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                //사용자가 다시 보지 않기에 체크를 하지 않고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            } else{
                //사용자가 다시 보지 않기에 체크하고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                //사용자가 다시 보지 않기에 체크를 하지 않고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            } else{
                //사용자가 다시 보지 않기에 체크하고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            }


            Log.d("ImageDetailActivity","runtimepermssion2");
//            ActivityCompat.requestPermissions(this,permissions,WRITE_EXTERNAL_STORAGE);
            //권한 용청
        }
    }

    @Override
    public void onClick(View view) {
        runtimepermssion2();
        imageSave();
    }
}
