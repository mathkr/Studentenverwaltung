package studiverwaltung.util;

public class IncrementLatch {
	private int countDown;

	public IncrementLatch(int initialCountDown){
		this.countDown = initialCountDown;
	}

	public IncrementLatch(){
		this(0);
	}

	synchronized public void increment(){
		++countDown;
	}

	synchronized public void decrement(){
		--countDown;
	}

	public void await() throws InterruptedException {
		while(countDown > 0){
			if(Thread.interrupted()){
				throw new InterruptedException();
			}
		}
	}
}
