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

}
