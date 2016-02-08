package utopia.inception.handling;

import utopia.inception.state.StateOperatorListener;

/**
 * These are the handler types introduced in the inception project
 * @author Mikko Hilpinen
 * @since 15.10.2015
 */
public enum InceptionHandlerType implements HandlerType
{
	/**
	 * The handler that handles stateOperators
	 * @see StateOperatorListener
	 * @see utopia.inception.state.StateOperatorListenerHandler
	 */
	STATEOPERATORLISTENER;
	
	
	// IMPLEMENTED METHODS	---------------

	@Override
	public Class<?> getSupportedHandledClass()
	{
		return StateOperatorListener.class;
	}
}
