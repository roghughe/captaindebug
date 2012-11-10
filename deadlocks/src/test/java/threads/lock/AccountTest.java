/**
 * 
 */
package threads.lock;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Roger
 * 
 *         Created 19:19:26 1 Nov 2012
 * 
 */
public class AccountTest {

	private Account instance;

	@Before
	public void setUp() throws Exception {
		instance = new Account(1, 100);
	}

	/**
	 * In using Lock / ReenterantLock implementation it's your responsibility to
	 * unlock at the end of use.
	 */
	@After
	public void tearDown() throws Exception {
		instance.unlock();
	}

	@Test
	public void testTryLockAndLock() {

		assertTrue(instance.tryLock());
	}

	/**
	 * Will pass because it's the same thread
	 */
	@Test
	public void testTryLockAndRelockAndPass() {

		instance.lock();

		assertTrue(instance.tryLock());
	}

}
