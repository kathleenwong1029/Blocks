/**
* The SongLinkedList class uses an array to keep track of variables in a code
* block.
*
* @author Kathleen Wong
*    email : kathleen.wong.1@stonybrook.edu
*    CSE 214 : Homework 3
*    R08 : TA Felix Rieg-Baumhauer
*    Stony Brook ID: 112859743
**/

public class Block {

  Variable vars[]; // array containing variables
  int counter = 0; // number of variable in code block

  /**
  * Instanties a Block by creating an empty array
  **/
  public Block(){
    vars = new Variable[10];
  }

  /**
  * Adds a varible object with given name and value
  * @param _name
  *    Name of variable.
  * @param _initVal
  *    Value of variable.
  **/
  public void addVariable(String name, int num){
    vars[counter] = new Variable(name, num);
    counter ++;
  }

  /**
  * Searches for and prints all local variables.
  **/
  public void printLocal(){
    if(counter == 0){
      System.out.println("No local variables to print.");
    }
    else{
      System.out.println(String.format("%-25s%-25s","Variable Name","Initial Value"));
      for(int i = 0 ; i < counter ; i++){
        System.out.println(String.format("%-25s%-5d",vars[i].getName(),vars[i].getInitialValue()));
      }
    }
  }

  /**
  * Searches for and prints a local variable.
  * @param var
  *    name of variable.
  * @return if variable exists
  **/
  public boolean printVar(String var){
    for (int i = 0 ; i < counter ; i++){
      if(vars[i].getName().equals(var)){
        System.out.println(String.format("%-25s%-25s","Variable Name","Initial Value"));
        System.out.println(String.format("%-25s%-5d",var,vars[i].getInitialValue()));
        return true;
      }
    }
    return false;
  }

}
