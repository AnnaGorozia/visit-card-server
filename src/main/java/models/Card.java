package models;

import java.util.ArrayList;

/**
 * Created by khrak on 7/22/16.
 */
public class Card {

    private String id;
    private ArrayList<Field> fields = new ArrayList<Field>();


    public Card(String id) {
        this.id = id;
    }

    public Card() {

    }

    public String getid(){
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public ArrayList<Field> getFields(){
        return fields;
    }


}
