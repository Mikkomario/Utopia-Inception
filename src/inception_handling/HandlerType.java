package inception_handling;

/**
 * HandlerType represents a single type of handler. Each handler handles a class. HandlerType 
 * is a wrapper for all the different HandlerTypes introduced in Utopia modules as well as 
 * client modules. Only enumerations should implement this interface.
 * @author Mikko Hilpinen.
 * @since 16.11.2014
 */
public interface HandlerType
{
	/**
	 * @return Which type of handled this type of handler supports
	 */
	public Class<?> getSupportedHandledClass();
}
