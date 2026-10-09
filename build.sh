#!/usr/env bin
set -eo pipefail

echo "
		BUILD SCRIPT
"


initial_build(){
	mvn clean package
	echo "	[build.sh] testing built jar file against init msg"
	MSG='{"src":"c1","dest":"n1","body":{"type":"init","msg_id":1,"node_id":"n1","node_ids":["n1"]}}'
	printf '%s | java -jar target/turing-1.0-SNAPSHOT.jar\033[0m\n' "$MSG"

	echo '{"src":"c1","dest":"n1","body":{"type":"init","msg_id":1,"node_id":"n1","node_ids":["n1"]}}' | java -jar target/turing-1.0-SNAPSHOT.jar


	echo " [build.sh] testing against init and generate message..."

	INIT_MSG='{"src":"c1","dest":"n1","body":{"type":"init","msg_id":1,"node_id":"n1","node_ids":["n1"]}}'
	GEN_MSG='{"src":"c1","dest":"n1","body":{"type":"generate","msg_id":1}}'

	# TODO: test stdout
	printf '%s\n%s\n' "$INIT_MSG" "$GEN_MSG" | java -jar target/turing-1.0-SNAPSHOT.jar


	echo " [build.sh] clearing stale binaries"
	echo ' [build.sh] rm node'
	rm bin/node || true

	mkdir -p bin/
	native-image -jar target/turing-1.0-SNAPSHOT.jar bin/node
}



echo_message(){
	echo "


			STARTING MAELSTROM: ECHO MESSAGE
	"
	echo "maelstrom test -w echo --bin ./bin/node --node-count 1 --time-limit 10"
	maelstrom test -w echo --bin ./bin/node --node-count 1 --time-limit 10
}


generate_message(){
	echo "


		STARTING MAELSTROM: GENERATE MSG
	"
	maelstrom test -w unique-ids --bin ./bin/node --time-limit 30 --rate 1000 --node-count 3 --availability total --nemesis partition
}

broadcast_message() {

	echo "


		RUNNING MAELSTROM: BROADCAST MESSAGES
	"
	maelstrom test -w broadcast --bin ./bin/node --node-count 1 --time-limit 20 --rate 10
}

initial_build
echo_message
generate_message
broadcast_message
