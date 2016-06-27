package wielowarstwowa;

public class Neuron
{
	double [] wages;
	double wage0;
	double memory[];
	Neuron( int input_quantity)
	{
		wages = new double[input_quantity];
		for(double wage: wages)
		{
			wage = (double) Math.random();
		}
		wage0 = (double) Math.random();
	}
	double feed(double[] x)
    {
		memory = x;
		double result = wage0;
        for(int i=0; i<wages.length;i++)
        {
        	result+=wages[i]*x[i];
        }
        return funkcja_aktywacji(result);
    }
	
	double[] learn(double error_values[], double learn_factor)
	{
		double error=0;
		for(int i = 0; i<error_values.length; i++)
		{
			error+=error_values[i];
		}
		double learn_const= learn_factor*rozniczka_z_funkcji_aktywacji();
		double return_errors[] = new double[wages.length];
		for(int i = 0; i<wages.length; i++)
		{
			return_errors[i]=error*wages[i];
			wages[i]+= learn_const * memory[i];
		}
		wage0 += learn_const;
		return return_errors;
	}
}
