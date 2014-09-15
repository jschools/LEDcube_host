LED Cube Sequencer
============

An LED cube is only as cool as the designs it can display. Programming in C on a device with a small amount of memory (Arduino) is a pain, so why not generate the designs on a computer?

This is an Eclipse project that allows you to generate 3D designs, preview them, and send them to a serial device. The graphical component uses the Processing (processing.org) library, but everything else is pure Java. Native rxtxSerial libraries for Windows and Mac are included in the project that make it work on my machines, but you may have varying levels of success on your own hardware.

### Main project components:
* com.schooler.ledcube.CubeApplet : Main driver. Run this class's main() to start. A Processing render window will open, and keyboard commands control the playback of the cube in the render window, which is also mirrored to the serial output.
* com.schooler.ledcube.CubeConfig : Main "routine" to play on the cube. Change out the Painter with another one from the com.schooler.ledcube.painter package, or use com.schooler.ledcube.function.Evaluator with a TimeFunction to render 3D functions.
* com.schooler.ledcube.output.SerialCubeOutput : Interface for the serial connection to the cube. Change the serial port name, speed, and etc. here.

### Keyboard commands:
* Space : Play/Pause
* a : pause and go to previous frame
* d : pause and go to next frame
* q : decrease playback speed (press multiple times to slow down, stop completely, and even go in reverse)
* e : increase speed
