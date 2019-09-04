package loltest;

enum CopyDepth {
	/** Any changes in the original object may affect the copy.
	 * 
	 */
	SHALLOW,
	/** Only changes to referenced objects (mutable object attributes) 
	 * may affect copy.
	 * 
	 */
	REFERENCE,
	/** All referenced objects are copied as new objects of their class.
	 * 
	 */
	STRICT;
}
