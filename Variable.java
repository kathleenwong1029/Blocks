/**
* The Variable class represents an int variable.
*
* @author Kathleen Wong
*    email : kathleen.wong.1@stonybrook.edu
*    CSE 214 : Homework 3
*    R08 : TA Felix Rieg-Baumhauer
*    Stony Brook ID: 112859743
**/

public class Variable {

  String name; // name of variable
  int initialValue; // value of variable


  /**
  * Instantiates a varible object with given name and value
  * @param _name
  *    Name of variable.
  * @param _initVal
  *    Value of variable.
  **/
  public Variable(String _name ,int initVal){
    name = _name;
    initialValue = initVal;
  }

  /**
  * Gets the name of the variable
  * @return name of variable
  */
  public String getName(){
    return name;
  }

  /**
  * Gets the value of the variable
  * @return value of variable
  */
  public int getInitialValue(){
    return initialValue;
  }

}
