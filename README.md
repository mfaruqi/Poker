This project is creating Poker, it is a one-player game against a CPU.
This project contanis Texas Holdem poker rules. It has functionality for folding, betting, and checking. 
From the overall way to bet chips the game the two missing parts are raising a bet and small blind, big blind.


USING:
This project just needs to be runned on a java platform. IT NEEDS TO USE THE JSON JAR FILE THAT IS INCLUDED IN THE ZIP FILE. 
Please set it to your class path so you may run the program.
One way to do this on mac terminal is : javac -cp .:json-20201115.jar *.java
You may have to compile the CardDeck file with the json jar file as well, same way as above.
To run it simply remove the "c" and ".java" so it will be : java -cp .:json-20201115.jar Poker

CPU:
When the program pauses a little and the cards switch, this means that the CPU folds. It also says that on the command line. I couldn't figure out how to say "Fold" for a certain period of time to indicate that so instead I used a thread
to just pause the simulation a bit.
The CPU folds on simple commands, with a little bit of randomness and also depending on what it has and the size of the bet. I made it a more type of "tight" player becasue there are no blinds so
there is no cost in being tight. It is still beatable pretty easily so it is no means my best, I will try to work on it in the future.


ERROS:
My code is usually works well without errors.
There is a logic problem with the chips sometimes become over 1000, which it should never do because both players start with 500 chips.
There is also another probelm where sometimes the API crashes and that I am not 100% sure why, I looked into it but was very confused.
Finally my array in my Controller class sometimes goes out of bounds which I am still trying to fix.
If any of these happen the cards will not appear and you may have to end the program and restart it.


MISCELLANEOUS:
This program was completely done by me.
The hands file works completely, and was tested by Project Euler Poker Hands problem. 
There are images in my project and I was not 100% sure how you would access them. So I did ImageIcon = new ImageIcon(Images(image folder)/PokerTable.png);
If that does not work please change the directory to your relative path, I think it should work but just in case it does not.
This occurs twice, once in Panel.java on Line # 27. It also happens in Controller.java on Line # 255 
To show who won I tried making some text come up on the system output. It sometimes does not work so that is a little problem. But who wins the round is always right.





