package com.karthik.grandmaster.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.util.Printer;

/**
* FusionSearch implements BinaryTree for the generic methods that it should provide 
* implementation in future.
* Computes the treesize and returns just the root of the tree currently.
*/
@Component
public class FusionSearch implements BinaryTree {

	@Autowired
	private Printer printer;

	/**
	* Computes the tree and provides the root of the tree.
	*/
	public int getRoot(int lowerbound,int higherbound,int multiplier){

		int treeLength = 0;
		int root = 0;
		
		if(multiplier==0){
			return lowerbound;
		}
		
		treeLength = computeTreeLength(lowerbound,higherbound,multiplier);
		root = ((lowerbound/multiplier)+(treeLength/2))*multiplier;
	
		if(root==0){
			return lowerbound;
		}
		return root;
	}

	/**
	* Computes the tree length.
	*/
	private int computeTreeLength(int lowerbound, int higherbound,int multiplier){
		return (int)Math.ceil(((double)(higherbound-lowerbound))/multiplier)+1;
	}


}
