# -*- coding: utf-8 -*-
"""
Created on Sun Sep 30 19:20:37 2018

@author: GAME
"""


import matplotlib.pyplot as plt
import numpy as np


lag = 0.1
x = np.arange(0.0, 2*np.pi+lag, lag)
print(x)
y = np.cos(x)

fig = plt.figure()
plt.plot(x, y)


plt.show()