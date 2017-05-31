import java.util.*;

public class BTree {
	DataSet data;
	BTreeNode root;
	int netMask;
	int bit;
	
	public BTree(int mask, DataSet data){
		this.data = data;
		netMask = mask;
		bit = 8;
		root = new BTreeNode("");
	}
	
//	public boolean insert(DataSet data){
//		BTreeNode bn;
//		
//		if(root.children.size() != 0){
//			// 읽은 데이터의 넷마스크
//			if(data.nSize != 0){
//				
//			}
//			else{
//				
//			}
//			
//		}
//		// Node 한개도 없을 때
//		else{
//			
//			// 읽은 데이터의 넷마스크
//			if(data.nSize != 0){
//				
//				
//				bn = new BTreeNode(data.sData1.substring(0, data.nSize));
//				bn.hash.put(data.sData1, 0);
//
//				// Netmask
//				if(data.nSize <= 8){
//					bn.setOutport(data.data1+data.data2+data.data3+data.data4+data.nSize % 24);					
//				}
//				bn.
//				
//				root.children.add(bn);			
//				
//			}
//			else{
//				root.setData("0");
//				root.setOutport(9999);
//			}
//			
//		}
//		
//		
//		return true;
//	}
	
	
	public boolean insert(DataSet data){
		BTreeNode parentNode = null;
		BTreeNode currentNode = root;
		BTreeNode newNode = null;
		
		int i=0;
		
		while(currentNode.children != null){
			if(data.nSize == 0){
				String ip = data.data1 + "" + data.data2 + "" + data.data3 + "" + data.data4;
				if(ip.equals("0000")){
					currentNode.outport.add(9999); // 0.0.0.0/0 일때 = out port 9999
					return true;
				}else{
					System.out.println("netmask 가 0입니다.");
					return true;
				}
			}
			else{
				currentNode = searchNode(data);
			}
			
		}
		return true;
	}
	
	
	
	
	public BTreeNode searchNode(DataSet data){
		BTreeNode tmpNode = root;
		BTreeNode newNode;
		
		int searchNum;
		int splitNum;
		
		searchNum = data.nSize / bit;
		splitNum = data.nSize % bit;
		
		tmpNode = findNode(searchNum, data); // search depth
		
		for(int i=0; i<tmpNode.children.size(); ++i){
			String nodestr = tmpNode.children.get(i).getData();
			
			
			
			
			
			
			
			
			
		}
		return tmpNode; // true = search success
	}
	
	public BTreeNode findNode(int depth, DataSet data){
		BTreeNode tmpNode = root;
		
		for(int i=1 ; i<=depth ; ++i){
			switch(depth){
				case 1:
					// 없을 때
					if(tmpNode.hash.get(data.sData1) == null){
						break;
					}
					else{
						tmpNode = tmpNode.children.get(tmpNode.hash.get(data.sData1));
					}
					break;
				case 2:
					// 없을 때
					if(tmpNode.hash.get(data.sData2) == null){
						break;
					}
					else{
						tmpNode = tmpNode.children.get(tmpNode.hash.get(data.sData2));
					}
					break;
				case 3:
					// 없을 때
					if(tmpNode.hash.get(data.sData3) == null){
						break;
					}
					else{
						tmpNode = tmpNode.children.get(tmpNode.hash.get(data.sData3));
					}
					break;
				case 4:
					// 없을 때
					if(tmpNode.hash.get(data.sData4) == null){
						break;
					}
					else{
						tmpNode = tmpNode.children.get(tmpNode.hash.get(data.sData4));
					}
					break;
			}
		}
		
		return tmpNode;
	}
}
