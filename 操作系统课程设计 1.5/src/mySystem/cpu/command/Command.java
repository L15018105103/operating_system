package mySystem.cpu.command;

import io.netty.channel.Channel;
import proto.ProtoClass;

/**
 * 
 * @author a7840
 * 因为所有ProtoClass子类都继承了getId等抽象方法，因此以下操作依然安全，只要保证typeNum正确，
         则一定能够执行以下方法成功（typeNum不正确时以下方法，如getId,会返回""空字符串，这个是设计需要，不是异常情况）
 */
public interface Command { // 父类
	public void excute(Channel incoming, ProtoClass myObject); // 执行命令,type为类型号，realProto为类型
}