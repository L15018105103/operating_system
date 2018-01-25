package mySystem.cpu.command;

import java.util.ArrayList;

/**
 * 虚拟操作系统指令集
 * @author a7840
 * 命令流水线模块，服务器开启时需要被实例化
 */
public class CommandPipeLine {
	public ArrayList<Command> commands=new ArrayList<Command>(); //存储命令的容器
	public CommandPipeLine(){ //在构造器中把所有命令对象加入到命令流水线commands中
		this.commands.add(new Command0()); //把命令加入到命令列表中
		this.commands.add(new Command1());
		this.commands.add(new Command2());
		this.commands.add(new Command3());
		this.commands.add(new Command4());
		/*this.commands.add(new Command5());
		this.commands.add(new Command6());*/
	}
}
