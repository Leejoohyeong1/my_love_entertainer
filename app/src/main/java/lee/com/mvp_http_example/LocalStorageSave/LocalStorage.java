package lee.com.mvp_http_example.LocalStorageSave;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class LocalStorage extends AsyncTask<Boolean,Void,Boolean>{

    File file;
    File dir;
    Context mContext;
    URL url;
    String Type = "";
    String name="";

    public LocalStorage(Context context,String flie, String url, String name){
        Log.d("LocalStorage","public");
        mContext = context;

        try {
            this.url = new URL(url);
        }catch (MalformedURLException e){
            e.printStackTrace();
            onLoadURLError();
        }

        this.name = name;
        this.dir = new File(flie);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if (aBoolean){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.Media.MIME_TYPE, this.Type);
            values.put(MediaStore.MediaColumns.DATA, this.file.getPath());

            mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            onLoadSuccess();
        }else{
            onLoadError();
        }
    }

    @Override
    protected Boolean doInBackground(Boolean... booleans) {

        try{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            this.Type = conn.getContentType();

            this.file = new File(dir,name+FileType(this.Type));

            InputStream is = conn.getInputStream();
            OutputStream outStream = new FileOutputStream(this.file);


            byte[] buf = new byte[1024];
            int len = 0;

            while ((len = is.read(buf)) > 0) {
                outStream.write(buf, 0, len);
            }

            outStream.close();
            is.close();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public String FileType(String type){
        String Return = "";
        switch (Type){
            case "image/gif":   Return =".png";
                break;
            case "image/jpeg":   Return =".jpg";
                break;
            case "image/png":   Return =".png";
                break;
        }
        return  Return;
    }
    public abstract void onLoadSuccess();
    public abstract void onLoadError();
    public abstract void onLoadURLError();
}
