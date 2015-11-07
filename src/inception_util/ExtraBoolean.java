package inception_util;

/**
 * This simple piece of data resembles Boolean, but has more states
 * @author Mikko Hilpinen
 * @since 
 */
public enum ExtraBoolean
{
	/**
	 * Used when something is completely and very much true
	 */
	EXTRA_TRUE(1.0),
	/**
	 * Used when something is almost false, but still true
	 */
	WEAK_TRUE(0.6),
	/**
	 * Used when something is almost true, but still false
	 */
	WEAK_FALSE(0.3),
	/**
	 * Used when something is completely and very much false
	 */
	EXTRA_FALSE(0.0);
	
	
	// ATTRIBUTES	---------------------
	
	private final double value;
	
	
	// CONSTRUCTOR	---------------------
	
	private ExtraBoolean(double value)
	{
		this.value = value;
	}

	
	// OTHER METHODS	-----------------
	
	/**
	 * @return The boolean value of this extra boolean
	 */
	public boolean toBoolean()
	{
		return this.value >= 0.5;
	}
	
	/**
	 * Checks whether the value is the same or more true than the provided value
	 * @param other The value this one is compared to
	 * @return Is this value at least as true as the other
	 */
	public boolean isAtLeastAsTrueAs(ExtraBoolean other)
	{
		return this.value >= other.value;
	}
	
	/**
	 * Checks if the two extra booleans have the same value
	 * @param other The other extra boolean value
	 * @return Extra true if the values are exactly equal, weak true if only the boolean 
	 * values are equal, extra false if the values are opposite (extra true vs. extra false), 
	 * otherwise weak false.
	 */
	public ExtraBoolean equals(ExtraBoolean other)
	{
		if (this == other)
			return EXTRA_TRUE;
		else if (toBoolean() == other.toBoolean())
			return WEAK_TRUE;
		else if (Math.abs(this.value - other.value) < 0.5)
			return WEAK_FALSE;
		else
			return EXTRA_FALSE;
	}
}
