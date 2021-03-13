# The focus of versions 2.x was to improve computation speed, in the hopes of creating a navigable real-time mandelbrot set application.

In order to compare computation speed, different versions of the program were timed while generating the same image.
This speedup originates from the implementation of heuristics in order to short circuit computation whenever we are confident that a point is a part of the mandelbrot set.
In order to showcase the value of this speedup, we must also verify that the results computed through heuristics matches the real values. 
You can see that the images are nearly identical.

## Case 1: Moderate number of points in Mandelbrot Set
- Image dimensions : 1920x1080
- Number of iterations : 50,000
- Number of threads : 4
- Center point : 0,0
- Radius of image : 2

The speeds are documented in the table below

|                  | baseline (v1.3) |  v2.1  |  v2.2  |
|----------------- |:---------------:|:------:|:------:|
| Time taken (sec) |        73       |   11   |   3    |

### Mandelbrot_v1.3
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/MandelbrotSet_v1.3.png" alt="mandelbrot v1.3" height="250"/>

### Mandelbrot_v2.1
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotset_v2.1.png" alt="mandelbrot v2.1" height="250"/>

### Mandelbrot_v2.2
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotset_v2.2.png" alt="mandelbrot v2.2" height="250"/>

## Case 2: Large number of points in Mandelbrot Set
- Image dimensions : 1920x1080
- Number of iterations : 50,000
- Number of threads : 4
- Center point : 0,0
- Radius of image : 0.5

The speeds are documented in the table below

|                               | baseline (v1.3)  |  v2.1  |  v2.2   |
|------------------------------ |:----------------:|:------:|:-------:|
| Time taken (sec)              |        340       |   57   |   13    |
| time taken (case 2 / case 1)  |       4.66       |  5.18  |   4.33  |

### Mandelbrot_v1.3
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotSet_v1.3%20-%20extreme.png" alt="mandelbrot v1.3 extreme" height="250"/>

### Mandelbrot_v2.1
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotset_v2.1%20-%20extreme.png" alt="mandelbrot v2.1 extreme" height="250"/>

### Mandelbrot_v2.2
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotset_v2.2%20-%20extreme.png" alt="mandelbrot v2.2 extreme" height="250"/>
