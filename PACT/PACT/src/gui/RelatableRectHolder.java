package gui;

import java.awt.Rectangle;

public class RelatableRectHolder {
	public RInt x, y, w, h;

	public RelatableRectHolder(RInt x, RInt y, RInt w, RInt h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x.getValue(), y.getValue(), w.getValue(), h.getValue());
	}

}

class RInt {
	private int i;
	private Relation r;

	public RInt(int i) {
		this.i = i;
	}

	public RInt(Relation r) {
		this.r = r;
	}

	public static RInt makeRInt(int i) {
		return new RInt(i);
	}

	public static RInt makeRInt(Relation r) {
		return new RInt(r);
	}

	public static RInt makeRInt(RInt r1, RInt r2, Operation o) {
		return new RInt(new Relation(r1, r2, o));
	}

	public int getValue() {
		if (r != null)
			return r.getResult().getValue();
		else
			return i;
	}

	public void setValue(int i) {
		this.i = i;
		if (r != null)
			r = null;
	}

	public void setRelation(Relation r) {
		this.r = r;
	}

}

class Relation {
	private RInt r1, r2;
	private Operation o;

	public Relation(RInt r1, RInt r2, Operation o) {
		this.r1 = r1;
		this.r2 = r2;
		this.o = o;
	}

	public void setOperation(Operation o) {
		this.o = o;
	}

	public RInt getResult() {
		return o.operate(r1, r2);
	}
}

interface Operation {
	public RInt operate(RInt x, RInt y);
}

class Operations {
	public static Operation sum() {
		return (x, y) -> new RInt(x.getValue() + y.getValue());
	}

	public static Operation subtract() {
		return (x, y) -> new RInt(x.getValue() - y.getValue());
	}

	public static Operation multiply() {
		return (x, y) -> new RInt(x.getValue() * y.getValue());
	}

	public static Operation divide() {
		return (x, y) -> new RInt(x.getValue() / y.getValue());
	}

	public static Operation constant() {
		return (x, y) -> x;
	}
}
