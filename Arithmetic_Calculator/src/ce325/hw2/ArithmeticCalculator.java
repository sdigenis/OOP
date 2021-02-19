package ce325.hw2;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticCalculator {
	
   public static void main(String []args){
    
       java.util.Scanner sc = new java.util.Scanner(System.in);
       System.out.print("Enter math expression: ");
       String line = sc.nextLine().replaceAll("\\s+", "");
       System.out.println("Math expression is " + line);
       
       int i=0, j=0, l=0, n=0, k=0 ;  
       double numbers[]= new double[1000];
       char []operators= new char[1000];
       double zero=0;    
       char check;
       
       for(k=0; k<line.length(); k++) {
    	   check=line.charAt(k);
    	   if(check !='\t' && check !=' ' && check !='.' && check!='x' && check!='+' && check!= '-' && check!= '*' && check!= '^' && check!='(' && check!=')' && check!='/' && check!= '0' && check!= '1' && check!= '2' && check!= '3' && check!= '4' && check!= '5' && check!= '6' && check!= '7' && check!= '8' && check!= '9') {
    		   System.out.println("Invalid character: " + check );
    		   System.out.println("Exiting...");
			   System.exit(0);
    	   }
    	   if(check == '(') {
    		   l++;
    	   }
    	   if(check == ')') {
    		   n++;
    		   if(n>l) {
    			   System.out.println("Invalid expression given.");
    			   System.out.println("Exiting...");
    			   System.exit(0);
    		   }  
    	   }
       }
       if(n!=l) {
		   System.out.println("Invalid expression given.");
		   System.out.println("Exiting...");
		   System.exit(0);
	   }
       check= '0';
       k=0;
       Pattern pattern = Pattern.compile(("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)\\/)\\/(\\^]|[x]))"));
       Matcher match = pattern.matcher(line);		
			
       while (match.find()) {		
    	   check=match.group().charAt(0);
    	   if( j>=1 && operators[j-1]!='0' && operators[j-1]!=')'  && (check == '+' || check == '-' || check == '*' || check == '/' || check == '^' || check == 'x') ) {
    		   System.out.println("Wrong format (two operators)!!");
    		   System.out.println("Exiting...");
               System.exit(0);
    		   return;  
    	   }
    	   if( j>=1 && operators[j-1]=='(' && (check == '+' || check == '-' || check == '*' || check == '/' || check == '^' || check == 'x') ) {
    		   System.out.println("Parenthesis-operator error");
    		   return;
    	   }
    		      	   
    	   if(check == ')') {
    		   operators[j]=match.group().charAt(0);
    		   n++;
    		   j++;
    		   continue;
    	   }
    	   else if(check == '(') {
    		   operators[j]=match.group().charAt(0);
    		   l++;
    		   j++;
    		   continue;
    	   }
    	   if(k%2 == 0) {
    		   numbers[j]=Double.parseDouble(match.group());
    		   operators[j]='0';
    		   //System.out.println( j + ": " +  numbers[j] );
    		   j++;
    	   }
    	   else {
    		   operators[j]=match.group().charAt(0);
    		   //numbers[j] = 0.000000000001 ;
    		   //System.out.println( j + ": " +  operators[j] );
    		   j++;
    	   }
    	   k++;
       }
       
       for(i=0; operators[i]!='\0'; i++ ) ;
       char []finalOperators= new char[i];
       double []finalNumbers= new double[i];
       char [] brackets= new char [i];
       l=0;
       n=0;
       
       for(i=0; i< finalOperators.length; i++) {
    	   finalOperators[i]= operators[i];
    	   finalNumbers[i]= numbers[i];
    	   if(operators[i]=='(' ) {
    		   brackets[i]= operators[i];
    		   n=i;
    	   }
    	   else if(operators[i] == ')') {
    		   brackets[i]= operators[i];
    		   l=i;
    	   }
    	   else 
    		   brackets[i]='0';
       }
       
      tree root = new tree(zero, '0', null);
      root.fill(finalOperators, 0, root,  finalNumbers);
      
      try {        
    	  PrintWriter pfile = new PrintWriter("ArithmeticExpression.dot");
    	  pfile.println(root.toDotString());
    	  pfile.close();
    	  System.out.println("PRINT DOT FILE OK!");
    	         
    	  Process p = Runtime.getRuntime().exec("dot -Tpng ArithmeticExpression.dot " + "-o ArithmeticExpression.png");
    	  p.waitFor();
    	  System.out.println("PRINT PNG FILE OK!");
    	} 
      	catch(Exception ex) {
      		System.err.println("Unable to write dotString!!!");
      		ex.printStackTrace();
      		System.exit(1);
      	}

      System.out.println(root.toString());
      
      System.out.println(root.calculate());
   } 
}

