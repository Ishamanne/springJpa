package decorative.design.pattren;

public class FoodDecorator implements Food{
private Food foodOrder;

FoodDecorator(Food foodOrder){
	this.foodOrder = foodOrder;
	
}
	
	
	@Override
	public String prepareFood() {
		
		return foodOrder.prepareFood();
	}

	@Override
	public double foodPrice() {
		// TODO Auto-generated method stub
		return foodOrder.foodPrice();
	}
	
	
	

}
