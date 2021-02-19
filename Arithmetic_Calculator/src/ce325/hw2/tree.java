package ce325.hw2;

public class tree {

	private double number;
	private char operator;
	private tree parent;
	public tree rightChild;
	private tree leftChild;
	
	public tree(double number, char operator, tree parent) {
		this.number=number;
		this.operator=operator;
		this.parent=parent;
	}
	
	public tree(double number, char operator, tree parent, tree rigthChild, tree leftChild) {
		this.number=number;
		this.operator= operator;
		this.parent=parent;
		this.rightChild= new tree(0, '0', parent);
		this.leftChild=  new tree(0, '0', parent);
	}
	
	public void setParent(tree parent) {
		this.parent = parent;
	}
	
	public tree getParent() {
		return parent; 
	}
	
	public void addLChild(tree Lchild) {
		Lchild.parent.leftChild= Lchild;
	}
	
	public void addRChild(tree Rchild) {
		Rchild.parent.rightChild= Rchild;
	}
	
	public void setDouble(double number) {
		
		this.number=number ; 
	}
	
	public char getOperator() {
		return operator;
	}
	
	public void setOperator(char operator) {
		this.operator=operator ; 
	}
	
	public double getDouble() {
		return number;
	}
	
	public tree getRchild(tree node) {
		return node.rightChild;
	}
	
	public tree getLchild(tree node) {
		return node.leftChild;
	}
	
	public void fill(char [] array, int position, tree node, double []numbers) {
		   
		double zero=0;
		int i =0;
		int j=0;
		int k=0;
		int l=0;
		int n=0;
		int counter=0;
		
		
		for(i=array.length-1, counter=0; i>0; i-- ) {
			if(array[i]==')') {
				counter++;
			}
			if(array[i]=='(') {
				counter--;
			}
			if((array[i]== '+' || array[i] == '-') && counter==0) {
				   
				tree rightnode = new tree (zero, '\0',  node);
				tree leftnode = new tree (zero, '\0',  node);
				char[] rightArrayOp = new char[array.length - i -1 ];
				double[] rightArrayNum = new double[array.length - i-1 ];
				char[] leftArrayOp = new char [i];
				double[] leftArrayNum = new double[i];
				for(j=0, k=i+1; j< array.length - i-1 ; j++, k++) {
					rightArrayOp[j] = array[k];
					rightArrayNum[j] = numbers[k];
				}
				
				for(j=0; j< i; j++) {
					leftArrayOp[j]= array[j];
					leftArrayNum[j] = numbers[j];
				}
				
				node.setOperator(array[i]);
				fill(rightArrayOp, position, rightnode, rightArrayNum);
				node.addRChild(rightnode);
				fill(leftArrayOp, position, leftnode, leftArrayNum);
				node.addLChild(leftnode);
				return;
			}
		}
		for(i=array.length-1, counter=0; i>0; i-- ) {
			if(array[i]==')')
				counter++;
			if(array[i]=='(')
				counter--;
			if((array[i]== '*' || array[i] == '/' || array[i] == 'x') && counter==0) {
	    			   
				tree rightnode = new tree (zero, '\0',  node);
				tree leftnode = new tree (zero, '\0',  node);
				char[] rightArrayOp = new char[array.length - i -1];
				char[] leftArrayOp = new char [i];
				double[] rightArrayNum = new double[array.length - i-1 ];
				double[] leftArrayNum = new double[i];
				for(j=0, k=i+1; j< array.length - i-1; j++, k++) {
					rightArrayOp[j] = array[k];
					rightArrayNum[j] = numbers[k];
				}
				for(j=0; j< i; j++) {
					leftArrayOp[j]= array[j];
					leftArrayNum[j] = numbers[j];
				}
				node.setOperator(array[i]);
				fill(rightArrayOp, position, rightnode, rightArrayNum);
				node.addRChild(rightnode);	   
				node.addLChild(leftnode);
				fill(leftArrayOp, position, leftnode, leftArrayNum);
				return;
			}
		}	
		for(i=array.length-1, counter=0; i>0; i-- ) {
			if(array[i]==')')
				counter++;
			if(array[i]=='(')
				counter--;
			
			if(array[i]== '^' && counter==0) {
	    			   
				tree rightnode = new tree (zero, '\0',  node);
				tree leftnode = new tree (zero, '\0',  node);
				char[] rightArrayOp = new char[array.length - i -1];
				char[] leftArrayOp = new char [i];
				double[] rightArrayNum = new double[array.length - i -1];
				double[] leftArrayNum = new double[i];
				for(j=0, k=i+1; j< array.length - i-1; j++, k++) {
					rightArrayOp[j] = array[k];
					rightArrayNum[j] = numbers[k];
				}
				for(j=0; j< i; j++) {
					leftArrayOp[j]= array[j];
					leftArrayNum[j] = numbers[j];
				}
				node.setOperator(array[i]);
				fill(rightArrayOp, position, rightnode, rightArrayNum);
				node.addRChild(rightnode);
				fill(leftArrayOp, position, leftnode, leftArrayNum);
				node.addLChild(leftnode);
				return;
			}
		}
		for(i=array.length-1, n=0, l=0, counter=0; i>=0; i-- ) {
			if(array[i]==')') {
				counter++;
				if(n==0)
					n=i;
			}
			if(array[i]=='(') {
				counter--;
				l=i;
			}
			if(counter==0 && n!=0) {
				char[]parenthesisOp= new char[n-l-1];
				double []parenthesisNum= new double[n-l-1];
				for(int counterPar=n-1; counterPar>l; counterPar--) {
					parenthesisOp[counterPar-l-1]=array[counterPar];
					parenthesisNum[counterPar-l-1]= numbers[counterPar];
				}
				fill(parenthesisOp, position, node, parenthesisNum);
				return;
			}
		}
		node.setDouble(numbers[position]);
	}
	
