{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  packages = with pkgs; [
    maelstrom-clj
    graalvmPackages.graalvm-ce
    maven
    gcc
    zlib
    zlib.static
    graphviz
    gnuplot
		lsof
  ];

  shellHook = ''
    export JAVA_HOME=${pkgs.graalvmPackages.graalvm-ce}
    echo "java: $(java -version 2>&1 | head -1)"
    echo "native-image: $(native-image --version 2>&1 | head -1)"
  '';
}
