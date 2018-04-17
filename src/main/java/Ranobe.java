import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Ranobe{
    String FILE;
    String NAME;
    ArrayList<Glava> GLAVA = new ArrayList<>();
    Ranobe(String file, String name){
        FILE = file;
        NAME = name;
    }
    void start(){
        String uri = Main.SITE + "v1/book/load/?book_alias=" + NAME;
        StringBuilder site_ = new StringBuilder();
        Main.GetJson(uri, site_);
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonRanobe json = gson.fromJson(site_.toString(), JsonRanobe.class);
        System.out.println(FILE + "    " + json.result.parts.size());
        for(PartRanobe e : json.result.parts){
            if (!isGlava(json.result.parts.indexOf(e))){
                GLAVA.add(new Glava(e.url, json.result.parts.indexOf(e), this, json));
            }
        }
    }
    private boolean isGlava(int number){
        return GLAVA.stream().anyMatch((g)->(g.NUMBER == number));
    }
}