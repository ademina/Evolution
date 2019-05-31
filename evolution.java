import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Main {
  static int foodPerDay = 5;
  static int numAnimals = 9;
  static int foodAvailable = foodPerDay;
  public static void main(String[] args) {
    int time;
    
    Scanner scan= new Scanner(System.in);


    ArrayList<Animal> animals = new ArrayList<Animal>();


    for(int i =0;i<numAnimals;i++ ){
      //add using the .add function
      animals.add(new Animal(50,i));

  
  
    }
    




    System.out.println("Progress to next Day?");
    String str = scan.nextLine();

    while(!str.equals("no") && animals.size() > 0){
      System.out.println("Food Available : " + String.valueOf(foodAvailable));
      System.out.println("");
      for(time=0;time<=24;time++){
        for(int i =0;i<animals.size();i++ ){
          foodAvailable = animals.get(i).hunt(foodAvailable);
          animals.get(i).update();
          if(animals.get(i).isDead()){
            System.out.println("Animal " +String.valueOf(animals.get(i).name) + " died ");
            //remove the animal at position i from the arraylist 
              animals.remove(i);

            
          }
        }

      }
      //end of the day, print all the animals in the animals array's status
      //wrapped in if statement to prevent null 
      if(animals.size() >= 0){
        for (int i =0;i<animals.size();i++){
          animals.get(i).animalStatus();
        }  
      }

      //add the foodperday to our foodAvailable variable 
      foodAvailable += foodPerDay;



      
      System.out.println("Progress to next Day?");
      str = scan.nextLine();
    }
  }
}



class Animal{
  int hunger = 100;
  int sleep = 100;
  int sense,name;
  boolean dead = false;

  public Animal(int s, int n){
    sense = s;
    name = n;
  }

  public void setHunger(int hunger){
    this.hunger = hunger;
  }
  
  public boolean isDead(){
    if(this.dead == true){
      return true;
    }
    else{
      return false;
    }
  }

  public void createNewAnimal(){
    //create a new animal and set its hunger to 0, as well as add it to the animals arraylist and set our own hunger to 100

    //fix this error by returning the animal and doing the animals food over 200 check inside the main function 
    animals.add(new Animal(50,animals.size()+1));
    this.setHunger(100);
  }



  public void update(){
    this.hunger--;
    this.sleep--;


    //if our hunger goes below 0, die
    if(this.hunger == 0){
      this.dead = true;
    }

    //if our hunger is 200 or above, create a new animal
    if(this.hunger >= 200){
      this.createNewAnimal();
    }
  }
  public void animalStatus(){
   System.out.print("Animal Name : ");
    System.out.println(this.name);
    System.out.print("Hunger-");
    System.out.println(this.hunger);
    System.out.print("Sleep-");
    System.out.println(this.sleep);
    System.out.println("");
  }

  public int hunt(int foodLeft){
    Random rand = new Random();

    
    int foodFound = ((this.sense+rand.nextInt(151))*foodLeft/6);

    if(foodFound >= 80){
      System.out.println("animal: " + String.valueOf(this.name) +  " found food");
      System.out.println("");
      this.hunger += 25;
      foodLeft--;
    }

    return foodLeft;
  }
}





// P(food) = (sence+random(1,150) )*foodLeft/5
// if P(food)>=80  survive