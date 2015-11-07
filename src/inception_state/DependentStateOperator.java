package inception_state;

import inception_handling.HandlingStateOperatorRelay;

/**
 * DependentStateOperator copies its state from another stateOperator.
 * @author Mikko Hilpinen
 * @since 16.11.2014
 */
public class DependentStateOperator extends StateOperator implements StateOperatorListener
{
	// ATTRIBUTES	---------------------------------------
	
	private StateOperator isDeadOperator;
	private HandlingStateOperatorRelay handlingOperators;
	
	
	// CONSTRUCTOR	---------------------------------------
	
	/**
	 * Creates a new StateOperator that copies its state from the given source. The operator 
	 * doesn't need to be added to any handler.
	 * @param parent The stateOperator from which the state is copied from
	 */
	public DependentStateOperator(StateOperator parent)
	{
		super(parent != null ? parent.getState() : false, true);
		
		// Initializes attributes
		this.isDeadOperator = null;
		this.handlingOperators = null;
		
		// Adds the object to the handler
		if (parent != null)
			parent.getListenerHandler().add(this);
	}
	
	/**
	 * Creates a new StateOperator. The operator must be set as a listener for another 
	 * operator's listenerHandler
	 * @param initialState The state the operator has before the handler gives any information
	 * @see StateOperator#getListenerHandler()
	 * @see StateOperatorListenerHandler#add(StateOperatorListener)
	 */
	public DependentStateOperator(boolean initialState)
	{
		super(initialState, false);
		
		// Initializes attributes
		this.isDeadOperator = null;
		this.handlingOperators = null;
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------

	@Override
	public void onStateChange(StateOperator source, boolean newState)
	{
		setState(newState);
	}

	@Override
	public StateOperator getIsDeadStateOperator()
	{
		if (this.isDeadOperator == null)
			this.isDeadOperator = new LatchStateOperator(false);
		return this.isDeadOperator;
	}
	
	@Override
	public HandlingStateOperatorRelay getHandlingOperators()
	{
		if (this.handlingOperators == null)
			this.handlingOperators = new HandlingStateOperatorRelay(new StateOperator(
					true, false));
		return this.handlingOperators;
	}
}
