package rodrigo.zaniolo.myshowcaseapp.managers.city;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CityManager {

    /* Variables. */
    private static CityManager instance = null;
    private LinkedHashMap<String, List<String>> cityMap;

    /* Constructors. */
    private CityManager() {
        cityMap = new LinkedHashMap<>();
        populateCityList();
    }

    /**
     * Getting Singleton Manager.
     * @return
     */
    public static CityManager getInstance(){
        if(instance == null){
            instance = new CityManager();
        }

        return instance;
    }

    /* Getter. */
    public LinkedHashMap<String, List<String>> getCityMap() {
        return cityMap;
    }

    /* Private Methods. */
    private void populateCityList(){
        add("br", "São Paulo");
        add("br", "Porto Alegre");
        add("sw", "Stockholm");
        add("us", "New York");
    }

    public void add(String country, String city){
        if(!cityMap.containsKey(country)){
            cityMap.put(country, new ArrayList<String>());
        }

        if(!cityMap.get(country).contains(city)) {
            cityMap.get(country).add(city);
        }
    }
}
