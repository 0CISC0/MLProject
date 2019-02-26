#import libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from fbprophet import Prophet
#read in and preview our data
df = pd.read_csv('./datasets/candy_production.csv')
df.head()

#rename date column ds, value column y per Prohet specs
df.rename(columns={'observation_date': 'ds'}, inplace=True)
df.rename(columns={'IPG3113N': 'y'}, inplace=True)
#ensure our ds value is truly datetime
df['ds'] = pd.to_datetime(df['ds'])
#filtering here on >=1995, just to pull the last ~20 years of production information
start_date = '01-01-1995'
mask = (df['ds'] > start_date)
df = df.loc[mask]




#initialize Prophet
m = Prophet()
#point towards dataframe
m.fit(df)
#set future prediction window of 2 years
future = m.make_future_dataframe(periods=730)
#preview our data -- note that Prophet is only showing future dates (not values), as we need to call the prediction method still
future.tail()




forecast = m.predict(future)
forecast[['ds', 'yhat', 'yhat_lower', 'yhat_upper']].tail()

