package wielowarstwowa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class siecNeuronowa
{
	neuronek [] network;
	int p;
	
	public siecNeuronowa(int wymiar)
	{
		p=wymiar;
		network = new neuronek[p];
		for(int i=0; i<p; i++)
		{
			network[i] = new neuronek(4);
		}
		//System.out.print(wymiar);
	}
	
	public siecNeuronowa(int wymiar, double [][] ranges)
	{
		System.out.println("przedzialy:");
		for(double[] range: ranges)
		{
			System.out.println(range[0] + " - " + range[1]);
		}
		p=wymiar;
		network = new neuronek[p];
		for(int i=0; i<p; i++)
		{
			network[i] = new neuronek(4, ranges);
			network[i].wypisz();
		}
		//System.out.print(wymiar);
	}
	
	public double[] neural_feed(double[] x)
	{
		double[] s = new double[p];
		int i=0;
		for(neuronek neuron: network)
		{
			s[i++]=neuron.feed(x);
		}
		return s;
	}
	
	public int [] neural_respond(double[] x)
	{
		int[] respond = new int[p];
		double[] s=neural_feed(x);
		int id_winner=find_winner_neuron(s);
		fill_with_zeros(respond);
		respond[id_winner]=1;
		return respond;
	}
	
	public int find_winner_neuron(double[] s)
	{
//		for(double a:s)
//		{
//			System.out.println(a);
//		}
		int result=0;
		for (int i=0;i<s.length; i++)
		{
			if(s[result]>s[i])
			{
				result=i;
			}
		}
		return result;
	}
	
	public void fill_with_zeros(int[] input)
	{
		for(int i:input)
		{
			i=0;
		}
	}
	
	public void train(double[][] learning_set,int epoch,double learning_rate)
	{
		for(int i=0;i<epoch;i++)
		{
			for(int j=0; j<150; j++)
			{
				double[] s=neural_feed(learning_set[j]);
				int id_winner = find_winner_neuron(s);
				//id_winner = j/50;
				learn_winner(id_winner, learning_set[j], learning_rate);
			}
		}
	}
	
	public void learn_winner(int winner_id, double[] el, double learning_rate)
	{
		network[winner_id].update_weight(el, learning_rate);
	}
	
	
	
	public static void main(String[] args)
	{
        
        double[][] data;
        ArrayList<String[]> listOfLists = new ArrayList<>();
        File input = new File("iris.data");
        Scanner scanLine;
		try
		{
			scanLine = new Scanner(input);
			ArrayList<Double> list;
			ArrayList<String> ans=new ArrayList<>();
			while(scanLine.hasNextLine())
			{
				String line=scanLine.nextLine();
				String l[] = line.split(",");
				
	            listOfLists.add(l);
	            //ans.add(scanNumber.next());
			}
			double[][] ranges  = new double [4][2];
			for(int i=0; i<4;i++)
			{
				ranges[i][0]=100;
				ranges[i][1]=0;
			}
			data = new double[listOfLists.size()-1][4];
			for(int i=0; i<listOfLists.size()-1;i++)
			{
				String[] tmp = listOfLists.get(i);
				for(int j=0;j<4;j++)
				{
					data[i][j] = Double.parseDouble(tmp[j]);
					System.out.println(data[i][0]);
					ranges[j][0] = min(ranges[j][0], data[i][j]);
					ranges[j][1] = max(ranges[j][1], data[i][j]);
				}
			}
//			for (int i=0;i<data.length; i++)
//			{
//				for (int j=0;j<4; j++)
//				{
//					System.out.print(data[i][j] + " ");
//				}
//				System.out.println();
//			}
			siecNeuronowa siec = new siecNeuronowa(3, ranges);
			siec.train(data, 200, 0.13);
			int[] tab = new int[3];
			for(int i=0; i<3;i++)
				tab[i]=0;
			for(int i=0;i<data.length;i++)
			{
				int j=siec.find_winner_neuron(siec.neural_feed(data[i]));
				tab[j]++;
				//System.out.println(j);
			}
			for(int i=0; i<3;i++)
				System.out.print(tab[i]+" ");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private static double max(double d, double e) {
		if(d>e)
			return d;
		else
			return e;
	}

	private static double min(double d, double e) {
		if(d<e)
			return d;
		else
			return e;
	}
}
