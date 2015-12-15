package screen.base;

import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 * Base search panel class 
 * @author Corne Lukken
 */
public class BaseSearch extends SwitchingJPanel{
    
    private SearchPanes searchPanes = new SearchPanes();
    
    public BaseSearch(LuggageControl luggageControl) {
        super(luggageControl);
    }
    
}
