T = int(input())

for t in range(T):
    n = int(input())
    arr = []
    dp = [[0]* n for i in range(2)]
    for i in range(2):
        arr.append(list(map(int,input().split(' '))))
    
    dp[0][0]=arr[0][0]
    dp[1][0]=arr[1][0]
    dp[0][1]=dp[1][0]+arr[0][1]
    dp[1][1]=dp[0][0]+arr[1][1]
    
    
    for i in range(2, n):
        for j in range(2):
                dp[0][i]=max(dp[1][i-1],dp[1][i-2])+int(arr[0][i])
                dp[1][i]=max(dp[0][i-1],dp[0][i-2])+int(arr[1][i])

    print(max(dp[0][n-1],dp[1][n-1]))            