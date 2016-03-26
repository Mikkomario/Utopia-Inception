package utopia.inception.util;

import utopia.inception.handling.Handled;
import utopia.inception.handling.HandlerRelay;
import utopia.inception.handling.HandlingStateOperatorRelay;
import utopia.inception.state.DependentStateOperator;
import utopia.inception.state.StateOperator;

/**
 * A ConnectedHandled shares its states with another object.
 * @author Mikko Hilpinen
 * @param <T> The type of object this one is connected to
 * @since 2.12.2014
 */
public class ConnectedHandled<T extends Handled> implements Handled
{
	// ATTRIBUTES	---------------------------------
	
	private T master;
	private StateOperator isDeadOperator;
	private HandlingStateOperatorRelay separateHandlingOperators;
	
	
	// CONSTRUCTOR	---------------------------------
	
	/**
	 * Creates a new object. The object will be dependent from the given master object. 
	 * Remember to add the object to a handlerRelay afterwards.
	 * @param master The object this object depends from.
	 * @see HandlerRelay#add(Handled...)
	 */
	public ConnectedHandled(T master)
	{
		// Initializes attributes
		if (master != null)
			setMaster(master);
		else
		{
			this.master = null;
			this.isDeadOperator = new StateOperator(false, true);
			this.separateHandlingOperators = new HandlingStateOperatorRelay(new StateOperator(
					false, false));
		}
	}
	
	
	// IMPLEMENTED METHODS	----------------------------

	@Override
	public StateOperator getIsDeadStateOperator()
	{
		return this.isDeadOperator;
	}
	
	@Override
	public HandlingStateOperatorRelay getHandlingOperators()
	{
		if (getMaster() != null)
			return this.master.getHandlingOperators();
		else
			return this.separateHandlingOperators;
	}
	
	
	// GETTERS & SETTERS	----------------------------
	
	/**
	 * Changes this object's master, making it depend from another object instead
	 * @param newMaster The new master object for this object to depend from
	 */
	public void setMaster(T newMaster)
	{	
		// If the new master is null, has to use own stateOperators (= dies)
		if (newMaster == null)
		{
			if (this.separateHandlingOperators != null)
				this.separateHandlingOperators = new HandlingStateOperatorRelay(
						new StateOperator(false, false));
			this.isDeadOperator = new StateOperator(true, false);
		}
		else if (!newMaster.equals(this.master))
		{
			StateOperator previous = this.isDeadOperator;
			this.isDeadOperator = new DependentStateOperator(
					newMaster.getIsDeadStateOperator());
			this.isDeadOperator.transferListenersFrom(previous);
		}

		this.master = newMaster;
	}
	
	/**
	 * Separates the object from its master. The object will automatically die.
	 */
	public void separate()
	{
		setMaster(null);
	}
	
	/**
	 * @return The object this object depends from
	 */
	protected T getMaster()
	{
		return this.master;
	}
}
