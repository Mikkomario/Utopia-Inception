package inception_state;

import inception_handling.Handler;
import inception_handling.HandlerType;
import inception_handling.InceptionHandlerType;

/**
 * StateListenerHandler informs multiple stateListeners about state information it receives.
 * 
 * @author Mikko Hilpinen
 * @since 16.11.2014
 */
public class StateOperatorListenerHandler extends Handler<StateOperatorListener> implements 
		StateOperatorListener
{
	// ATTRIBUTES	------------------------------------
	
	private StateOperator lastSource;
	private boolean lastState;
	
	
	// IMPLEMENTED METHODS	----------------------------

	@Override
	public HandlerType getHandlerType()
	{
		return InceptionHandlerType.STATEOPERATORLISTENER;
	}

	@Override
	protected boolean handleObject(StateOperatorListener l)
	{
		// Informs the object about the stateChange
		l.onStateChange(this.lastSource, this.lastState);
		
		return true;
	}

	@Override
	public void onStateChange(StateOperator source, boolean newState)
	{	
		// Informs the objects about the stateChange
		this.lastSource = source;
		this.lastState = newState;
		
		// TODO: Consider setting this false
		handleObjects(true);
		
		this.lastSource = null;
	}
}
