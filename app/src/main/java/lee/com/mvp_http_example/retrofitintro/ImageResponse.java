package lee.com.mvp_http_example.retrofitintro;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImageResponse {
    @Override
    public String toString() {
        return "ImageResponse{" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", total=" + total +
                ", start=" + start +
                ", display=" + display +
                ", items=" + items +
                '}';
    }

    @SerializedName("lastBuildDate")  String lastBuildDate;
    @SerializedName("total")  int total;
    @SerializedName("start")  int start;
    @SerializedName("display")  int display;
    @SerializedName("items") ArrayList<ImageItem> items;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }


    public ArrayList<ImageItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ImageItem> items) {
        this.items = items;
    }
}

