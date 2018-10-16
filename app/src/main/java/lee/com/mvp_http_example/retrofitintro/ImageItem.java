package lee.com.mvp_http_example.retrofitintro;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageItem implements Serializable{
    @Override
    public String toString() {
        return "ImageItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", sizeheight='" + sizeheight + '\'' +
                ", sizewidth='" + sizewidth + '\'' +
                '}';
    }

    @SerializedName("title")  String title;
    @SerializedName("link")  String link;
    @SerializedName("thumbnail")  String thumbnail;
    @SerializedName("sizeheight")  int sizeheight;
    @SerializedName("sizewidth")  int sizewidth;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getSizeheight() {
        return sizeheight;
    }

    public void setSizeheight(int sizeheight) {
        this.sizeheight = sizeheight;
    }

    public int getSizewidth() {
        return sizewidth;
    }

    public void setSizewidth(int sizewidth) {
        this.sizewidth = sizewidth;
    }
}

