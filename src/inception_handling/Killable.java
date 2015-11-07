package inception_handling;

import inception_state.StateOperator;

/**
 * Killable objects may be considered dead or alive. If an object is considered dead, it 
 * should not be used anymore.
 * 
 * @author Mikko Hilpinen
 * @since 3.8.2015
 */
public interface Killable
{
	/**
	 * @return The stateOperator that defines whether the object is considered dead (or alive).
	 */
	public StateOperator getIsDeadStateOperator();
}
