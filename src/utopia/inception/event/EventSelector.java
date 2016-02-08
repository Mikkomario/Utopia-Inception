package utopia.inception.event;

/**
 * MouseEventSelectors select only certain MouseEvents they receive, based on their attributes. 
 * Selectors can be used for picking the events that interest the user.
 * 
 * @author Mikko Hilpinen
 * @param <T> The type of event this selector selects
 * @since 18.11.2014
 */
public interface EventSelector<T extends Event>
{
	/**
	 * Tells whether this selector would select the given event / does the given element 
	 * have the features the selector requires.
	 * 
	 * @param event The event that is tested
	 * @return Would the selector select the event
	 */
	public boolean selects(T event);
}
