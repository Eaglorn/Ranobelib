import java.util.ArrayList;
import java.util.Collections;

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
        try {
            String uri = Main.SITE + "v1/book/get/?bookAlias=" + NAME;
            StringBuilder site_ = new StringBuilder();
            Main.GetJson(uri, site_);
            Gson gson = new GsonBuilder().serializeNulls().create();
            JsonRanobe json = gson.fromJson(site_.toString(), JsonRanobe.class);
            System.out.println(FILE + "    " + json.result.parts.size());
            Collections.reverse(json.result.parts);
            for (PartRanobe e : json.result.parts) {
                if (!isGlava(json.result.parts.indexOf(e)) && !e.payment && !e.partDonate) {
                    GLAVA.add(new Glava(e.url, json.result.parts.indexOf(e), this, json));
                }
            }
        } catch (Exception e) {
        }
    }
    private boolean isGlava(int number){
        return GLAVA.stream().anyMatch((g)->(g.NUMBER == number));
    }
}
