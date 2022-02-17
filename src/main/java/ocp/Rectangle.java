package ocp;

public class Rectangle implements Shape{
	public Double length;
    public Double width;
    
	@Override
	public Double calculateArea() {
		return length * width;
	}
}
