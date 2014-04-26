package studiverwaltung.util;

import java.util.concurrent.CountDownLatch;

/**
 * A Latch that can be incremented to account for an unknown number
 * of Conditions or Threads we need to wait for.
 *
 * Once the Lock is opened (the first time it reaches zero when it
 * is initialized with a non-zero countDown or the first time it
 * reaches zero after it has been incremented for the first time
 * if it has been initialized with zero) it stays open.
 */
public class IncrementLatch {
	private int countDown;
	private CountDownLatch countDownLatch;

	public IncrementLatch(int initialCountDown){
		this.countDown = initialCountDown;
		if (initialCountDown != 0) {
			 countDownLatch = new CountDownLatch(1);
		}
	}

	public IncrementLatch(){
		this(0);
	}

	synchronized public void increment(){
		if (countDownLatch == null) {
			countDownLatch = new CountDownLatch(1);
		}
		++countDown;
	}

	synchronized public void decrement(){
		--countDown;
		if (countDown  <= 0) {
			countDownLatch.countDown();
		}
	}

	public void await() throws InterruptedException {
		if (countDownLatch != null) {
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
