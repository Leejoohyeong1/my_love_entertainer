package lee.com.mvp_http_example.retrofitintro;

import com.google.gson.annotations.SerializedName;

public class ImageItem{
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
    @SerializedName("sizeheight")  String sizeheight;
    @SerializedName("sizewidth")  String sizewidth;

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

    public String getSizeheight() {
        return sizeheight;
    }

    public void setSizeheight(String sizeheight) {
        this.sizeheight = sizeheight;
    }

    public String getSizewidth() {
        return sizewidth;
    }

    public void setSizewidth(String sizewidth) {
        this.sizewidth = sizewidth;
    }
}

