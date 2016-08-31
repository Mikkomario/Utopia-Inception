package utopia.inception.event;

import java.util.ArrayList;
import java.util.List;

import utopia.inception.event.Event.Feature;

/**
 * StrictEventSelectors select only certain events based on their features. 
 * Selectors can be used for picking the events that interest the user. Initially the selector 
 * accepts all the events. The required features should be added separately.
 * @author Mikko Hilpinen
 * @param <T> The type of event this selector selects
 * @since 17.11.2014
 */
public class StrictEventSelector<T extends Event> 
		implements EventSelector<T>
{
	// ATTRIBUTES	-------------------------------------
	
	private final List<Feature> requiredFeatures, unnacceptableFeatures;
	
	
	// CONSTRUCTOR	-------------------------------------
	
	/**
	 * Creates a new StrictEventSelector. Initially the selector accepts all the events. 
	 * The required features should be added separately.
	 */
	public StrictEventSelector()
	{
		// Initializes attributes
		this.requiredFeatures = new ArrayList<Feature>();
		this.unnacceptableFeatures = new ArrayList<Feature>();
	}

	
	// OTHER METHODS	---------------------------------
	
	/**
	 * Adds a new feature to the features required for selection. Watch out for 
	 * exclusive features
	 * @param feature The feature the event must have in order to be selected
	 */
	public void addRequiredFeature(Feature feature)
	{
		if (feature != null && !this.requiredFeatures.contains(feature) && 
				!this.unnacceptableFeatures.contains(feature))
			this.requiredFeatures.add(feature);
	}
	
	/**
	 * Adds a new feature to the features that are not acceptable for selection.
	 * @param feature The feature which makes an event unacceptable.
	 */
	public void addUnacceptableFeature(Feature feature)
	{
		if (feature != null && !this.unnacceptableFeatures.contains(feature) && 
				!this.requiredFeatures.contains(feature))
			this.unnacceptableFeatures.add(feature);
	}
	
	@Override
	public boolean selects(T event)
	{
		// Checks if the event has all the required features
		List<Feature> features = event.getFeatures();
		
		for (Feature requirement : this.requiredFeatures)
		{
			if (!features.contains(requirement))
				return false;
		}
		
		for (Feature unacceptable : this.unnacceptableFeatures)
		{
			if (features.contains(unacceptable))
				return false;
		}
		
		return true;
	}
	
	
	// FACTORIES	---------------------------------------
	
	/**
	 * @return A selector that accepts all mouse events
	 */
	public static StrictEventSelector<Event> createAllAcceptingSelector()
	{
		return new StrictEventSelector<Event>();
	}
}
