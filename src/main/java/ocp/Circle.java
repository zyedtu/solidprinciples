package ocp;

public class Circle implements Shape{
	public Double radius;

	@Override
	public Double calculateArea() {
		return 3.14 * radius * radius;
	}
}
