package ehu.isad;

import java.util.Arrays;

public class Details {
    String ISBN;
    String[] publishers;
    String title;
    String number_of_pages;

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

    public String getNumber_of_pages(){
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

    public void setIsbn(String isbn){
        this.ISBN=isbn;
    }



}
