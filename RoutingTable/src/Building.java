import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Building {
	public static final int DIP = 9999;
	private ArrayList<DataSet> mArray;
	private ArrayList<DataSet> mArray_test;
	private Hashtable<Integer, Hashtable<String, ArrayList<DataSet>>> hdata;
	private StringBuilder mResultSearch;
	private int protoCount, count;
	private int[] proto;
	private String type;
	
	public Building() {
		mArray = new ArrayList<DataSet>();
		mResultSearch = new StringBuilder();
		count = 0;
		protoCount = 1;
		proto = new int[]{1, 6, 17, 6, 17, 255, 6}; // 1 = icmp, 6 = tcp, 17 = udp, 255 = any
	}
	
	public void start(){
		// 빌딩과정
		inputData();
		building();
		
		printData();
		
		// 검색과정
		//test();
	}
	
	private void test(){
		for(int i=0 ; i<mArray_test.size() ; i++){
			// test를 위해서 이렇게수동으로 바꿈
			search(mArray_test.get(i), i);
		}
		// 파일 쓰기
		File file = new File("output.txt");
		
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(mResultSearch.toString());
			
			writer.close();
		}catch(Exception e){
			System.out.println(e);
		}

	}
	
	
	private void search(DataSet packet, int i){
		
		Hashtable<String, ArrayList<DataSet>> thash = hdata.get(packet.nSize);
		ArrayList<DataSet> tarray = thash.get(packet.sbit);
		
		mResultSearch.append(i+":"+packet.data1+" "+packet.data2+" "+packet.data3+" "+packet.data4+", ");
//		mResultSearch.append(i+":"+tarray.get(0).data1+" "+tarray.get(0).data2+" "+tarray.get(0).data3+" "+tarray.get(0).data4+", ");
//		System.out.print(i+":"+packet.data1+" "+packet.data2+" "+packet.data3+" "+packet.data4+", ");
		if(tarray != null){
			for(int j=0; j<tarray.size() ; ++j){
				mResultSearch.append(tarray.get(j).outport());
				//System.out.print(tarray.get(j).outport()+" ★ ");
			}
			mResultSearch.append("\n");
			//System.out.println("");
		}
		else{
			mResultSearch.append("9999");
			//System.out.print("9999");
		}
	}
	
	
	
	private void building(){
		hdata = new Hashtable<Integer, Hashtable<String, ArrayList<DataSet>>>();
		for(int i=0 ; i<33 ; ++i){
			Hashtable<String, ArrayList<DataSet>> init = new Hashtable<String, ArrayList<DataSet>>();
			hdata.put(i, init);
		}
		
		for(int i=0 ; i<mArray.size() ; ++i){
			Hashtable<String, ArrayList<DataSet>> temp = hdata.get(mArray.get(i).nSize);
			if(temp.get(mArray.get(i).sbit) == null){
				temp.put(mArray.get(i).sbit, new ArrayList<DataSet>());
				temp.get(mArray.get(i).sbit).add(mArray.get(i));
			}
			else{
				temp.get(mArray.get(i).sbit).add(mArray.get(i));
			}
		}
	}

	
	
	public void inputData(){
		mArray_test = new ArrayList<DataSet>();

		try{
			BufferedReader in = new BufferedReader(new FileReader("data-raw-table-Washington.txt")); //data-raw-table-Washington.txt
			String s;
			int d1, d2, d3, d4, n;
			int protocol = 0;
			int priority = 0;
			
			while((s = in.readLine()) != null){
				
				if(s.trim().equals("556686")){
					continue;
				}
				
				count++;
				if(count % 100 != 0){
					if(protoCount < 8){
						protocol = proto[protoCount-1];
						protoCount++;
					}else{
						protoCount = 1;
						protocol = proto[protoCount-1];
						protoCount++;
					}
				}else{
					protocol = DIP;
				}
				
				if(count % 2 == 0){
					type = "allow";
				}else{
					type = "drop";
				}
				
				if(priority == 10){
					priority = 0;
				}
				
				String[] str = s.split(" ");
				d1 = Integer.parseInt(str[0]);
				d2 = Integer.parseInt(str[1]);
				d3 = Integer.parseInt(str[2]);
				d4 = Integer.parseInt(str[3]);
				n = Integer.parseInt(str[4]);
				mArray.add(new DataSet(protocol, d1, d2, d3, d4, n, type, priority));
				mArray_test.add(new DataSet(protocol, d1, d2, d3, 1, n, type, priority));
				priority++;
			}
			in.close();
		}catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}
		
	}
	
	
	public void printData(){
		for(int i=0; i<mArray.size(); ++i){
			System.out.println(mArray.get(i).getProtocol() + " " + mArray.get(i).sData1 + " "  + mArray.get(i).sData2 + " "  + mArray.get(i).sData3 + " "  + mArray.get(i).sData4 + " "  + mArray.get(i).nSize + " "  + mArray.get(i).getPriority() + " "  + mArray.get(i).getType());
		}
	}
}
