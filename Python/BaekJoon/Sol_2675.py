T = int(input())

for t in range(T):
    Str = input().split(' ')
    R = int(Str[0])
    S = Str[1]
    P = ""
    for i in range(len(S)):
        for j in range(R):
            P += S[i]
    print(P)




