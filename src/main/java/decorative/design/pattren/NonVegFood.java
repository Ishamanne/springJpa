package decorative.design.pattren;

public class NonVegFood  extends FoodDecorator{

	NonVegFood(Food foodOrder) {
		super(foodOrder);
		
	}
	
	public String prepareFood() {
		 return super.prepareFood() +" With Roasted Chiken and Chiken Curry  ";   
	}
	
	public double foodPrice() {
		
		return super.foodPrice()+150.0;  
	}

}
