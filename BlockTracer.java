/**
* The BlockTracer class uses stacks to represent the code blocks of a c file.
*
* @author Kathleen Wong
*    email : kathleen.wong.1@stonybrook.edu
*    CSE 214 : Homework 3
*    R08 : TA Felix Rieg-Baumhauer
*    Stony Brook ID: 112859743
**/

import java.util.Stack;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.File;

public class BlockTracer{

  /**
  * Allows user to interact with the meny and playlist.
  * @param String[] args
  *    Command-line arguments.
  * @exception FileNotFoundException
  *    Indicates that the file was not found.
  **/
  public static void main(String[] args) throws FileNotFoundException {
    try{
      System.out.print("Please input a filename: ");
      Scanner sc = new Scanner(System.in);
      String fileName = sc.nextLine();

      File file = new File(fileName);
      Scanner stdin = new Scanner(file);

      Stack<Block> pancakes = new Stack<Block>();
      int blockCounter = 0;
      String name = "";
      Block cake = new Block();
      String line = stdin.nextLine();
      try{
        while(!(line.equals(null))){
          line = stdin.nextLine();

          if(line.contains("{")){
            cake = new Block();
            pancakes.push(cake);
            blockCounter ++;
          }

          if(line.contains(" int ")){
            String[] ints = line.split(" int ");
            int size = ints.length;
            for(int j = 1; j < size ; j++){
              String data = ints[j].substring(0,ints[j].indexOf(";"));
              if(data.indexOf(",") > 0){
                String[] split = data.split("\\s+");
                for(int i = 0; i < split.length ; i ++){
                  if(!(split[i].indexOf("=") == -1)){
                    int equal = split[i].indexOf("=");
                    int num = 0;
                    if(!(split[i].indexOf(",") == -1)){
                      num = Integer.parseInt(split[i].substring(equal+1,split[i].length()-1));
                    }
                    else{
                      num = Integer.parseInt(split[i].substring(equal+1));
                    }
                    cake.addVariable(split[i].substring(0,equal), num);
                  }
                  else if(i == (split.length - 1)){
                    cake.addVariable(split[i].substring(0),0);
                    break;
                  }
                  else if(!(split[i + 1].equals("="))){
                    cake.addVariable(split[i].substring(0,split[i].length()-1),0);
                  }
                  else{
                    int noComma = 0;
                    if((split[i+2].substring(split[i+2].length() - 1).equals(","))){
                      noComma = Integer.parseInt(split[i+2].substring(0,split[i+2].length()-1));
                    }
                    else{
                      noComma = Integer.parseInt(split[i+2].substring(0,split[i+2].length()));
                    }
                    cake.addVariable(split[i], noComma);
                    i+=2;
                  }
                }
              }
              else if(data.indexOf("=") == -1){
                cake.addVariable(data.substring(0),0);
              }
              else{
                String[] split = data.substring(0,data.length()).split("\\s+");
                int equalsIndex = data.indexOf("=");
                if(split.length==1){
                  int numAdd = Integer.parseInt(data.substring(equalsIndex+1, data.length()));
                  cake.addVariable(data.substring(0, equalsIndex), numAdd);
                }
                else{
                  int numAdd = Integer.parseInt(data.substring(equalsIndex+2, data.length()));
                  cake.addVariable(data.substring(0, equalsIndex - 1 ), numAdd);
                }
              }
            }
          }

          if(line.contains("/*$print LOCAL")){
            Block local = pancakes.peek();
            local.printLocal();
            System.out.println("\n");
          }
          else if(line.contains("/*$print")){
            String var = line.substring(line.indexOf("*") + 8 , line.indexOf("*", line.indexOf("*") + 1));
            Block local = pancakes.peek();
            Stack<Block> pancakes2 = new Stack<Block>();
            int count = blockCounter;

            while(count > 0){
              if(local.printVar(var)){
                System.out.println("\n");
                break;
              }
              else{
                pancakes2.push(pancakes.pop());
                if(!pancakes.empty()){
                  local = pancakes.peek();
                }
                count --;
              }
            }

            if(count == 0){
              System.out.println("Variable not found: " + var + "\n");
            }
            while(count < blockCounter){
              pancakes.push(pancakes2.pop());
              count ++;
            }
          }

          if(line.contains("}")){
            Block bob = pancakes.pop();
          }

        }
      }
      catch(NoSuchElementException e){
        System.exit(0);
      }
    }
    catch(FileNotFoundException e){
      System.out.println("File not Found. Please recompile and enter a valid file.");
    }
  }
}
