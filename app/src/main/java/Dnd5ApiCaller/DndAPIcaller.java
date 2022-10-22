package Dnd5ApiCaller;

import java.util.List;

/**
 * This class is responsible in making calls to the dndApi. It also parses the xml information into
 * 3 data points. Title description and type.
 */
public class DndAPIcaller {

    /**
     * Not really sure if necessary.
     */
    DndAPIcaller(){
        super();
    }

    /**
     * This method will take one string query and use it to call the API using the type set by the
     * format enum.
     * @param query the keyword that we are using to create the query.
     * @param type the type of query.
     * @return returns a list of Dnd5items ready to use by other classes.
     */
    public static List<Dnd5Item> searchFor(String query, QueryType type){
        return null;
    };
}
