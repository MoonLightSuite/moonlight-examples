time = 0:0.1:1000;
a = time;
b = cos(time);

InitBreach
trace = [time' a' b'];
BrTrace = BreachTraceSystem({'a','b'}, trace);
BreachProp=STL_Formula('phi','alw_[0,500] (a[t]>=4)');
rob=BrTrace.CheckSpec('phi');
figure; BrTrace.PlotRobustSat('phi',2);

tic
[robBreach, tau] = STL_Eval(BrTrace.Sys, BreachProp, BrTrace.P, BrTrace.P.traj,'thom');
toc
