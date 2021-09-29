while True :
    Line =  list(map(int, input().split()))
    if Line[0] == 0 and Line[1] == 0 and Line[2] == 0 :
        break;
    Line.sort()

    if (Line[0]*Line[0])+(Line[1]*Line[1]) == (Line[2]*Line[2]) :
        print("right")
    else :
        print("wrong")