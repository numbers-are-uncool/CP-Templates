slay = list(map(int, input().split()))
n = slay[0]
m = slay[1]
a = slay[2]
b = slay[3]
adj = {}
for i in range(n + 1):
    adj[i] = []
for i in range(m):
    u, v = map(int, input().split())
    adj[u].append(v)
    adj[v].append(u)
q = []
vis = [False for x in range(n + 1)]
dis = [0 for x in range(n + 1)]
vis[a] = True
q.append(a)
while len(q) > 0:
    cur = q.pop(0)
    for i in adj[cur]:
        if vis[i] is False:
            vis[i] = True
            dis[i] = dis[cur] + 1
            q.append(i)
print(dis[b])
