package inception_util;

/**
 * All data type classes / enumerations should implement this interface so that they can be 
 * used with generalised values.
 * @author Mikko Hilpinen
 * @since 25.10.2015
 */
public interface DataType
{
	/**
	 * Is this data type the same as the other
	 * @param other The other data type
	 * @return Are these data types the same
	 */
	public boolean equals(DataType other);
}
