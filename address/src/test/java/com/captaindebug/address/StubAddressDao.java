/**
 * 
 */
package com.captaindebug.address;

/**
 * @author RogerHughes
 * 
 */
public class StubAddressDao implements AddressDao {

	private final Address address;

	public StubAddressDao(Address address) {
		this.address = address;
	}

	/**
	 * @see com.captaindebug.address.AddressDao#findAddress(int)
	 */
	@Override
	public Address findAddress(int id) {
		return address;
	}
}
