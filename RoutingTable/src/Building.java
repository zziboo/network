import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Building {
	private ArrayList<DataSet> mArray;
	private ArrayList<DataSet> mArray_test;
	private Hashtable<Integer, Hashtable<String, ArrayList<DataSet>>> hdata;
	private StringBuilder mResultSearch;
	
	public Building() {
		mArray = new ArrayList<DataSet>();
		mResultSearch = new StringBuilder();
	}
	
	public void start(){
		// ºôµù°úÁ¤
		inputData();
		building();
		
		// °Ë»ö°úÁ¤
		test();
	}
	
	private void test(){
		for(int i=0 ; i<mArray_test.size() ; i++){
			// test¸¦ À§ÇØ¼­ ÀÌ·¸°Ô¼öµ¿À¸·Î ¹Ù²Þ »ÑÀ×»ÑÀ×
			search(mArray_test.get(i), i);
		}
		// ÆÄÀÏ ¾²±â
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
				//System.out.print(tarray.get(j).outport()+" ¡Ú ");
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
			
			while((s = in.readLine()) != null){
				
				if(s.trim().equals("556686")){
					continue;
				}
				
				String[] str = s.split(" ");
				
				d1 = Integer.parseInt(str[0]);
				d2 = Integer.parseInt(str[1]);
				d3 = Integer.parseInt(str[2]);
				d4 = Integer.parseInt(str[3]);
				n = Integer.parseInt(str[4]);
				mArray.add(new DataSet(d1, d2, d3, d4, n));
				mArray_test.add(new DataSet(d1, d2, d3, 1, n));
			}
			in.close();
		}catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}
		
	}
}
