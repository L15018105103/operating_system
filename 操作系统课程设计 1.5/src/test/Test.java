package test;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<M> list=new ArrayList<M>();
		for(int i=0;i<5;i++){
			list.add(new M("1"));
		}
		list.get(1).value="2";
		for(int i=0;i<5;i++){
			System.out.println(list.get(i).value);
		}
	}
}

class M{
	public String value;
	public M(String value){
		this.value=value;
	}
}