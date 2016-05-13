System Requirement: Java 1.7+

This application is responsible for computing local information within a rolling time window of length T, such as the number of
points in the window, the minimum, maximum, or the rolling sum.
An example is given below, with T = 60 and
• T: number of seconds since epoch
• V: value (price ratio)
• N: number of observations in the current window
• RS: the current rolling sum in the current window
• minV: the minimum in the current window
• maxV: the maximum in the current window:

T V N RS MinV MaxV
———————————————————————
1355270609 1.80215 1 1.80215 1.80215 1.80215
1355270621 1.80185 2 3.604 1.80185 1.80215
1355270646 1.80195 3 5.40595 1.80185 1.80215
1355270702 1.80225 2 3.6042 1.80195 1.80225
1355270702 1.80215 3 5.40635 1.80195 1.80225
1355270829 1.80235 1 1.80235 1.80235 1.80235
1355270854 1.80205 2 3.6044 1.80205 1.80235
1355270868 1.80225 3 5.40665 1.80205 1.80235
1355271000 1.80245 1 1.80245 1.80245 1.80245
1355271023 1.80285 2 3.6053 1.80245 1.80285
1355271024 1.80275 3 5.40805 1.80245 1.80285
1355271026 1.80285 4 7.2109 1.80245 1.80285
1355271027 1.80265 5 9.01355 1.80245 1.80285
1355271056 1.80275 6 10.8163 1.80245 1.80285
1355271428 1.80265 1 1.80265 1.80265 1.80265
1355271466 1.80275 2 3.6054 1.80265 1.80275
1355271471 1.80295 3 5.40835 1.80265 1.80295
1355271507 1.80265 3 5.40835 1.80265 1.80295
1355271562 1.80275 2 3.6054 1.80265 1.80275
1355271588 1.80295 2 3.6057 1.80275 1.80295
...

Given an input filename as a single argument, produces a table similar to the one above. 

To execute the application execute file 'process-time-series' by:
process-time-series {filename}

For e.g.
process-time-series data.txt
