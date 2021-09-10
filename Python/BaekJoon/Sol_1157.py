String = input();
alphabet = [0 for i in range(26)];

for i in range(0, len(String)):
    if String[i].isupper():
        alphabet[ord(String[i]) - 65] += 1
    else:
        alphabet[ord(String[i]) - 97] += 1

maxAlpha = max(alphabet);

maxCnt = 0;
idx = -1;
for i in range(0, 26):
    if maxAlpha == alphabet[i]:
        idx = i
        maxCnt += 1

if maxCnt > 1:
    print("?")
else:
    print(chr(idx+65))