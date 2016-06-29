package wielowarstwowa;

public class Neuron
{
	double [] wages;
	double wage0;
	double memory[];
	double e;
	Activator activator;
	Neuron(int input_quantity, Activator activator)
	{
		this.activator = activator;
		wages = new double[input_quantity];
		for(int i = 0; i < wages.length; i++)
		{
			wages[i] = Math.random()*2-1;
		}
		wage0 = Math.random()*2-1;
	}
	double feed(double[] x)
    {
		memory = x;
		double result = wage0;
        for(int i=0; i<wages.length;i++)
        {
        	result+=wages[i]*x[i];
        }
        e=result;
        return activator.Activate(result);
    }
	
	double[] learn(double error_values[], double learn_factor)
	{
		double error=0;
		for(int i = 0; i<error_values.length; i++)
		{
			error+=error_values[i];
		}
		double learn_const= learn_factor*error;
		double return_errors[] = new double[wages.length];
		for(int i = 0; i<wages.length; i++)
		{
			return_errors[i]= error * memory[i];
			wages[i]+= learn_const *activator.Deactivate(e) * memory[i];
			System.out.println("wage n.o.: " + i + " wage value = "+ wages[i] + " learn const:" + learn_const + "  " + "e="+ e + " activator= " + activator.Activate(e) + " deactivator=  " + activator.Deactivate(e) + "  " + memory[i]);
		}
		try{
			Thread.sleep(10);
		}catch(Exception e)
		{
			System.out.println("sleep exception");
		}

		wage0 += learn_const*activator.Deactivate(e);
		return return_errors;
	}
}
