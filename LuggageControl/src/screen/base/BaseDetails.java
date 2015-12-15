package screen.base;

import baseClasses.SwitchingJPanel;
import main.LuggageControl;

/**
 *
 * @author Corne Lukken
 */
public class BaseDetails extends SwitchingJPanel{
    
    private SearchPanes searchPanes = new SearchPanes();
    
    public BaseDetails(LuggageControl luggageControl) {
        super(luggageControl);
    }
    
}
