# Symbol-Balance

A program that read through a Java file and checks to make sure
that all { }’s, ( )'s, [ ]'s, " "’s, and /* */’s are properly balanced.
There are three types of errors that can be encountered:
- The file ends with one or more opening symbols missing their corresponding closing symbols.
- There is a closing symbol without an opening symbol.
- There is a mismatch between closing and opening symbols (for example: { [ } ] ).
If no error is found, BalanceError.java returns null.
