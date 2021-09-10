n = int(input());
arr = [[0] * n for i in range(n)]

for i in range(n):
    num = input().split(' ')
    for j in range(len(num)):
        arr[i][j] = num[j]

dp = [[0] * n for i in range(n)]
dp[0][0] = int(arr[0][0])
for i in range(1, n):
    for j in range(n):
        if j == i:
            dp[i][j] = dp[i - 1][j - 1] + int(arr[i][j])
        elif j == 0:
            dp[i][j] = dp[i - 1][j] + int(arr[i][j])
        else:
            dp[i][j] = max(dp[i - 1][j - 1], dp[i - 1][j]) + int(arr[i][j])

print(max(dp[n - 1]))
