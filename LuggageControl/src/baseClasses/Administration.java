package baseClasses;

import managers.DatabaseMan;

/**
 * base class for administration classes which manage the insertion, deletion and editing of data.
 * @author Corne Lukken
 */
public class Administration {
    
    private final DatabaseMan db;
    
    /**
     *
     * @param databaseManReference
     */
    public Administration(DatabaseMan databaseManReference) {
        this.db = databaseManReference;
    }
    
}
