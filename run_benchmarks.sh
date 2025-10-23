#!/usr/bin/env bash
set -euo pipefail

SIZES=(10 50 100 256 512)
RUNS=10
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
mkdir -p "$ROOT_DIR/results"

echo "Working from: $ROOT_DIR"

echo "Building Java module..."
mvn -f "$ROOT_DIR/Java/matrix/pom.xml" package

echo "Compiling C benchmark..."
gcc -O2 -o "$ROOT_DIR/benchmark" "$ROOT_DIR/C/benchmark.c" "$ROOT_DIR/C/matrix_multiply.c"

for SIZE in "${SIZES[@]}"; do
  echo "=== Running benchmarks for SIZE=$SIZE, RUNS=$RUNS ==="

  JAVA_OUT="$ROOT_DIR/results/results_java_${SIZE}.csv"
  echo "Running Java: output -> $JAVA_OUT"
  java -jar "$ROOT_DIR/Java/matrix/target/matrix-1.0-SNAPSHOT.jar" "$SIZE" "$RUNS" > "$JAVA_OUT"

  C_OUT="$ROOT_DIR/results/results_c_${SIZE}.csv"
  echo "Running C: output -> $C_OUT"
  "$ROOT_DIR/benchmark" "$SIZE" "$RUNS" > "$C_OUT"

  PY_OUT="$ROOT_DIR/results/results_py_${SIZE}.csv"
  echo "Running Python: output -> $PY_OUT"
  python3 "$ROOT_DIR/Python/main.py" --size "$SIZE" --runs "$RUNS" > "$PY_OUT"

done

echo "All benchmarks finished. Results are in: $ROOT_DIR/results"
