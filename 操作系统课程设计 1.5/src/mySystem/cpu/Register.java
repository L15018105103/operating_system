package mySystem.cpu;

/**
 * 寄存器类
 * @author a7840
 * 由于
 */
public class Register {
	public AX ax;
	public BX bx;
	public CX cx;
	public DX dx;
	public Register(){
		ax=new AX();
		bx=new BX();
		cx=new CX();
		dx=new DX();
	}
}

class AX{
	public long value; // 寄存器的值
	
	// 加了synchronized修饰的同步方法
	public synchronized void setValue(long value){
		this.value=value;
	}
	
	public synchronized void addValue(long b){
		this.value+=b;
	}
	
	public synchronized void subValue(long b){
		this.value-=b;
	}
	
	public synchronized void mulValue(long b){
		this.value*=b;
	}
}

class BX extends AX{
	
}

class CX extends AX{
	
}

class DX extends AX{
	
}