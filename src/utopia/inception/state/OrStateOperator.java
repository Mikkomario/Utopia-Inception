package utopia.inception.state;

import java.util.List;

/**
 * This stateOperator depends from multiple stateOperators and has true state if any of those 
 * operators has a true state.
 * 
 * @author Mikko Hilpinen
 * @since 11.12.2014
 */
public class OrStateOperator extends LogicalStateOperator
{
	// CONSTRUCTOR	---------------------
	
	/**
	 * Creates a new operator
	 * @param conditions The stateOperators that affect the state of this operator
	 */
	public OrStateOperator(StateOperator... conditions)
	{
		super(conditions);
	}

	
	// IMPLEMENTED METHODS	----------------

	@Override
	protected boolean defineStateFrom(List<StateOperator> conditions)
	{
		for (StateOperator condition : conditions)
		{
			if (condition.getState())
				return true;
		}
		
		return false;
	}
}
