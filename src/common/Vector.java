package common;



public class Vector {

	
	/**
	 * 默认是10个元素，如果不够，再加10个
	 */
	private int jishu = 10;
	private int jishuBeishu = 1;
	private Object ob[] = new Object[jishuBeishu*jishu];
	private int currentNumber = 0;
	
	public void addElement(Object object){
		if(currentNumber<ob.length){
			ob[currentNumber] = object;
		}else{
			Object obBuff[] = new Object[jishuBeishu*jishu];
			
			jishuBeishu++;
			for(int i = 0;i<currentNumber;i++){
				obBuff[i] = ob[i];
				ob[i] = null;
			}
			ob = new Object[jishuBeishu*jishu];
			for(int i = 0;i<obBuff.length;i++){
				ob[i] = obBuff[i];
			}
			ob[currentNumber] = object;
		}
		currentNumber++;
	}
	
	public Object elementAt(int index){
		if(index>=currentNumber){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(ob[index]!=null){
			return ob[index];
		}else{
			return null;
		}
	}
	
	public void removeAllElements(){
		for(int i = 0;i<currentNumber;i++){
			ob[i] = null;
		}
		currentNumber = 0;
		jishuBeishu = 1;
	}
	
	public void removeElementAt(int index){
		if(index>=currentNumber||index<0){
			return;
		}
		ob[index] = null;
		for(int i = index;i<currentNumber-1;i++){
			ob[i] = ob[i+1];
		}
		ob[currentNumber-1] = null;
		currentNumber--;
	}
	
	public void setElementAt(Object object,int index){
		if(index>=currentNumber){
			throw new ArrayIndexOutOfBoundsException();
		}
		ob[index] = object;
	}
	
	public void removeElement(Object object){
		if(currentNumber<=0){
			return;
		}
		int id = -1;
		for(int i = 0;i<currentNumber;i++){
			if(ob[i].equals(object)){
				id = i;
				break;
			}
		}
		removeElementAt(id);
	}
	
	public int size(){
		return currentNumber;
	}
	
}
