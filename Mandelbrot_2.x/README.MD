# The focus of versions 2.x was to improve computation speed, in the hopes of creating a navigable real-time mandelbrot set application.

In order to compare computation speed, different versions of the program were timed while generating the same image.
- Image dimensions : 1920x1080
- Number of iterations : 50,000
- Number of threads : 4
- Center point : 0,0
- Radius of image : 2

The speeds are documented in the table below

|                  | baseline (v1.3) |  v2.1  |  v2.2  |
|----------------- |:---------------:|:------:|:------:|
| Time taken (sec) |      93.666     | 11.488 | 3.041  |

This speedup originates from the implementation of heuristics in order to short circuit computation whenever we are confident that a point is a part of the mandelbrot set.
In order to showcase the value of this speedup, we must also verify that the results computed through heuristics matches the real values. 
You can see that the images are nearly identical.


## Mandelbrot_v1.3
This version builds upon v1.1 by adding multiple color schemes. The color scheme in the picture below was created by [RogerTheRabbit](https://github.com/RogerTheRabbit). The aspect ratio of images is still limited to 1:1. </br>
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/MandelbrotSet_v1.3.png" alt="mandelbrot v1.3" height="450"/>

## Mandelbrot_v2.1
First program that generates a picture output. The color scheme was not worked on and the aspect ratio of images was 1:1. </br>
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotset_v2.1.png" alt="mandelbrot v2.1" height="450"/>

## Mandelbrot_v2.2
This version builds upon v1.1 by adding multiple color schemes. The color scheme in the picture below was created by [RogerTheRabbit](https://github.com/RogerTheRabbit). The aspect ratio of images is still limited to 1:1. </br>
<img src="https://github.com/Runtime-Learner/Mandelbrot-set/blob/main/Mandelbrot_2.x/images/mandelbrotset_v2.2.png" alt="mandelbrot v2.2" height="450"/>
