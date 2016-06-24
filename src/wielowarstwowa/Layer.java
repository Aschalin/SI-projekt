package wielowarstwowa;

public class Layer
{
	Neuron[] neurons;
	int quantity, input_size;
	
	public Layer(int input_quantity, int neurons_quantity)
	{
		input_size = input_quantity;
		quantity = neurons_quantity;
		neurons = new Neuron[neurons_quantity];
		for(int i = 0; i < neurons_quantity; i++)
		{
			neurons[i] = new Neuron(input_quantity);
		}
	}
	
	public double[] feed(double[] input)
	{
		double[] result = new double[quantity];
		
		for(int i=0; i<quantity; i++)
		{
			result[i]=neurons[i].feed(input);
		}
		return result;
	}
	
}
