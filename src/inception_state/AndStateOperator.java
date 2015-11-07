package inception_state;

import java.util.List;

/**
 * This stateOperator depends from multiple other stateOperators. It won't affect those 
 * operators, however. In order for the state to be true, the other operators' states also 
 * need to be true.
 * @author Mikko Hilpinen
 * @since 11.12.2014
 */
public class AndStateOperator extends LogicalStateOperator
{
	// CONSTRUCTOR	------------------------------
	
	/**
	 * Creates a new operator that depends from the given operators
	 * @param requirements The stateOperators that affect this operator
	 */
	public AndStateOperator(StateOperator... requirements)
	{
		super(requirements);
	}
	
	
	// IMPLEMENTED METHODS	-------------------------
	
	@Override
	protected boolean defineStateFrom(List<StateOperator> conditions)
	{
		for (StateOperator requirement : conditions)
		{
			if (!requirement.getState())
				return false;
		}
			
		return true;
	}
}
