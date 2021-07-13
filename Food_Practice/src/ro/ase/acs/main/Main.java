package ro.ase.acs.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ro.ase.acs.classes.Ingredient;
import ro.ase.acs.classes.Omelette;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Ingredient ingredient1 = new Ingredient("Eggs", 6, 4);
		Ingredient ingredient2 = new Ingredient("Cheese", 1, 8);
		Ingredient ingredient3 = new Ingredient("Ham", 4, 9);

		Omelette omelette1 = new Omelette(Arrays.asList(ingredient1, ingredient2, ingredient3));
		try {
			Omelette omelette2 = (Omelette) omelette1.clone();
			List<Ingredient> otherIngredients = omelette2.getIngredients();
			otherIngredients.set(2, new Ingredient("Olives", 10, 10));
			otherIngredients.add(new Ingredient("Tomatoes", 2, 5));
			System.out.println(otherIngredients.get(2));
			omelette2.setIngredients(otherIngredients);
			System.out.println("First omelette");
			System.out.println(omelette1.toString());
			System.out.println("Second omelette");
			System.out.println(omelette2.toString());
			System.out.println(omelette1.getIngredients().stream().count());
			System.out.println(omelette2.getIngredients().stream().count());

			new Thread(omelette1).start();
			new Thread(omelette2).start();

			Runnable r = () -> {
				try {
					omelette2.serialize();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
			new Thread(r).start();

//			Runnable r1 = () -> {
//				Omelette dOmelette = new Omelette();
//				try {
//					dOmelette.deserialize();
//				} catch (ClassNotFoundException | IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(dOmelette.toString());
//			};
			System.out.println("Here comes the stream");
			List<Ingredient> filteredIngredients = omelette1.getIngredients().stream().filter(x -> x.getQuantity() > 5)
					.collect(Collectors.toList());
			for (Ingredient ingred : filteredIngredients) {
				System.out.println(ingred.getName());
			}

			try {
				omelette1.serialize();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			Omelette deserializedOmelette = new Omelette();
//			try {
//				deserializedOmelette.deserialize();
//				System.out.println("This is the deserialized omelette");
//				System.out.println(deserializedOmelette.toString());
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
