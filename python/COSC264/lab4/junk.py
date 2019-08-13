import sys
sys.setrecursionlimit(999999)
alist = []
while True:
    alist.append('a' for i in range(99999))
    print(alist)