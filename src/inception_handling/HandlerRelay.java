package inception_handling;

import java.util.HashMap;
import java.util.Map;

/**
 * HandlerRelays keep track of different types of Handlers and provide access to them. 
 * A handled can be added to a handlerRelay like any Handler, only with that difference 
 * that the handled will be added to any handler in the relay that accepts it.
 * @author Mikko Hilpinen
 * @since 16.11.2014
 */
public class HandlerRelay
{
	// ATTRIBUTES	---------------------------------------------
	
	private Map<HandlerType, Handler<? extends Handled>> handlers;
	
	
	// CONSTRUCTOR	---------------------------------------------
	
	/**
	 * Creates a new empty handlerRelay
	 */
	public HandlerRelay()
	{
		// Initializes attributes
		this.handlers = new HashMap<>();
	}
	
	/**
	 * Creates a new handlerRelay that is a copy of the given handlerRelay. The new relay will 
	 * use the exact same handlers, not just copies of them.
	 * @param other The relay this one is copied from
	 */
	public HandlerRelay(HandlerRelay other)
	{
		this.handlers = new HashMap<>();
		this.handlers.putAll(other.handlers);
	}
	
	
	// OTHER METHODS	------------------------------------------
	
	/**
	 * Adds the given handler to the relay
	 * @param h The handler that will be added to the relay
	 * @param killPrevious If there is already a handler of that type in the relay, will it 
	 * be killed in the process
	 */
	public void addHandler(Handler<? extends Handled> h, 
			boolean killPrevious)
	{
		HandlerType type = h.getHandlerType();
		
		// If there already is a handler of the given type, things get more complicated
		if (containsHandlerOfType(type))
		{
			Handler<? extends Handled> other = getHandler(type);
			
			// Kills the previous handler if necessary
			if (killPrevious)
				other.getIsDeadStateOperator().setState(true);
		}
		
		this.handlers.put(type, h);
	}
	
	/**
	 * Replaces a previous handler with a new one. The contents of the previous handler will 
	 * transferred and it will be killed.
	 * @param newHandler The handler that will be added to the relay
	 */
	public void replaceHandler(Handler<? extends Handled> newHandler)
	{
		addHandler(newHandler, true);
	}
	
	/**
	 * Adds the given handler to the relay. This method expects that there is no handler 
	 * of the same type in this relay already (in which case the old handler is removed from 
	 * the relay).
	 * @param h The handler that will be added to the relay.
	 */
	public void addHandler(Handler<? extends Handled> h)
	{
		addHandler(h, false);
	}
	
	/**
	 * @param type The type of the handler that could be included in this relay
	 * @return Does this relay hold a handler of the given type
	 */
	public boolean containsHandlerOfType(HandlerType type)
	{
		return this.handlers.containsKey(type);
	}
	
	/**
	 * Adds a handled to all handlers in the relay that happen to support it. If none of the 
	 * handlers support this handled, no change is made.
	 * @param h The handled that may be added to some of the handlers.
	 * @return Was the handled added to any handler
	 */
	public boolean add(Handled h)
	{
		boolean wasAdded = false;
		
		for (HandlerType type : this.handlers.keySet())
		{
			if (type.getSupportedHandledClass().isInstance(h))
			{
				this.handlers.get(type).volatileAdd(h);
				wasAdded = true;
			}
		}
		
		return wasAdded;
	}
	
	/**
	 * Removes the handled from any handler in this relay.
	 * @param h The handled that will be removed from the handler(s) of this relay.
	 */
	public void remove(Handled h)
	{
		for (HandlerType type : this.handlers.keySet())
		{
			if (type.getSupportedHandledClass().isInstance(h))
				this.handlers.get(type).removeHandled(h);
		}
	}
	
	/**
	 * Returns a handler of the given type from this relay.
	 * @param type The type of the desired handler.
	 * @return a handler with the given type or null if the relay doesn't contain a handler 
	 * of the given type.
	 */
	public Handler<? extends Handled> getHandler(HandlerType type)
	{
		return this.handlers.get(type);
	}
	
	/**
	 * Enables or disables a certain handler in the relay
	 * @param handlerType The type of handler that will be enabled / disabled
	 * @param newState The handler's new state
	 */
	public void setHandlingState(HandlerType handlerType, boolean newState)
	{
		Handler<? extends Handled> handler = getHandler(handlerType);
		if (handler != null)
			handler.getHandlingOperator().setState(newState);
	}
}
