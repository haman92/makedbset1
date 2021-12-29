package oracledb1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class DB {
	 int order[];
     int charge[];
	//192.168.56.1
	//1521
	//sid: orclcdb
	public  String rand_alpha(int length)
	{
		String str = "";
		Random rand = new Random();
		for(int j=0 ;j<length;j++)
    	{
    		str+=String.valueOf((char)('A'+(int)(rand.nextDouble()*26))); 		
    	}
		return str;
	}
	public  int rand_int(Random rand, int num)
	{
		
		return (int)(rand.nextInt(num));
	}
	public  void st1(Connection conn) throws SQLException
	{
		String SQLStmt1 = "INSERT INTO ������� "  
				+ "(��������ڵ�,������ĸ�) " 
				+ "VALUES(?, ?)";
	    PreparedStatement stmt1 = conn.prepareStatement(SQLStmt1);
	    
	    System.out.println("��������");
	    for(int i =0 ;i<26;i++)
	    {
	    	char value = (char) ('A'+(char)i);
	    	String str = String.valueOf(value);
	    	stmt1.setString(1,str);
	    	stmt1.setString(2,str);
	    	stmt1.addBatch();
	    }
	    stmt1.executeBatch();
	    stmt1.close();
	    System.out.println("�����Ϸ�");
	    
	}
	public  void st2(Connection conn,int arr_process_num,int product_size) throws SQLException
	{
		Random rand = new Random();
		String SQLStmt2 = "INSERT INTO ��ǰ "  
				+ "(��ǰ��ȣ,��ǰ��,����,���޾�ü�ڵ�) " 
				+ "VALUES(?, ?,?,?)"; 
	    PreparedStatement stmt2 = conn.prepareStatement(SQLStmt2);
	    System.out.println("��ǰ����");
	    HashSet<String> hashset = new HashSet<String>();
	    for(int i =1 ;i<product_size+1;i++)
	    {
	    	
	    	stmt2.setLong(1, i);
	    	String str = "";
	    	while(true)
	    	{

	    		str=rand_alpha(5);
		    	if(!hashset.contains(str))
		    	{
		    		break;
		    	}else
		    	{
		    		hashset.add(str);
		    	}
	    	}
	    	stmt2.setString(2, str);
	    	stmt2.setLong(3, (int)(rand.nextDouble()*1000000));
	    	str="";
	    	str = rand_alpha(2);
	    	stmt2.setString(4, str);
	    	stmt2.addBatch();
	    	if(i%arr_process_num==0)
	    	{
	    		stmt2.executeBatch();
	    	}
	    }
	    stmt2.executeBatch();
	    
	    
	    hashset.clear();
	    System.out.println("��ǰ�Ϸ�");
	}
	public  void st3(Connection conn,int arr_process_num,int customer_size) throws SQLException
	{
		Random rand = new Random();
	    String SQLStmt3 = "INSERT INTO �� "  
				+ "(����ȣ,����,��������,����ó,����,�����,����) " 
				+ "VALUES(?, ?,?,?,?,?,?)";
	    HashSet<String> hashset = new HashSet<String>();
	    PreparedStatement stmt3 = conn.prepareStatement(SQLStmt3);
	    /*
	     * + "(����ȣ,����,��������,����ó,����,�����,����) " 
				+ "VALUES(?, ?,?,?,?,?,?)";
	     */
	    System.out.println("������");
	    int customer[]= new int[customer_size+1];
	    for(int i =1 ;i<customer_size+1;i++)
	    {
	    	stmt3.setLong(1, i);
	    	
	    	int MFrand = rand_int(rand,2);
	    	if(MFrand ==0)
	    		stmt3.setString(2, "M");
	    	else 
	    		stmt3.setString(2, "F");
	    	
	    	Calendar cal = Calendar.getInstance();
		    cal.set(2000, 1, 1);
	        int signup = rand_int(rand,7300);
	        customer[i]=signup;
	        cal.add(Calendar.DATE, signup);     
	        stmt3.setDate(3, new java.sql.Date(cal.getTimeInMillis()));
	        
	        
	        String str_phone = String.valueOf(rand_int(rand,99999999)+1);
	        while(str_phone.length()!=8)
	        {
	        	str_phone = String.valueOf(rand_int(rand,99999999)+1);
	        }
	        str_phone="010"+str_phone;
	        
	        stmt3.setString(4, str_phone);
	        
	        int age = 20+rand_int(rand,80);
	        
	        stmt3.setLong(5, rand_int(rand,100));
	        int grade = rand_int(rand,100);
	        
	        String str = "";
	        if(grade<=50)
	        {
	        	str+=String.valueOf((char)('E'));
	        }else if(grade<=75&&grade>50)
	        {
	        	str+=String.valueOf((char)('D'));
	        }else if(grade<=90&&grade>75)
	        {
	        	str+=String.valueOf((char)('C'));
	        }else if(grade<=98&&grade>90)
	        {
	        	str+=String.valueOf((char)('B'));
	        }else
	        {
	        	str+=String.valueOf((char)('A'));
	        }				 		
	    	stmt3.setString(6,str);
	        
	    	str ="";
	    	while(true)
	    	{

	    		str=rand_alpha(5);
		    	if(!hashset.contains(str))
		    	{
		    		break;
		    	}else
		    	{
		    		hashset.add(str);
		    	}
	    	}
	    	stmt3.setString(7, str);

	        stmt3.addBatch();
	    	
	        if(i%arr_process_num==0)
	    	{
	    		stmt3.executeBatch();
	    	}
	    }
	    stmt3.executeBatch();
	    
	    System.out.println("���Ϸ�");
	    

	}
	public  void st4(Connection conn,int arr_process_num,int product_size,int customer_size,int order_size) throws SQLException
	{
		Random rand = new Random();
		String SQLStmt4 = "INSERT INTO �ֹ� "  
				+ "(�ֹ���ȣ,��ǰ��ȣ,����ȣ,�ֹ�����,�ֹ��ݾ�,�����) " 
				+ "VALUES(?, ?,?,?,?,?)";
	    PreparedStatement stmt4 = conn.prepareStatement(SQLStmt4);
	    System.out.println("�ֹ�����");
	   
	    for(int i =1 ;i<order_size+1;i++)
	    {
	    	stmt4.setLong(1, i);
	    	
	    	stmt4.setLong(2, rand_int(rand,product_size)+1);
	    	stmt4.setLong(3, rand_int(rand,customer_size)+1);
	    	
	    	Calendar cal = Calendar.getInstance();
		    cal.set(2021, 1, 1);
	        DateFormat df = new SimpleDateFormat("yyyyMMdd");
	        int adddate = rand_int(rand,370);
	        order[i]=adddate;
	        cal.add(Calendar.DATE, adddate);     
	        stmt4.setDate(4, new java.sql.Date(cal.getTimeInMillis()));
	        
	    	
	    	int charge_a = rand_int(rand,10000000);
	    	charge[i]=charge_a;
	    	stmt4.setLong(5, charge_a);
	        
	    	String str = "";
	    	int rndint =-1;
	    	rndint= rand_int(rand,100);
	    	if(rndint<=40)
	        {
	        	str+=String.valueOf((char)('A'));
	        }else if(rndint<=60&&rndint>40)
	        {
	        	str+=String.valueOf((char)('B'));
	        }else if(rndint<=65&&rndint>60)
	        {
	        	str+=String.valueOf((char)('C'));
	        }else if(rndint<=70&&rndint>65)
	        {
	        	str+=String.valueOf((char)('D'));
	        }else if(rndint<=75&&rndint>70)
	        {
	        	str+=String.valueOf((char)('E'));
	        }else if(rndint<=80&&rndint>75)
	        {
	        	str+=String.valueOf((char)('F'));
	        }else if(rndint<=85&&rndint>80)
	        {
	        	str+=String.valueOf((char)('G'));
	        }else if(rndint<=90&&rndint>85)
	        {
	        	str+=String.valueOf((char)('H'));
	        }else if(rndint<=91&&rndint>90)
	        {
	        	str+=String.valueOf((char)('I'));
	        }else if(rndint<=92&&rndint>91)
	        {
	        	str+=String.valueOf((char)('J'));
	        }else if(rndint<=93&&rndint>92)
	        {
	        	str+=String.valueOf((char)('K'));
	        }else if(rndint<=94&&rndint>93)
	        {
	        	str+=String.valueOf((char)('L'));
	        }else if(rndint<=95&&rndint>94)
	        {
	        	str+=String.valueOf((char)('M'));
	        }else if(rndint<=96&&rndint>95)
	        {
	        	str+=String.valueOf((char)('N'));
	        }else if(rndint<=97&&rndint>96)
	        {
	        	str+=String.valueOf((char)('O'));
	        }else if(rndint<=98&&rndint>97)
	        {
	        	str+=String.valueOf((char)('P'));
	        }else if(rndint<=99&&rndint>98)
	        {
	        	str+=String.valueOf((char)('Q'));
	        }else
	        {
	        	str+=String.valueOf((char)('R'));
	        }
	        
	        
	    	
	    	str +=rand_alpha(1);
	    	stmt4.setString(6, str);
	        
	        
	        
	        stmt4.addBatch();		    	
	        if(i%arr_process_num==0)
	    	{
	        	System.out.println("�ֹ�������"+i/arr_process_num);
	    		stmt4.executeBatch();
	    		conn.commit();
	    	}
	        
	        
	    }
		stmt4.executeBatch();
		conn.commit();
		stmt4.clearBatch();
	    System.out.println("�ֹ��Ϸ�");
	}
	public void st5(Connection conn,int arr_process_num,int order_size, int charge_size) throws SQLException
	{
		Random rand = new Random();
		
		String SQLStmt5 = "INSERT INTO ���� "  
				+ "(������ȣ,�ֹ���ȣ,��������ڵ�,�����ݾ�,��������,�ֹ�����) " 
				+ "VALUES(?, ?,?,?,?,?)";
		
		
	    PreparedStatement stmt5 = conn.prepareStatement(SQLStmt5);
		
	    
	    System.out.println("��������");
	    HashSet<Integer> hashset_gualge = new HashSet<Integer>();
	    for(int i =1 ;i<charge_size+1;i++)
	    {
	    	stmt5.setLong(1, i);
	    	
	    	int jumun=rand_int(rand,order_size)+1;
	    	while(true)
	    	{
	    		if(!hashset_gualge.contains(jumun))
	    			break;
	    		jumun=rand_int(rand,order_size)+1;
	    	}
	    	hashset_gualge.add(jumun);
	    	stmt5.setLong(2, jumun);

	        String str = "";
			str+=String.valueOf((char)('A'+rand_int(rand,26))); 		
	    	stmt5.setString(3,str);
	    	stmt5.setLong(4, charge[jumun]);
	    	//stmt5.setLong(4, 1);
	    	
	    	Calendar cal = Calendar.getInstance();
		    cal.set(2021, 1, 1);
	        cal.add(Calendar.DATE, order[jumun]+rand_int(rand,10));     
	        stmt5.setDate(5, new java.sql.Date(cal.getTimeInMillis()));
	        
	        Calendar cal1 = Calendar.getInstance();
	        cal1.set(2021, 1, 1);
	        cal1.add(Calendar.DATE, order[jumun]);     
	        stmt5.setDate(6, new java.sql.Date(cal1.getTimeInMillis()));
	        
	    	stmt5.addBatch();
	    	
	        if(i%arr_process_num==0)
	    	{
	        	System.out.println("����������"+i/arr_process_num);
	    		stmt5.executeBatch();
	    		conn.commit();
	    	}
	    }
		stmt5.executeBatch();
		conn.commit();

	    stmt5.clearBatch();
	    System.out.println("�����Ϸ�");
	    
	}
	public void start()
	{
		Scanner sc = new Scanner(System.in);
		
		String ip = "127.0.0.1";// ���� ip
		String port = "1614";//���� ��Ʈ
		String sid = "oracle2";
		String url = "jdbc:oracle:thin:@"+ip+":"+port+":"+sid;
		String id = "orcl2";
		String password = "orcl2";
		int product_size = 15000;
		int customer_size = 1000000;
		int order_size= 60000000;
		int charge_size=55000000;
		int arr_process_num = 100000;

		
		System.out.println("IP�� �Է����ּ��� ex)127.0.0.1");
		ip=sc.nextLine();
		System.out.println("����Ŭ ��Ʈ�� �Է����ּ��� ex)1521");
		port=sc.nextLine();
		System.out.println("SID�� �Է����ּ���");
		sid= sc.nextLine();
		System.out.println("ID�� �Է����ּ���");
		id = sc.nextLine();
		System.out.println("��й�ȣ�� �Է����ּ���");
		password = sc.nextLine();
		
		System.out.println("��ǰ ���̺� ����� �Է����ּ��� 1100�� ������ ���� ex) 15000");
		product_size = Integer.parseInt(sc.nextLine());
		System.out.println("�� ���̺� ������ �� �Է����ּ��� 1100�� ������ ���� ex)1000000");
		customer_size  = Integer.parseInt(sc.nextLine());
		System.out.println("�ֹ� ���̺� ������ �� �Է����ּ��� ex)60000000");
		order_size  = Integer.parseInt(sc.nextLine());
		System.out.println("���� ���̺� ������ �� �Է����ּ��� ex)55000000");
		charge_size  = Integer.parseInt(sc.nextLine());
		System.out.println("��ġ�� �Էµ� ������ �� �Է����ּ��� ex)100000");
		arr_process_num  = Integer.parseInt(sc.nextLine());
		
		
		
		long starttime = System.currentTimeMillis(); 

		Connection conn = null;
		java.sql.Statement st = null; //DB�� �����ϴ� ���

		ResultSet rs = null; //��� �޾Ƽ� ó���Ҷ�
		try {
			order = new int[order_size+1];
		    charge = new int[order_size+1];
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver load ����!");
			conn = DriverManager.getConnection(url, id, password);
			System.out.println("DB ���� ����!");
			
			conn.setAutoCommit(false);

		    
			//�������insert
		    
		    
		    //�������============================================================================
		    st1(conn);
		    conn.commit();
		    //��ǰ============================================================================
		    st2(conn,arr_process_num,product_size);
		    conn.commit();
		    //��============================================================================
		    st3(conn,arr_process_num,customer_size);
		    conn.commit();
		    
		    //�ֹ�==========================================================================
			st4(conn,arr_process_num,product_size,customer_size,order_size);
			conn.commit();
		    //����==========================================================================
		    st5(conn,arr_process_num,order_size,charge_size);
		    conn.commit();		    
		    long endtime = System.currentTimeMillis(); // �ڵ� ���� �Ŀ� �ð� �޾ƿ���
		    long meantime = (endtime - starttime)/1000; //�� �ð��� �� ���
		    System.out.println("�ð�����(s) : "+meantime);

		    System.out.println("�Ϸ�");
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
