#주유소
import sys
input =sys.stdin.readline

N = int(input())
cost = []
gasCost = []

cost = input().rstrip().split(" ")
gasCost = input().rstrip().split(" ")
cost = list(map(int,cost))
gasCost = list(map(int,gasCost))
