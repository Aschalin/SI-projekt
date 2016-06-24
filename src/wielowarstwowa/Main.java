package wielowarstwowa;

import java.util.Scanner;

public class Main
{
	int input_size = 3;
	int layers_quantity = 3;
	int[] layers_size = {3, 3, 3};
	
	Network net = new Network(input_size, layers_quantity, layers_size);
	
}
