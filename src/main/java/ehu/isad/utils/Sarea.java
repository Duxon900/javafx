package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {
    public static Book readFromUrl(String isbn){
        isbn=isbn.toLowerCase();
        String inputLine="";

        try{
            URL isbnWeb= new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
            URLConnection iw= isbnWeb.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(iw.getInputStream()));
            inputLine=bufferedReader.readLine();
            bufferedReader.close();

            String[] zatitu;//{"ISBN:9781491906187":
            zatitu = inputLine.split("\"ISBN:"+isbn+"\": "); //{ jartzen bada errorea ematen du

            inputLine=zatitu[1].substring(0,zatitu[1].length()-1);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Gson gson=new Gson();
        return gson.fromJson(inputLine,Book.class);
    }

}
