package com.liuzy.export;

public class Test {
	public static void main(String[] args) {
		Farther p = new Farther();
		p.say();
		
		Child c = new Child();
		c.say();
	}
}
class Farther implements IFarther {
	public String Money = "200￥";
	
	public void say() {
		System.out.println("Farther have money " + Money);
	}
}

class Child extends Farther implements IChild {

	public void say() {
		System.out.println("Child have money ");
	}
}

interface IFarther {
	public String Money = "100￥";
	public void say();
}

interface IChild extends IFarther {
	
}