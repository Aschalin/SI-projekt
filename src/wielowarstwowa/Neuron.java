package wielowarstwowa;

public class Neuron
{
	int [] wages;

	Neuron( int input_quantity)
	{
		wages = new int[input_quantity];
		for(int wage: wages)
		{
			wage = (int) Math.random();
		}
	}
	double feed(double[] x)
    {
        double result = 0;
        for(int i=0; i<wages.length;i++)
        {
            result+=wages[i]*x[i];
        }
        return result;
    }
}
