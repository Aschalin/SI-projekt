package wielowarstwowa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		int input_size = 35;
		int layers_quantity = 2;
		int[] layers_size = {10, 26};
		Activator[] layers_activator = {new Sigmoidal(), new Linear()};
		Network net = new Network(input_size, layers_quantity, layers_size, layers_activator);
		
		try
		{
			double[][] data = readFile("data.txt");
			double[][] ans = readFile("ans.txt");
			System.out.println(data.length + " " + data[0].length + ", " + ans.length + " " + ans[0].length);
			net.learn(data, ans, 0.15, 1100);
			double[] test = net.feed(data[1]);
			for(double val: test)
			{
				System.out.println(val + " ");
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	static double[][] readFile(String fileName) throws FileNotFoundException
	{
		double[][] data;
		
	    ArrayList<String[]> listOfLists = new ArrayList<>();
	    File input = new File(fileName);
	    Scanner scanLine = new Scanner(input);
		while(scanLine.hasNextLine())
		{
			String line=scanLine.nextLine();
			String l[] = line.split(" ");
			
	        listOfLists.add(l);
		}
		data = new double[listOfLists.size()][listOfLists.get(0).length];
		for(int i=0; i<listOfLists.size()-1;i++)
		{
			String[] tmp = listOfLists.get(i);
			for(int j=0; j<data[0].length; j++)
			{
				data[i][j] = Double.parseDouble(tmp[j]);
			}
		}
		return data;
	}
}