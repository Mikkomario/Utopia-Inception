package utopia.inception.event;

/**
 * MouseEventSelectors select only certain MouseEvents they receive, based on their attributes. 
 * Selectors can be used for picking the events that interest the user.
 * @author Mikko Hilpinen
 * @since 18.11.2014
 */
public interface EventSelector
{
	/**
	 * Tells whether this selector would select the given event / does the given element 
	 * have the features the selector requires.
	 * 
	 * @param event The event that is tested
	 * @return Would the selector select the event
	 */
	public boolean selects(Event event);
}
