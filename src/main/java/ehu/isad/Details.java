package ehu.isad;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Details {
    String ISBN;
    String[] publishers;
    String title;
    int number_of_pages;

    public Details(String titleA,String isbnA){
        this.ISBN=isbnA;
        this.title=titleA;
    }


    @Override
    public String toString() {
        return "Details{" +
                "isbn='" + ISBN + '\'' +
                ", publishers=" + Arrays.toString(publishers) +
                ", title='" + title + '\'' +
                ", number_of_pages='" + number_of_pages + '\'' +
                '}';
    }

    public int getNumber_of_pages(){
        return number_of_pages;
    }

    public String getTitle(){
        return title;
    }

    public String getPublishers(){
        return Arrays.toString(publishers);
    }

    public String getIsbn(){
        return ISBN;
    }

    public void setPublishers(String[] publishers) {
        this.publishers = publishers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber_of_pages(int number_of_pages) {
        this.number_of_pages = number_of_pages;
    }

    public void setIsbn(String isbn){
        this.ISBN=isbn;
    }



}
