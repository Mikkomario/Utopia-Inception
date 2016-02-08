package utopia.inception.handling;

import java.util.HashMap;
import java.util.Map;

import utopia.inception.state.DependentStateOperator;
import utopia.inception.state.StateOperator;

/**
 * HandlingStateOperatorRelay is a collection of stateOperators. The relay offers different 
 * StateOperators for different Handlers, allowing both simplicity and versatility.
 * @author Mikko Hilpinen
 * @since 21.7.2015
 */
public class HandlingStateOperatorRelay
{
	// ATTRIBUTES	--------------------------
	
	private Map<HandlerType, StateOperator> operators;
	private StateOperator defaultOperator;
	
	
	// CONSTRUCTOR	--------------------------
	
	/**
	 * Creates a new operator relay that uses the following stateOperator when others are 
	 * not defined)
	 * @param defaultOperator The stateOperator used when another operator hasn't been 
	 * specified
	 */
	public HandlingStateOperatorRelay(StateOperator defaultOperator)
	{
		this.defaultOperator = defaultOperator;
		this.operators = new HashMap<>();
	}
	
	/**
	 * Creates a new operator relay. A mutable default operator will be created with default 
	 * state of true.
	 */
	public HandlingStateOperatorRelay()
	{
		this.defaultOperator = new StateOperator(true, true);
		this.operators = new HashMap<>();
	}
	
	
	// ACCESSORS	------------------------------
	
	/**
	 * @return The operator that is used when there's no specified StateOperator for a 
	 * HandlerType
	 */
	public StateOperator getDefaultOperator()
	{
		return this.defaultOperator;
	}
	
	/**
	 * Changes the default StateOperator used
	 * @param operator The new stateOperator that will be used as a default option
	 */
	public void setDefaultOperator(StateOperator operator)
	{
		if (operator == null)
			return;
		
		// Transfers the listeners to the new operator
		operator.transferListenersFrom(this.defaultOperator);
		this.defaultOperator = operator;
	}
	
	
	// OTHER METHODS	-----------------------
	
	/**
	 * Finds the operator that defines whether the object should be handled by a certain 
	 * handler (if applicable)
	 * @param handlerType The type of handler that may handle the object
	 * @return The operator that defines whether that handler should handle the object
	 */
	public StateOperator getShouldBeHandledOperator(HandlerType handlerType)
	{
		StateOperator operator = this.operators.get(handlerType);
		if (operator == null)
			return getDefaultOperator();
		else
			return operator;
	}
	
	/**
	 * Changes the StateOperator used for the provided Handler type
	 * @param operator The new stateOperator that will be used
	 * @param type The handler type the operator will be used for
	 */
	public void setShouldBeHandledOperator(HandlerType type, StateOperator operator)
	{
		if (operator != null && type != null)
		{
			// If there was an operator for the given type already, transfers the listeners 
			// from that
			StateOperator previous = this.operators.get(type);
			if (previous != null)
				operator.transferListenersFrom(previous);
				
			this.operators.put(type, operator);
		}
	}
	
	/**
	 * Changes the state of all the mutable operators in the relay
	 * @param newState The new state the mutable operators will have
	 */
	public void setAllStates(boolean newState)
	{
		for (StateOperator operator : this.operators.values())
		{
			operator.setState(newState);
		}
		this.defaultOperator.setState(newState);
	}
	
	/**
	 * Makes the operator use a dependent stateOperator for the given handler type
	 * @param master The object the operator will be dependent from
	 * @param operatorType The type of operator that will be added
	 */
	public void makeDependent(Handled master, HandlerType operatorType)
	{
		setShouldBeHandledOperator(operatorType, new DependentStateOperator(
				master.getHandlingOperators().getShouldBeHandledOperator(operatorType)));
	}
	
	/**
	 * Adds a StateOperator for the given handler type. The operator will be a normal 
	 * stateOperator that has the same state and mutability as the previously used operator.
	 * @param type The type of Handler the operator is used for
	 */
	public void addOperatorForType(HandlerType type)
	{
		StateOperator previousOperator = getShouldBeHandledOperator(type);
		setShouldBeHandledOperator(type, new StateOperator(previousOperator.getState(), 
				previousOperator.isMutable()));
	}
}
