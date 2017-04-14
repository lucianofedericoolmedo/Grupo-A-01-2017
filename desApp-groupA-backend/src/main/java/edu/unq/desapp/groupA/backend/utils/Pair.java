package edu.unq.desapp.groupA.backend.utils;

public class Pair<TypeA, TypeB> {

	private TypeA value0;
	
	private TypeB value1;

	public Pair(TypeA valueA, TypeB valueB) {
		this.value0 = valueA;
		this.value1 = valueB;
	}
	
	public TypeA getValue0() {
		return value0;
	}

	public void setValue0(TypeA value0) {
		this.value0 = value0;
	}

	public TypeB getValue1() {
		return value1;
	}

	public void setValue1(TypeB value1) {
		this.value1 = value1;
	}
}
