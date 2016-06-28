package wielowarstwowa;

public class Neuron
{
	double [] wages;
	double wage0;
	double memory[];
	Neuron(int input_quantity)
	{
		wages = new double[input_quantity];
		for(double wage: wages)
		{
			wage = (double) Math.random();
		}
		wage0 = (double) Math.random();
	}
	double feed(double[] x, int aktywator)
    {
		memory = x;
		double result = wage0;
        for(int i=0; i<wages.length;i++)
        {
        	result+=wages[i]*x[i];
        }
		switch(aktywator)
		{
			case 0:
				return aktywacja_lin(result);
			case 1:
				return aktywacja_sig(result);
			default:
				return aktywacja_sig(result);
		}
    }

	double aktywacja_sig(double input)
	{
		return (1/(1+Math.exp(-0.5*input)));
	}

	double aktywacja_lin(double input)
	{
		return (input*0.5);
	}
	double aktywacja_tan(double input)
	{
		return ((Math.exp(2*input) - 1) / (Math.exp(2*input) + 1));
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
