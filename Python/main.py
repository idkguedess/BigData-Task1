import argparse

from src.benchmark import run_benchmark

def main():
    parser = argparse.ArgumentParser(
        description="Run matrix multiplication benchmark."
    )
    parser.add_argument("--size", type=int, default=512, help="Size of the matrices")
    parser.add_argument("--runs", type=int, default=10, help="Number of benchmark runs")
    args = parser.parse_args()

    run_benchmark(args.size, args.runs)


if __name__ == "__main__":
    main()