Payment tracker - program that keeps a record of payments. Each payment includes a currency and an amount. The program outputs a list of all the currency and amounts to the console once per minute. The input can be typed into the command line, and optionally also be loaded from a file when starting up.

1. Requirements
===============
JDK 8
Console for input.


2. Build instructions
=====================
Open a terminal/console, change to the program directory and type

ant

for compiling sources code, executing tests and packaging program into build/payment_tracker.jar
Note: There are more available ant targets (e.g. ant test, ant build)


3. Run instructions 
===================
Open a terminal/console, change to the program directory and type

java -jar build/payment_tracker.jar

3.1 Options for running payment tracker program
-----------------------------------------------
-i filename - for reading file with payments records

-r filename - for reading file with currency exchange rate

Example:
java -jar build/payment_tracker.jar -i data/payments.txt -r data/rates.txt

3.2 Payments file format
------------------------
CURRENCY_CODE VALUE
CURRENCY_CODE VALUE
CURRENCY_CODE VALUE

(CURRENCY_CODE is uppercase 3 letter word, VALUE is decimal value)

Example:
GBP 200
USD 100
EUR 400
EUR 2.5

3.3 Rates file format
---------------------
CURRENCY_CODE RATE
CURRENCY_CODE RATE
CURRENCY_CODE RATE

(CURRENCY_CODE is uppercase 3 letter word, RATE is decimal value)
Example:
HKD 10
RMB 0.7
EUR 1.1


3.4 Program input
-----------------
Only following input formats are accepted, other inputs (including empty input, inputs with leading or tailing whitespace) is not accepted and the error message is displayed.

3.4.1 format for adding payment
-------------------------------
CURRENCY_CODE VALUE
- CURRENCY_CODE is uppercase 3 letter word, VALUE is decimal value
Example:
GBP 200
USD 100
EUR 400
EUR 2.5

3.4.2 format for terminating program
------------------------------------
quit
