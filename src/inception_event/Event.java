package inception_event;

import java.util.List;

/**
 * Events are happenings generated during the processing and user activity. Each event can 
 * be described with features that discern it from other events.
 * 
 * @author Mikko Hilpinen
 * @since 18.11.2014
 */
public interface Event
{
	/**
	 * @return All the features that describe this event
	 */
	public List<Feature> getFeatures();
	
	
	// INTERFACES	----------------------------------------
	
	/**
	 * Features describe different UserEvents
	 * 
	 * @author Mikko Hilpinen
	 * @since 18.11.2014
	 */
	public interface Feature
	{
		// Used as a wrapper
	}
}
