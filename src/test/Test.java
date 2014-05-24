package test;

class Test {
	private Integer bar;

	Test() {
		this.bar = new Integer(5);
		final Integer baz = new Integer(1);

		B b = new B(new A() {
			@Override
			public void foo() {
				bar = new Integer(baz);
			}
		});

		System.out.println(bar);

		b.doIt();

		System.out.println(bar);
	}

	public static void main (String[] args) {
		new Test();
	}
}

class B {
	private A a;
	B(A a) {
		this.a = a;
	}

	public void doIt() {
		a.foo();
	}
}

interface A {
	public void foo();
}
