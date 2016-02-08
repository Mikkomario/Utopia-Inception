package utopia.inception.state;

/**
 * LatchStateOperator is a StateOperator that can be changed once and then becomes immutable
 * 
 * @author Mikko Hilpinen
 * @since 16.11.2014
 */
public class LatchStateOperator extends StateOperator
{
	// CONSTRUCTOR	---------------------------------------
	
	/**
	 * Creates a new latchStateOperator with the given initial state
	 * @param initialState The state of the operator in the beginning
	 */
	public LatchStateOperator(boolean initialState)
	{
		super(initialState, true);
	}
	
	
	// IMPLEMENTED METHODS	------------------------------

	@Override
	public void setState(boolean newState)
	{
		boolean previousState = getState();
		super.setState(newState);
		// The operator becomes immutable once the state changes once
		if (getState() != previousState)
			setMutable(false);
	}
}
