package typeFactory;

public class TypeFactory {
	public TypePipeLine typeList=new TypePipeLine();
	private int maxNum=10; //类型个数最大值
	public Type getType(int type){ //传入带头的二进制数组
		if(type<0||type>maxNum){ //类型号不正确
			return null;
		}
		else{
			return typeList.types.get(type); //返回得到的类型对象
		}
	}
}