	public String toDotString() {
		
		String str = "digraph ArithmeticExpressionTree {\n"
	            + "fontcolor=\"navy\";\n"
	            + "fontsize=20;\n"
	            + "labelloc=\"t\";\n"
	            + "label=\"Arithmetic Expression\"\n";
		
         str = str + toDotString(this);
         str = str  + "}" ;
            return str;
    }
	
	public String toDotString(tree node) {
		
		String str = new String();
		
		if(node == null)
			return "";
		
		if(node.operator == '\0') 
			str = str + node.hashCode() + " [label=\"" + node.getDouble() + "\", shape=circle, color=black]\n" ;
			
		else {
			str = str + node.hashCode() + " [label=\"" + node.getOperator() + "\", shape=circle, color=black]\n" ;
			str= str + node.hashCode() + "->" + node.leftChild.hashCode() + "\n";
			str = str + node.hashCode() + "->" + node.rightChild.hashCode()+ "\n";		
			}
		str = str + toDotString(node.rightChild);
		str = str + toDotString(node.leftChild);
		
		return str;
	}
	
	/*public String toDotString(tree node) {
		
		String str = new String();
		
		if(node == null) {
			return "";
		}
		if(node.operator == '\0') {
			str = str + node.hashCode() + " [label=\"" + node.getDouble() + "\", shape=circle, color=black]\n" ;
			if(getHash(node)!=0)
				str = str + node.hashCode() + "->" + getHash(node) + "\n";
			
		}
		else {
			str = str + node.hashCode() + " [label=\"" + node.getOperator() + "\", shape=circle, color=black]\n" ;
			str = str + node.hashCode() + "->" + getHash(node) + "\n";
		}
		str = str + toDotString(node.leftChild);
		str = str + toDotString(node.rightChild);
		
		
		return str;
	}
	
	public int getHash(tree node) {
		
		if(getLchild(node)!=null)
			return node.leftChild.hashCode();
		
		tree curr = node;
		tree parent = curr.parent;
		while(parent!=null && parent.rightChild == curr) {
			
			curr = curr.parent;
			parent = parent.parent;
		}
		if(parent == null)
			return 0;
		
		return parent.rightChild.hashCode();
	}*/
	
	public String toString(tree node) {

		String str=new String();
        if (this.getLchild(node) == null) {
        	if(this.operator!='\0') 
        		str =str + this.operator;
        	else 
        		str = str + this.number;
        	
        	return str;
        }

        str = str + "(" + this.getLchild(node).toString();
        if(this.operator!='\0') 
        	str = str + this.operator;
        else
        	str = str + this.number;
        
        str = str + this.getRchild(node).toString();
        
        str = str + ")";
        
        return str;
    }

	public String toString() {
		
		return toString(this);
	}
	
	public double calculate() {

		return calculate(this);
	}
	
	public double calculate(tree node) {
				
		if(node == null) {
			return 0;
		}
		
		while ( (node.rightChild.getDouble()== 0) || (node.leftChild.getDouble()==0) ) {
			if(node.leftChild.getDouble()==0)
				calculate(node.leftChild);
			if(node.rightChild.getDouble()==0)
				calculate(node.rightChild);
		}
		if(node.getOperator()=='+') {
			node.number= node.rightChild.getDouble() + node.leftChild.getDouble();
		}
		else if(node.getOperator()=='-') {
			node.number = node.leftChild.getDouble() - node.rightChild.getDouble(); 
		}
		else if(node.getOperator()=='*' || node.getOperator()== 'x') {
			node.number = node.rightChild.getDouble() * node.leftChild.getDouble(); 
		}
		else if(node.getOperator()=='/') {
			node.number = node.leftChild.getDouble() / node.rightChild.getDouble(); 
		}
		else if(node.getOperator()=='^') {
			node.number = Math.pow(node.leftChild.getDouble(), node.rightChild.getDouble()); 
		}
		
		return node.number;
	
	}

	
}
