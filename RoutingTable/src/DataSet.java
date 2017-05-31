public class DataSet {
	public int data1, data2, data3, data4, nSize;
	public String sData1, sData2, sData3, sData4, sNSize;
	public String sbit;
	
	public DataSet(int data1, int data2, int data3, int data4, int nSize){
		this.data1 = data1; 
		this.data2 = data2; 
		this.data3 = data3; 
		this.data4 = data4;
		this.nSize = nSize;
		convertBinary();
		sumBit();
	}
	
	public void convertBinary(){
		sData1 = String.format("%8s", Integer.toString(data1,2)).replace(' ', '0');
		sData2 = String.format("%8s", Integer.toString(data2,2)).replace(' ', '0');
		sData3 = String.format("%8s", Integer.toString(data3,2)).replace(' ', '0');
		sData4 = String.format("%8s", Integer.toString(data4,2)).replace(' ', '0');
		sNSize = String.format("%8s", Integer.toString(nSize,2)).replace(' ', '0');
	}
		
	public void sumBit(){
		String temp = sData1 + sData2 + sData3 + sData4;
		sbit = temp.substring(0, nSize);
	}
	
	public int outport(){
		int outport = (data1 + data2 + data3 + data4 + nSize) % 24;
		return outport;
	}
	/*
	public int getData1(){
		return data1;
	}
	
	public int getData2(){
		return data2;
	}
	
	public int getData3(){
		return data3;
	}
	
	public int getData4(){
		return data4;
	}
	
	public int getNSize(){
		return nSize;
	}*/
}
