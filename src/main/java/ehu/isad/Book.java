package ehu.isad;

import ehu.isad.Details;

public class Book {
    String info_url;
    String bib_key;
    String preview_url;
    String thumbnail_url;
    Details details;

    public void setInfo_url(String info_url) {
        this.info_url = info_url;
    }

    public void setBib_key(String bib_key) {
        this.bib_key = bib_key;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    String preview;

    @Override
    public String toString() {
        return "Liburuak{" +
                "info_url='" + info_url + '\'' +
                ", bib_key='" + bib_key + '\'' +
                ", preview_url='" + preview_url + '\'' +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                ", details=" + details +
                ", preview='" + preview + '\'' +
                '}';
    }

    public Details getDetails(){
        return this.details;
    }

    public String getThumbnail_url(){
        return this.thumbnail_url;
    }

    public String getInfo_url(){
        return this.info_url;
    }

    public String getBib_key(){
        return this.bib_key;
    }

    public String getPreview_url(){
        return this.preview_url;
    }

    public String getPreview(){
        return this.preview;
    }
}
