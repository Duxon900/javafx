package ehu.isad;

import ehu.isad.Details;

public class Book {
    String info_url;
    String bib_key;
    String preview_url;
    String thumbnail_url;
    Details details;
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
