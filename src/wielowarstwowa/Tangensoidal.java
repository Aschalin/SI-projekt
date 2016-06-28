package wielowarstwowa;

public class Tangensoidal implements Activator
{

	@Override
	public double Activate(double input)
	{
		return ((Math.exp(2*input) - 1) / (Math.exp(2*input) + 1));
	}

	@Override
	public double Deactivate(double input)
	{
		double der = 1d - input * input;
		return der;
	}

}