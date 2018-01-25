package typeFactory;
import java.util.ArrayList;

/**
 * 
 * @author a7840
 * 命令流水线模块，服务器开启时需要被实例化
 */
public class TypePipeLine {
	public ArrayList<Type> types=new ArrayList<Type>(); //存储命令的容器
	public TypePipeLine(){ //在构造器中把所有类型对象加入到命令流水线types中
		this.types.add(new Type0()); //把命令加入到命令列表中
		this.types.add(new Type1());
		this.types.add(new Type2());
		this.types.add(new Type3());
		this.types.add(new Type4());
		this.types.add(new Type5());
		this.types.add(new Type6());
	}
}