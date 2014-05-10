/* Programming assignments for 'Programmieren I + II' at the
 * Hochschule Bremerhaven, GERMANY.
 *
 * Copyright (C) 2014 Matthis Krause
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package algorithms.util;

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

	public IncrementLatch(int initialCountDown) {
		this.countDown = initialCountDown;
		if (initialCountDown != 0) {
			 countDownLatch = new CountDownLatch(1);
		}
	}

	public IncrementLatch() {
		this(0);
	}

	synchronized public void increment() {
		if (countDownLatch == null) {
			countDownLatch = new CountDownLatch(1);
		}
		++countDown;
	}

	synchronized public void decrement() {
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
