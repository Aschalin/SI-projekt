package wielowarstwowa;

import java.util.Random;

public class neuronek {
    double[] w;
    double w0;
    neuronek(int wejsc)
    {
        Random rand = new Random();
        w = new double[wejsc];
        w0 = rand.nextFloat() * 10;
        for(int i=0;i<wejsc;i++)
        {
            w[i]=rand.nextFloat() * 5;
        }
    }
    
    neuronek(int wejsc, double przedzia造[][])
    {
        Random rand = new Random();
        w = new double[wejsc];
        w0 = rand.nextDouble() * 2 - 1;
        for(int i=0;i<wejsc;i++)
        {
            w[i]=przedzia造[i][0] + rand.nextDouble() * (przedzia造[i][1] - przedzia造[i][0]);
        }
    }
    
    double feed(double[] x)
    {
        double result = 0;
        for(int i=0; i<w.length;i++)
        {
            result+=w[i]*x[i];
        }
        return result;
    }
   
    void learn(int ile_epok, double[][]x_ucz,double[] d_ocz,double theta)
    {
        for(int j=0;j<ile_epok;j++)
        {
            for(int i=0;i<d_ocz.length;i++)
            {
                double y = feed(x_ucz[i]);
                double e = d_ocz[i] - y;
                for(int k=0;k<=w.length-1;k++)
                {
                    w[k] = w[k] + theta * e *x_ucz[i][k];
                }
                w0 = w0 + theta * e;
            }
        }
    }
   
    void wypisz()
    {
        for(int i=0;i<w.length;i++)
        {
            System.out.print(w[i] + "   ");
        }
        System.out.println();
    }
    
    public void update_weight(double[] x, double learning_rate)
    {
    	for(int i=0;i<x.length;i++)
    	{
    		w[i] = w[i] + learning_rate * (w[i] - x[i]);
    	}
    }
   
   
    public static void main(String[] args) {
        double[][] x_ucz=new double[][] {{-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,1},{1,-1,1,1,1,1,-1,-1,1,-1,-1,1}};
        double[] d_oczek = new double[] {-1,1};
        neuronek neuron = new neuronek(12);
        neuron.learn(500, x_ucz, d_oczek, 0.05);
        //neuron.wypisz();
        System.out.println("------------");
        double[][] x_test=new double[][]{{-1,-1,1,-1,-1,1,-1,-1,1,-1,-1,1},{1,-1,1,1,1,1,-1,-1,1,-1,-1,1},
                                        {-1,1,1,-1,-1,1,-1,-1,1,-1,-1,1},{-1,1,1,1,1,1,-1,-1,1,-1,-1,1},
                                        {-1,-1,1,-1,-1,1,-1,1,1,-1,-1,1},{1,-1,1,1,-1,1,1,1,1,-1,-1,1}};
        for(int i=0;i<x_test.length;i++)
        {
            System.out.println(neuron.feed(x_test[i]));
        }
    }

}