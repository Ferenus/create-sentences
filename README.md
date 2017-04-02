The Challenge

Write a program to reassemble a given set of text fragments into their original
sequence. For this challenge your program should have a main method accepting
one argument – the path to a well-formed UTF-8 encoded text file. Each line in the
file represents a test case of the main functionality of your program: read it, process it
and println to the console the corresponding defragmented output.
Each line contains text fragments separated by a semicolon, ‘;’. You can assume that
every fragment has length at least 2.

Example input 1:
O draconia;conian devil! Oh la;h lame sa;saint!

Example output 1:
O draconian devil! Oh lame saint!

Example input 2:
m quaerat voluptatem.;pora incidunt ut labore et d;, consectetur, adipisci
velit;olore magnam aliqua;idunt ut labore et dolore magn;uptatem.;i dolorem
ipsum qu;iquam quaerat vol;psum quia dolor sit amet, consectetur, a;ia
dolor sit amet, conse;squam est, qui do;Neque porro quisquam est, qu;aerat
voluptatem.;m eius modi tem;Neque porro qui;, sed quia non numquam ei;lorem
ipsum quia dolor sit amet;ctetur, adipisci velit, sed quia non numq;unt ut
labore et dolore magnam aliquam qu;dipisci velit, sed quia non numqua;us
modi tempora incid;Neque porro quisquam est, qui dolorem i;uam eius modi
tem;pora inc;am al

Example output 2:
Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet,
consectetur, adipisci velit, sed quia non numquam eius modi tempora
incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
