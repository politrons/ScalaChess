clean:
	sbt clean

test:
	sbt test

build:
	sbt assembly

run-game:
	java -cp ./target/scala-2.12/ScalaChess-assembly-0.1.jar  com.politrons.app.ChessApp ./src/main/resources/sample-moves.txt 2000



