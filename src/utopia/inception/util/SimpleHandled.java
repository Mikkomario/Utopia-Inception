package utopia.inception.util;

import utopia.inception.handling.Handled;
import utopia.inception.handling.HandlerRelay;
import utopia.inception.handling.HandlingStateOperatorRelay;
import utopia.inception.state.LatchStateOperator;
import utopia.inception.state.StateOperator;

/**
 * This class is a simple implementation of the Handled interface and can be used as a 
 * superclass for other objects. The class implements the basic Handled functions.
 * @author Mikko Hilpinen
 * @since 21.7.2015
 */
public class SimpleHandled implements Handled
{
	// ATTRIBUTES	-------------------------
	
	private StateOperator isDeadOperator;
	private HandlingStateOperatorRelay handlingOperators;
	
	
	// CONSTRUCTOR	------------------------
	
	/**
	 * Creates a new object. Remember to add the object to a handler relay afterwards
	 * @see HandlerRelay#add(Handled...)
	 */
	public SimpleHandled()
	{
		this.isDeadOperator = new LatchStateOperator(false);
		this.handlingOperators = new HandlingStateOperatorRelay(new StateOperator(true, true));
	}

	@Override
	public StateOperator getIsDeadStateOperator()
	{
		return this.isDeadOperator;
	}

	@Override
	public HandlingStateOperatorRelay getHandlingOperators()
	{
		return this.handlingOperators;
	}

	
	// ACCESSORS	-----------------------
	
	/**
	 * Changes the stateOperator that defines whether the object is considered alive or dead
	 * @param operator The new stateOperator for liveliness
	 */
	public void setIsDeadOperator(StateOperator operator)
	{
		if (operator == null)
			return;
		
		// Transfers the listeners between the operators as well
		operator.transferListenersFrom(this.isDeadOperator);
		this.isDeadOperator = operator;
	}
}
