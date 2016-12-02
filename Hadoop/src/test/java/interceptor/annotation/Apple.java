package interceptor.annotation;

import interceptor.annotation.FruitColor.Color;

public class Apple {

	@FruitName("Apple")
	private String fruitName;
	
	@FruitColor(fruitColor = Color.RED)
	private String appleColor;
	
	@FruitProvider(id=1, name="陕西红富士集团", address="陕西省西安市延安路88号")
	private String appleProvider;
	
	
	public void setAppleProvider(String appleProvider) {
		this.appleProvider = appleProvider;
	}
	
	public String getFruitName() {
		return fruitName;
	}

	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}

	public String getAppleProvider() {
		return appleProvider;
	}
	
	public void displayName() {
		System.out.println("水果的名字是：苹果");
	}
}
