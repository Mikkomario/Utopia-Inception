package inception_event;

import java.util.ArrayList;
import java.util.List;

/**
 * StrictEventSelectors select only certain events based on their features. 
 * Selectors can be used for picking the events that interest the user.
 * 
 * @author Mikko Hilpinen
 * @param <T> The type of event this selector selects
 * @param <FeatureType> The type of the features selected by this selector
 * @since 17.11.2014
 */
public class StrictEventSelector<T extends Event, FeatureType extends Event.Feature> 
		implements EventSelector<T>
{
	// ATTRIBUTES	-------------------------------------
	
	private final List<FeatureType> requiredFeatures, unnacceptableFeatures;
	
	
	// CONSTRUCTOR	-------------------------------------
	
	/**
	 * Creates a new StrictEventSelector. Initially the selector accepts all the events. 
	 * The required features should be added separately.
	 */
	public StrictEventSelector()
	{
		// Initializes attributes
		this.requiredFeatures = new ArrayList<FeatureType>();
		this.unnacceptableFeatures = new ArrayList<FeatureType>();
	}

	
	// OTHER METHODS	---------------------------------
	
	/**
	 * Adds a new feature to the features required for selection. Watch out for 
	 * exclusive features
	 * 
	 * @param feature The feature the event must have in order to be selected
	 */
	public void addRequiredFeature(FeatureType feature)
	{
		if (feature != null && !this.requiredFeatures.contains(feature) && 
				!this.unnacceptableFeatures.contains(feature))
			this.requiredFeatures.add(feature);
	}
	
	/**
	 * Adds a new feature to the features that are not acceptable for selection.
	 * @param feature The feature which makes an event unacceptable.
	 */
	public void addUnacceptableFeature(FeatureType feature)
	{
		if (feature != null && !this.unnacceptableFeatures.contains(feature) && 
				!this.requiredFeatures.contains(feature))
			this.unnacceptableFeatures.add(feature);
	}
	
	@Override
	public boolean selects(T event)
	{
		// Checks if the event has all the required features
		List<Event.Feature> features = event.getFeatures();
		
		for (FeatureType requirement : this.requiredFeatures)
		{
			if (!features.contains(requirement))
				return false;
		}
		
		for (FeatureType unacceptable : this.unnacceptableFeatures)
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
	public static StrictEventSelector<Event, Event.Feature> createAllAcceptingSelector()
	{
		return new StrictEventSelector<Event, Event.Feature>();
	}
}
