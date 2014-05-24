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