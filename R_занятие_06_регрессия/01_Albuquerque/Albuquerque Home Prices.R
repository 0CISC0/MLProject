Версия 2  14.10.2011


setwd("C:\\Aaa\\Lgu\\001_r\\000")

x <- read.table ("Albuquerque Home Prices_data.txt", header=T)

names(x)

# PRICE SQFT AGE FEATS NE CUST COR  TAX

summary(x)

x$AGE[x$AGE==-9999] <- NA
x$TAX[x$TAX==-9999] <- NA

itog1 <- lm(x$PRICE ~ x$SQFT + x$AGE + x$FEATS + x$NE + x$CUST + x$COR + x$TAX)

summary(itog1)


itog2 <- lm(x$PRICE ~ x$SQFT + x$CUST + x$TAX)

summary(itog2)


plot(x$SQFT, x$TAX)


cor(x$SQFT, x$TAX, use = "complete.obs")

cor(x$SQFT, x$TAX, use = "complete.obs", method = "spearman")

cor(x, use = "complete.obs", method = "spearman")

cor(x, use = "complete.obs", method = "pearson")


cor.test(x$SQFT, x$TAX, use = "complete.obs")



itog3 <- lm(x$PRICE ~ x$SQFT + x$AGE + x$FEATS + x$NE + x$CUST + x$COR)

summary(itog3)


itog4 <- lm(x$PRICE ~ x$SQFT + x$AGE + x$CUST)

summary(itog4)



!!!!!!!!!!!!!!!!
добавить прогноз!!!!!



itog4$res


#  удаление строк с пропусками

#  непраавильно!!!
x2 <- x[x$AGE != NA, ]

dim(x)
dim(x2)

summary(x$AGE)
summary(x2$AGE)

#  праавильно!!!
x3 <- x[!is.na(x$AGE), ]

dim(x3)
summary(x3$AGE)

itog5 <- lm(x3$PRICE ~ x3$SQFT + x3$AGE + x3$CUST)

summary(itog5)

itog5$res

plot(x3$PRICE, itog5$res)

hist(itog5$res)





