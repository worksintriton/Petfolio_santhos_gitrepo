package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.adapter.ThreeLevelListAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RetailerAllCategoriesActivity extends AppCompatActivity {

    /**
     * The Expandable list view.
     */
    ExpandableListView expandableListView;

    // We have two  main category. (third one is left for example addition)

    /**
     * The Parent Group Names.
     */
    String[] parent = new String[]{"Body Parts", "Brakes, Suspension & Steering","Headlights & Lighting",

                                    "Engine", "Interior", "Exterior", "Wheels & Tires"}; // comment this when uncomment bottom
    //String[] parent = new String[]{"MOVIES", "GAMES", "SERIALS"}; // example for 3 main category lists

    /*
    If above line is uncommented uncomment the following too:
    - serials array
    - serials genre list
    - Datastructure for Third level Serials.
    - secondLevel.add(serials);
    - serials category all data
    - data.add(thirdLevelSerials);

     */

    /**
     * The Movies Genre List.
     */


    // We have two  main category. (third one is left for example addition)
    String[] movies = new String[]{"Accessories"};
    /**
     * The Games Genre List.
     */
    String[] games = new String[]{"Fps", "Moba", "Rpg", "Racing"};

    // String[] serials = new String[]{"Crime", "Family", "Comedy"};


    /**
     * The Horror movie list.
     */
    // movies category has further genres
    String[] horror = new String[]{"Conjuring", "Insidious", "The Ring"};
    /**
     * The Action Movies List.
     */
    String[] action = new String[]{"Seat Covers", "Floor Mats"};
    /**
     * The Thriller Movies List.
     */
    String[] thriller = new String[]{"Imitation Game", "Tinker, Tailer, Soldier, Spy", "Inception", "Manchester by the Sea"};


    /**
     * The Fps games.
     */
    // games category has further genres
    String[] fps = new String[]{"CS: GO", "Team Fortress 2", "Overwatch", "Battlefield 1", "Halo II", "Warframe"};
    /**
     * The Moba games.
     */
    String[] moba = new String[]{"Dota 2", "League of Legends", "Smite", "Strife", "Heroes of the Storm"};
    /**
     * The Rpg games.
     */
    String[] rpg = new String[]{"Witcher III", "Skyrim", "Warcraft", "Mass Effect II", "Diablo", "Dark Souls", "Last of Us"};
    /**
     * The Racing games.
     */
    String[] racing = new String[]{"NFS: Most Wanted", "Forza Motorsport 3", "EA: F1 2016", "Project Cars"};

    // serials genre list
    /*String[] crime = new String[]{"CSI: MIAMI", "X-Files", "True Detective", "Sherlock (BBC)", "Fargo", "Person of Interest"};

    String[] family = new String[]{"Andy Griffith", "Full House", "The Fresh Prince of Bel-Air", "Modern Family", "Friends"};

    String[] comedy = new String[]{"Family Guy", "Simpsons", "The Big Bang Theory", "The Office"};
*/


    /**
     * Datastructure for Third level movies.
     */
    LinkedHashMap<String, String[]> thirdLevelMovies = new LinkedHashMap<>();
    /**
     * Datastructure for Third level games.
     */
    LinkedHashMap<String, String[]> thirdLevelGames = new LinkedHashMap<>();

    // LinkedHashMap<String, String[]> thirdLevelSerials = new LinkedHashMap<>();


    /**
     * The Second level.
     */
    List<String[]> secondLevel = new ArrayList<>();


    /**
     * The Data.
     */
    List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_all_categories);

        // second level category names (genres)
        secondLevel.add(movies);
        secondLevel.add(games);
        // secondLevel.add(serials);

        // movies category all data
        thirdLevelMovies.put(movies[0], action);






        // serials category all data
      /*  thirdLevelSerials.put(serials[0], crime);
        thirdLevelSerials.put(serials[1], family);
        thirdLevelSerials.put(serials[2], comedy);
*/


        // all data
        data.add(thirdLevelMovies);
        data.add(thirdLevelGames);
        //data.add(thirdLevelSerials);


        // expandable listview
        expandableListView = (ExpandableListView) findViewById(R.id.expandible_listview);

        // parent adapter
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, parent, secondLevel, data);


        // set adapter
        expandableListView.setAdapter( threeLevelListAdapterAdapter );


        // OPTIONAL : Show one list at a time
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }
}