package dip;

public class DVD implements Product{

	@Override
	public void seeReviews() {
		System.out.println("See Review - Critique DVD");
	}

	@Override
	public void getSample() { 
		System.out.println("Watch Sample - Critique DVD");
   }
}
