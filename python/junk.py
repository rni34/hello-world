# coding: utf-8
# 自分の得意な言語で
# Let's チャレンジ！！
import datetime

input_line = input()
input_list = input_line.split()
# print(input_list)
a = int(input_list[0])
b = int(input_list[1])
c = int(input_list[2])
d = int(input_list[3])
e = int(input_list[4])
n = int(input())
# print(a,b,c,d,e,n)
train_time_table = []
up_down = []
for i in range(n):
    input_line = input()
    direction = input_line[0]
    arrival_time = datetime.time(int(input_line[2:4]), int(input_line[5:7]), 00)
    depart_time = datetime.time(int(input_line[8:10]), int(input_line[11:13]), 00)
    # rint(arrival_time, depart_time)
    train_time_table.append([direction, arrival_time, depart_time])
# print()
# for i in train_time_table:
#     print(i[0],i[1],i[2])

result = []
previous_open = None
previous_close = None
for train_line in train_time_table:
    if train_line[0] == '0':
        gate_close_time = datetime.datetime.combine(datetime.date.today(), train_line[1]) - datetime.timedelta(
            seconds=a)
        gate_open_time = datetime.datetime.combine(datetime.date.today(), train_line[2]) + datetime.timedelta(seconds=b)
    else:
        gate_close_time = datetime.datetime.combine(datetime.date.today(), train_line[1]) - datetime.timedelta(
            seconds=c)
        gate_open_time = datetime.datetime.combine(datetime.date.today(), train_line[1]) + datetime.timedelta(seconds=d)

    result.append([gate_close_time.strftime("%H:%M:%S"), gate_open_time.strftime("%H:%M:%S")])

ans = []

for i, train_line in enumerate(result):
    if i > 0 and result[i - 1][0] > train_line[0]:
        ans.pop()
        ans.append([train_line[0], result[i - 1][1]])
    else:
        ans.append(train_line)

res = []
for i, train_line in enumerate(ans):
    gate_open_time = datetime.time(int(ans[i][1][:2]), int(ans[i][1][3:5]), int(ans[i][1][6:8]))
    gate_close_time = datetime.time(int(ans[i][0][:2]), int(ans[i][0][3:5]), int(ans[i][0][6:8]))
    res.append([gate_close_time, gate_open_time])
final = []
for i, train_line in enumerate(res):
    if i > 0:
        # print(res[i-1][1])
        interval = datetime.datetime.combine(datetime.date.today(), res[i - 1][1]) + datetime.timedelta(seconds=e)
        if interval >= datetime.datetime.combine(datetime.date.today(), train_line[0]):
            final.append([res[i - 1][1], train_line[0]])
        else:
            final.append(train_line)
    else:
        final.append(train_line)

for i in final:
    print(i[0], '-', i[1])

# ・踏切を開く時刻から e 秒以内に再度踏切を閉じる必要がある場合、踏切を開きません。









