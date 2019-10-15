The proposed solution will handle following scenarios 

1) No restriction .. Santa will be selected based on random shuffle 
2) You can add restriction like avoid santa from same family using family restriction list
3) you can prevent same santa by providing last x years santa as colon separated list as part of previous_santas

# HOW TO RUN
Compile using maven 

## mvn clean install 

# how to run 
csv file look like as follow

name,familymember,previous_santas
Olivia,,
Emma,,
Ava,,
Sophia,,
Isabella,,

java -jar secretSanta.jar csv file_path console

you will get output like as follow.
List of Receiver and Giver
Olivia --Santa--> Isabella
Emma --Santa--> Olivia
Ava --Santa--> Emma
Sophia --Santa--> Ava
Isabella --Santa--> Sophia


prevent same santa assignment 

new csv file as follow 
name,familymember,previous_santas
Olivia,,Isabella
Emma,,Olivia
Ava,,Emma
Sophia,,Ava
Isabella,,Sophia


you will get 
List of Receiver and Giver
Olivia --Santa--> Emma
Emma --Santa--> Isabella
Ava --Santa--> Sophia
Sophia --Santa--> Olivia
Isabella --Santa--> Ava
