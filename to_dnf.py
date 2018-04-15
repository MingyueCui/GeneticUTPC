from sympy import *

file = open("./not.txt")
pr = file.read()
print to_dnf(pr, False)
file.close()
