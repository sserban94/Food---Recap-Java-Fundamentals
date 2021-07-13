package ro.ase.acs.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Omelette implements Cloneable, Runnable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	List<Ingredient> ingredients = null;

	
	public Omelette()
	{
		
	}
	public Omelette(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Ingredient> getIngredients() {
		List<Ingredient> tempList = new ArrayList<>();
		tempList.addAll(ingredients);
		return tempList;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = new ArrayList<>();
		this.ingredients.addAll(ingredients);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Omelette copy = (Omelette) super.clone();
		copy.setIngredients(ingredients);
		return copy;

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This omelette contains: ");
		for (Ingredient ing : ingredients) {
			builder.append(ing.name);
			builder.append(" ");
		}
		return builder.toString();
	}

	public int calculateTotalQuantity() {
		int sum = 0;
		for (Ingredient ing : ingredients) {
			sum += ing.quantity;
		}
		return sum;
	}

	@Override
	public void run() {
		System.out.println(calculateTotalQuantity());
	}
	
	public void serialize() throws IOException
	{
		FileOutputStream stream = new FileOutputStream("omelette.data");
		ObjectOutputStream oos = new ObjectOutputStream(stream);
		oos.writeObject(this);
		oos.close();		
	}
	
	public void deserialize() throws ClassNotFoundException, IOException
	{
		FileInputStream stream = new FileInputStream("omelette.data");
		ObjectInputStream ois = new ObjectInputStream(stream);
		Omelette omelette = (Omelette) ois.readObject();
		this.setIngredients(omelette.ingredients);
		ois.close();
	}

}
















