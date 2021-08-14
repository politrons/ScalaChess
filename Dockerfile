FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

RUN  apt-get install -y make

WORKDIR /ScalaChess
COPY  . /ScalaChess/
RUN chmod 777 /ScalaChess/run_game.sh
RUN sbt assembly
CMD /ScalaChess/run_game.sh
