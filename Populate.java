import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;

public class Populate {
	String truncate_table;
	public static void main(String[] tables) throws IOException {
		// TODO Auto-generated method stub
		Jdbc jdbc=new Jdbc();
		Scanner sc=new Scanner(System.in);
		String path="E:\\Reference Books\\Database Systems\\HW3\\New folder\\";
		String[] table=new String[12];
		
		for(int i=0;i<12;i++)
		{
			table[i]=sc.nextLine().trim();
		}
		for(String name:table)
		{
		//String name=sc.();
		String tablename=name.replace(".dat","");
		tablename=tablename.replaceAll("-","_");
		System.out.println(tablename);
		int column_count=getColumnCount(tablename);
		String file_name=name;
		BufferedReader br=new BufferedReader(new FileReader(new File(path+file_name)));
		br.readLine();
		try {
			jdbc.statement.execute("truncate table "+tablename);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean True=true;
		
		while(True)
		{
			String line=br.readLine();
			if(line!=null)
			{
			String query=makeQuery(line,tablename,column_count);
			jdbc.insertData(query);
			}
			else
			{
				break;
			}
		}
		
	}
		jdbc.close();

	}
		
	public static int getColumnCount(String tablename)
	{
		switch(tablename)
		{
		case "movie_actors":
			return 4;
		case "movie_countries":
			return 2;
		case "movie_directors":
			return 3;
		case "movie_genres":
			return 2;
		case "movie_locations":
			return 5;
		case "movie_tags":
			return 3;
		case "movies":
			return 21;
		case "tags":
			return 2;
		case "user_ratedmovies":
			return 9;
		case "user_ratedmovies_timestamps":
			return 4;
		case "user_taggedmovies":
			return 9;
		case "user_taggedmovies_timestamps":
			return 4;
			default:
				System.out.println("check table name");
				System.exit(0);
		}
		return -1;
	}
	public static String makeQuery(String line,String tablename,int columncount)
	{
			//line=line.trim();
			String data_array[]=line.split("\t");
			String values="";
			for(int i=0;i<columncount-1;i++)
			{
				
				if(i>=data_array.length)
				{
					values=values+"\"\",";
					
				}
				else
				{
					int ch=checkForQuotes(data_array[i]);
					switch(ch) {
					case 1:
						values=values+"\""+data_array[i]+"\",";	
					break;
					case 2:
						values=values+"\'"+data_array[i]+"\',";
						break;
					case 3:
						values=values+"\'0.0\',";
						break;
					default:
						values=values+"\'"+data_array[i]+"\',";
						break;
					
					//values=values+data_array[i]+",";
				}
				
			}}
			if(data_array.length!=columncount)
			{
				values+="\"\"";
			}
			else
			{
				int ch=checkForQuotes(data_array[columncount-1]);
				switch(ch) {
				case 1:
					values=values+"\""+data_array[columncount-1]+"\"";	
				break;
				case 2:
					values=values+"\'"+data_array[columncount-1]+"\'";
					break;
				case 3:
					values=values+"\'0.0\'";
					break;
				default:
					values=values+"\'"+data_array[columncount-1]+"\'";
					break;
				
				}
			}
			String sql="insert into "+tablename+" values("+values+");";			
			return sql;
			
	}
public static boolean isNumber(String string)
{
	
    boolean numeric = true;

    numeric = string.matches("-?\\d+(\\.\\d+)?");

    if(numeric)
    		return true;
    else
        
    		return false;


}
public static int checkForQuotes(String s)
{
	char ch='\'';
if(s.contains(ch+""))
{
	return 1;
}
else if(s.contains("\""))
{	
	return 2;
}
else if(s.contains("\\N"))
{
	return 3;
}
return -1;
}
}
class Jdbc {
	Statement statement;
	Connection connection;
	public Jdbc()
	{
		String driver = "com.mysql.jdbc.Driver";  
	    String url = "jdbc:mysql://localhost/test";
	    String username = "root";
	    String password = "tiger";  
	   try{
	      Class.forName("com.mysql.jdbc.Driver");
	      this.connection = DriverManager.getConnection(url,username,password);
	      this.statement = connection.createStatement();
	          
	     
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	}



    public void insertData(String sql)
   {
	   try {
		statement.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    
	   // System.out.println(sql);
		e.printStackTrace();
	}
   }

      public void close()
   {
	   try {
		statement.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    
		e.printStackTrace();
	}
	   try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	    

		e.printStackTrace();
	}
   }
   
}