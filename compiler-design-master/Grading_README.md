CS 4240 - Compilers Phase I Part I Projcet

Chase Maggio, Jared Raiola, Parsa Esfahani


To run our program for this phase/part of the project:

	0. run '$ make clean'
	1. run '$ make'
	2. run '$ make run filename=<Tiger test program file>'
		a. This will run our program with the passed in <Tiger test program file>
		   tiger program test file. If you want to test with a different test file,
		   please add your file to the root of the project directory change the
		   file name used for testing in the 'run' command of the makefile.
			. If you get an error regarding antlr4 runtime dependencies,
			  make sure to add antlr-4.7.1-complete.jar to your CLASSPATH.
				. The commands that can help with that are:
					1. run '$ cd /usr/local/lib'
					2. run '$ curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar'
					3. run '$ export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"'
				. Then now start back again at step 0. run '$ make clean'
	3*. To clean the generated '*.class' files, please run '$ make clean'.


Thank you!
