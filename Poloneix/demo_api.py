from poloniex_api import Poloniex
import matplotlib.pyplot as plt
import numpy as np


my_polo = Poloniex(
  API_KEY = '',
  API_SECRET = ''
)

my_file = open("some.txt", "w")
#Получаем пятиминутные свечи за последние 30 минут
import time
end_time = int(time.time()) # время окончания - текущее
start_time = int(end_time - 60 * 1051200) # время начала - текущее минус 30 минут
candles = my_polo.returnChartData(currencyPair="USDT_BTC", start=start_time, end=end_time, period=7200)
n = []

quo = []

for i in candles:
   #my_file.write(str(i['low']) + '\n')
   n.append(i['low'])
   
for i in range(1, len(n)-1):
    quo.append(n[i] / n[i-1])
    
for i in quo:
    my_file.write(str(i) + '\n')
    
x = np.arange(len(quo))
y = quo

fig = plt.figure()
plt.plot(x, y)


plt.show()




   

my_file.close()