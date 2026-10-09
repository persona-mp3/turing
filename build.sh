#!/usr/env bin
set -eo pipefail

mvn clean package
echo "
		BUILD SCRIPT
"
echo " Testing built jar file..."

MSG='{"src":"c1","dest":"n1","body":{"type":"init","msg_id":1,"node_id":"n1","node_ids":["n1"]}}'
printf '%s | java -jar target/turing-1.0-SNAPSHOT.jar\033[0m\n' "$MSG"

echo '{"src":"c1","dest":"n1","body":{"type":"init","msg_id":1,"node_id":"n1","node_ids":["n1"]}}' | java -jar target/turing-1.0-SNAPSHOT.jar
echo "clearing stale binaries"

# remove stale binaries
echo 'rm node' 
rm node || true

echo " Building native binary..."
echo "native-image -jar target/turing-1.0-SNAPSHOT.jar node
"

native-image -jar target/turing-1.0-SNAPSHOT.jar node

echo " starting maelstrom..."
echo "maelstrom test -w echo --bin ./node --node-count 1 --time-limit 10"
maelstrom test -w echo --bin ./node --node-count 1 --time-limit 10
