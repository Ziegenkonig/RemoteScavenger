package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.utils.Array;

import parts.CPU;
import parts.Microphone;
import parts.Parts;
import parts.PersonalityChip;
import parts.PowerSupply;
import parts.Thermometer;

public class Inventory {
	public int size; //Total slots
	public int capacity; //Slots filled
	public int remaining; //Slots left
	
	//Object list that represents the items within contents
	public List<Parts> contents;
	
	public Inventory (int limit) 
	{
		size = limit;
		capacity = 0;
		remaining = size-capacity;
		
		contents = new ArrayList<Parts>();
	}
	
	//Method that allows an object to be stored into contents
	public void storeItem (Parts item)
	{
		if (contents.size() <= size) {
			contents.add(item);
			capacity ++;
		}
	}
	
	//Method that allows an object to be taken out of contents
	public void removeItem (Object item)
	{
		if (contents.size() > 0 && contents.contains(item)) {
			contents.remove(item);
			capacity --;
		}
	}
	//Method that sorts contents by type
	public void sortType ()
	{
		
	}
	//Method that sorts contents alphabetically
	public void sortName ()
	{
		Collections.sort(contents);
	}
	//Method that gets all of the objects stored within contents
	public Array<Object> getItems () 
	{
		Array<Object> tmp = new Array<Object>();
		for (int i = 0; i < capacity; i++)
			tmp.add(contents.get(i));
		
		return tmp;
	}
	//toString method that when called returns a string with all of the items inside the contents
	public String toString () 
	{
		String tmp = "";
		for (int i = 0; i < capacity; i++)
			tmp = tmp + (contents.get(i).toString()) + "\n";
		return tmp;
	}
	
	public static void main()
	{
		Inventory inv = new Inventory(100);
		inv.storeItem(new Microphone());
		inv.storeItem(new CPU());
		inv.storeItem(new PersonalityChip(0));
		inv.storeItem(new Thermometer());
		inv.storeItem(new PowerSupply());
		System.out.println(inv);
		inv.sortName();
	}
}
