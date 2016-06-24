package wielowarstwowa;

public class Network
{
	Layer[] layers;
	int input_size, output_size;
	
	Network(int input_quantity, int layers_quantity, int[] layers_size)
	{
		input_size = input_quantity;
		output_size = layers_size[layers_quantity-1];
		layers = new Layer[layers_quantity];
		
		layers[0] = new Layer(input_quantity, layers_size[0]);
		for(int i = 1; i < layers_quantity; i++)
		{
			layers[i] = new Layer(layers_size[i-1], layers_size[i]);
		}
	}
	
	public double[] feed(double[] input)
	{
		for(Layer layer: layers)
		{
			input = layer.feed(input);
		}
		return input;
	}
}
