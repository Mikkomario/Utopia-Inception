package utopia.inception.event;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiMouseEventSelector selects an event if it matches any of its internal requirement 
 * collections (selectors).
 * 
 * @author Mikko Hilpinen
 * @param <T> The type of event selected
 * @since 18.11.2014
 */
public class MultiEventSelector<T extends Event> implements EventSelector<T>
{
	// ATTRIBUTES	-------------------------------------------
	
	private List<EventSelector<T>> selectors;
	
	
	// CONSTRUCTOR	------------------------------------
	
	/**
	 * Creates a eventSelector that doesn't accept any events. Additional selectors / options
	 * can be added manually
	 */
	public MultiEventSelector()
	{
		// Initializes attributes
		this.selectors = new ArrayList<EventSelector<T>>();
	}
	
	
	// IMPLEMENTED METHODS	-----------------------------

	@Override
	public boolean selects(T event)
	{
		for (EventSelector<T> selector : this.selectors)
		{
			if (selector.selects(event))
				return true;
		}
		
		return false;
	}
	
	
	// OTHER METHODS	---------------------------------
	
	/**
	 * Adds a new option to the accepted 
	 * @param selector The selector that will work as an option for selection
	 */
	public void addOption(EventSelector<T> selector)
	{
		if (!this.selectors.contains(selector))
			this.selectors.add(selector);
	}
}
