src=src
out=out

main: $(src)/*.cpp
	mkdir -p out
	g++ $(src)/*.cpp -o $(out)/main.o -Wall -std=c++11

clean:
	rm -r $(out)/*

test:
	mkdir -p out
	g++ $(src)/*.cpp -o $(out)/main.o -Wall -std=c++11 && ./$(out)/main.o
