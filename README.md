# Player-Checker

Spring boot REST API that discriminates between players types:
 -  Players of type `novice` will be sent through a kafka queue.
 -  Players of type `expert` will be saved in a h2 database.
 -  If Players is neither `novice` or `expert` it will not be saved or sent to a queue.


## Requirements
- Java 8 or above
- Maven 3
- Apache Kafka 2.7.0
- Zookeeper 3.5.8 

## Install and Build
```
1. git clone https://github.com/jsalazare/players-checker.git
2. cd players-checker
3. mvn package
```
The created jar file will be located under target/players-checker-1.0.0.jar

## Run the App
```
1. Zookeeper and Apache kafka should be running 
2. Create a new topic named 'novice-players' in kafka using the following command: 
        bin/kafka-topics.sh --create --topic novice-players --bootstrap-server localhost:9092
3. To run the app 
    - Using maven: mvn spring-boot:run
    - Running the jar file: java -jar target/players-checker-1.0.0.jar 
```

To read the events sent to the `novice-players` topic open a new command line, go to your kafka folder and run the command below:
```
bin/kafka-console-consumer.sh --topic novice-players --from-beginning --bootstrap-server localhost:9092
```


## Usage

You can run the following CURL command to process an array of players.
```
curl -XPOST -H "Content-type: application/json" -d '{"players": [{"name": "Sub zero","type": "expert"},{"name": "Scorpion","type": "novice"},{"name": "Reptile","type": "meh"}]}' 'http://localhost:8181/player-checker/v1/player/process'
```
It returns a list in the following format:
```
{
  "result": [
    "player Sub zero stored in DB",
    "player Scorpion sent to Kafka topic",
    "player Reptile did not fit"
  ]
}
```

## Test

To run tests execute `mvn test`

## Optional

You can have a look at H2 inserted data by going to the endpoint http://localhost:8181/h2-console
```
JDBC URL : jdbc:h2:mem:testdb
User Name: sa
Password should be empty
```
then execute `SELECT * FROM PLAYERS_EXPERT `