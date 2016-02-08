package utopia.inception.state;

import utopia.inception.handling.Handled;

/**
 * StateListeners are interested in changes happening in stateOperators.
 * 
 * @author Mikko Hilpinen
 * @since 16.11.2014
 */
public interface StateOperatorListener extends Handled
{
	/**
	 * This method will be called when a stateOperator's state changes.
	 * @param source The stateOperator the change happened in
	 * @param newState The new state the operator received
	 */
	public void onStateChange(StateOperator source, boolean newState);
}