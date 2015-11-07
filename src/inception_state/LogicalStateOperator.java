package inception_state;

import java.util.ArrayList;
import java.util.List;

/**
 * Logical state operators define their state based on multiple other state operators
 * @author Mikko Hilpinen
 * @since 17.10.2015
 */
public abstract class LogicalStateOperator extends StateOperator
{
	// ATTRIBUTES	------------------------------
	
	private List<StateOperator> conditions;
	
	
	// CONSTRUCTOR	------------------------------
	
	/**
	 * Creates a new operator that depends from the given operators
	 * @param conditions The stateOperators that affect this operator
	 */
	public LogicalStateOperator(StateOperator... conditions)
	{
		super(false, false);
		
		// Initializes attributes
		this.conditions = new ArrayList<>();
		
		for (StateOperator operator : conditions)
		{
			this.conditions.add(operator);
		}
	}
	
	
	// ABSTRACT METHODS	-----------------------------
	
	/**
	 * In this method the subclass should logically define it's state
	 * @param conditions The operators that could / should affect the state
	 * @return The operators state, based on the provided operators
	 */
	protected abstract boolean defineStateFrom(List<StateOperator> conditions);
	
	
	// IMPLEMENTED METHODS	-------------------------
	
	@Override
	public boolean getState()
	{
		return defineStateFrom(this.conditions);
	}
	
	
	// OTHER METHODS	-----------------------------
	
	/**
	 * Adds a new stateOperator this one is dependent from
	 * @param operator The operator this operator will depend from
	 */
	public void addConditionOperator(StateOperator operator)
	{
		if (!this.conditions.contains(operator))
			this.conditions.add(operator);
	}
	
	/**
	 * Removes a stateOperator from ones this depends from
	 * @param operator The operator this one will no longer depend from
	 */
	public void removeConditionOperator(StateOperator operator)
	{
		this.conditions.remove(operator);
	}
}
