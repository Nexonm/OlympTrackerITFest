package neoxs.olymptracker.data.repositoryImpl;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import neoxs.olymptracker.data.db_room.entity.RoomPeople;
import neoxs.olymptracker.data.mapper.EntityMapper;
import neoxs.olymptracker.domain.entity.ItemParse;
import neoxs.olymptracker.domain.repository.ItemParseRepository;

public class ItemParseRepositoryImpl implements ItemParseRepository {

    private final MutableLiveData<ArrayList<ItemParse>> itemsListLiveData;
    private final ArrayList<ItemParse> itemsData;

    private static int pageNum;

    private static final int pageNumMax;
    private static final String siteUrl;
    private static final String pageRec;

    static{
        pageNum = 0;
        pageNumMax = 17;
        siteUrl = "https://xn--h1akbcbhelu.xn--p1ai";
        pageRec = "/beijing2022?page=";
    }

    @Override
    public void itemParseAddNew() {
        pageNum++;
        if (pageNum<=pageNumMax)
            updatePeopleListAsyncTask();
    }

    @Override
    public MutableLiveData<ArrayList<ItemParse>> getAllParseItems() {
        return itemsListLiveData;
    }

    public ItemParseRepositoryImpl(){
        itemsListLiveData = new MutableLiveData<>();
        itemsData = new ArrayList<>();
        updatePeopleListAsyncTask();
    }

    private void updatePeopleListAsyncTask() {
        AsyncTask.execute(() -> {
                ArrayList<ItemParse> arrData = new ArrayList<>();
            try {
                //getting the needed part of html
                Document doc = Jsoup.connect(siteUrl+pageRec+pageNum).get();

                Elements data = doc.select("span.field-content");
                int size = data.size();

                //start analysing the data and catching the needed things
                for (int i = 0; i < size; i++) {
                    String imageUrl = siteUrl+ imgParser(data.eq(i)
                            .select("div.uk-text-center")
                            .html()
                            .split("\n"));
                    //in case i cant get photo url using select() i parse it myself
                    String name = data.select("h1.uk-card-title")
                            .select("a")
                            .eq(i)
                            .text();
                    String sportName = data.select("p.sportsmen-kartochka-1")
                            .eq(i)
                            .text();
                    String sportCategory = data.select("p.sportsmen-kartochka-2")
                            .eq(i)
                            .text();
                    String yearOfBirth = data.select("p.sportsmen-kartochka-2")
                            .select("time")
                            .eq(i)
                            .text();
                    arrData.add(new ItemParse(imageUrl, name, sportName, isNum(sportCategory), yearOfBirth));
                    Log.d("parse", "parsing url:" + imageUrl + ", name:" + name +
                            ", sportName:" + sportName + ", sportCategory:" + isNum(sportCategory) +
                            ", birth:" + yearOfBirth);
                }
            } catch (IOException e) {
                Log.e("parse", e.getMessage());
            }
            /*
            TODO when it adds new items to RecycleView it updates items before it, maybe it's more
            efficient to load all in a time, it is possible to do with only one new for loop
             */

            itemsData.addAll(arrData);
            itemsListLiveData.postValue(new ArrayList<>(itemsData));
        });
    }

    private String imgParser(String[] strs) {
        for (String s :strs) {
            if (s.contains("img class=\"uk-transition-scale-up uk-transition-opaque\" data-src"))
                return s.substring(68, s.indexOf("data-width")-2);
        }
        return "no found";
    }

    private String isNum(String str){
        try {
            int a = Integer.parseInt(str);
            return " ";
        }catch (NumberFormatException meaning){
            return str;
        }
    }


}
